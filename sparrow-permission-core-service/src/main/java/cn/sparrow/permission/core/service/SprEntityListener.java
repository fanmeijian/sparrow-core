package cn.sparrow.permission.core.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.exception.DenyPermissionException;
import cn.sparrow.permission.exception.NoPermissionException;
import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.common.CurrentEntityManager;
import cn.sparrow.permission.model.common.CurrentUser;
import cn.sparrow.permission.model.common.DeleteAuditLog;
import cn.sparrow.permission.model.common.PermissionCheckResult;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.token.DataPermissionToken;
import cn.sparrow.permission.model.token.PermissionToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SprEntityListener implements PersistEventListener, PreUpdateEventListener, PreDeleteEventListener,
		PostLoadEventListener, PostDeleteEventListener, PreLoadEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void onPersist(PersistEvent event) throws HibernateException {
		checkPermission(event.getObject().getClass().getName(), PermissionEnum.AUTHOR);
	}

	@Override
	public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException {
		// TODO Auto-generated method stub
		log.info("crete already {}", createdAlready);
	}

	@Override
	public void onPreLoad(PreLoadEvent event) {
		// 检查模型的读权限：
		String modelName = event.getPersister().getEntityMetamodel().getName();
		if (!modelName.equals(Model.class.getName())) {
			checkPermission(modelName, PermissionEnum.READER);

			// TODO:数据的读权限
//					if (!checkPermission(modelName, PermissionEnum.READER)) {
//						emptyData(event.getEntity());
//					}
			// 字段读权限
		}
	}

	@Override
	public void onPostLoad(PostLoadEvent event) {
		String modelName = event.getPersister().getEntityMetamodel().getName();
		if (!modelName.equals(Model.class.getName())) {
			// TODO:数据的读权限

			// get data permission token
//			CurrentEntityManager.get().find(DataPermissionToken.class, );

			if (AbstractSparrowEntity.class.isAssignableFrom(event.getEntity().getClass())) {
				DataPermissionToken dataPermissionToken = ((AbstractSparrowEntity) event.getEntity())
						.getDataPermissionToken();
				if (dataPermissionToken != null) {
					PermissionCheckResult permissionCheckResult = new PermissionCheckResult();
					int checkResult = checkDataPermission(
							dataPermissionToken.getSparrowPermissionToken().getPermissionToken(),
							PermissionEnum.READER);
					if (checkResult == -1) {
						emptyData(event.getEntity());
						permissionCheckResult.setDenyDataRead(true);
					}
					if (checkResult == -2) {
						emptyData(event.getEntity());
						permissionCheckResult.setNoDataRead(true);

					}
					((AbstractSparrowEntity) event.getEntity()).setPermissionCheckResult(permissionCheckResult);
				}
			}

			// 字段读权限
		}
	}

	@Override
	public boolean onPreDelete(PreDeleteEvent event) {
		checkPermission(event.getPersister().getEntityMetamodel().getName(), PermissionEnum.DELETER);
		return false;
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		checkPermission(event.getPersister().getEntityMetamodel().getName(), PermissionEnum.EDITOR);
		return false;
	}

	private void checkPermission(String modelName, PermissionEnum permission) {
		if (CurrentEntityManager.get() != null) {
			Model model = CurrentEntityManager.get().find(Model.class, modelName);
			if (model != null) {
				if (model.getSparrowPermissionToken() != null) {
					PermissionToken permissionToken = model.getSparrowPermissionToken().getPermissionToken();
					PermissionService permissionService = new PermissionServiceImpl(CurrentEntityManager.get());
					try {
						permissionService.hasPermission(CurrentUser.get(), permissionToken, permission);
					} catch (DenyPermissionException | NoPermissionException e) {
						throw new HibernateException(
								(e.getClass().isAssignableFrom(DenyPermissionException.class) ? "拒绝 " : "没有权限 ")
										.concat(modelName).concat(" ").concat(e.getMessage()));
					}
				}
			}
		}
	}

	private int checkDataPermission(PermissionToken permissionToken, PermissionEnum permission) {
		if (CurrentEntityManager.get() == null) {
			return 0;
		}

		PermissionService permissionService = new PermissionServiceImpl(CurrentEntityManager.get());
		try {
			permissionService.hasPermission(CurrentUser.get(), permissionToken, permission);
		} catch (DenyPermissionException | NoPermissionException e) {
			// 读权限的时候，不直接抛出错误，而是将对象置为空
			if (PermissionEnum.READER.equals(permission)) {
				return e.getClass().isAssignableFrom(DenyPermissionException.class) ? -1 : -2;
			}
		}
		return 0;
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
//		log.info("{}({}) deleted by {}", event.getPersister().getEntityMetamodel().getName(), event.getId(),
//				CurrentUser.get());
		try {
			CurrentEntityManager.get().persist(new DeleteAuditLog(event.getPersister().getEntityMetamodel().getName(),
					new ObjectMapper().writeValueAsString(event.getId())));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void emptyData(Object object) {

		// 不清空id字段
		List<Field> fieldList = new ArrayList<Field>();
		fieldList.addAll(Arrays.asList(object.getClass().getSuperclass().getDeclaredFields()));
		fieldList.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
		Field[] fields = fieldList.toArray(new Field[] {});
		for (Field field : fields) {
			boolean isId = false;
			for (Annotation annotation : field.getDeclaredAnnotations()) {
				if (annotation.annotationType().equals(Id.class)
						|| annotation.annotationType().equals(EmbeddedId.class)) {
					isId = true;
					break;
				}
			}

			if (!isId) {
				try {
					if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
						Method getMethod = object.getClass().getMethod("get" + StringUtils.capitalize(field.getName()));

						Method setMethod = object.getClass().getMethod("set" + StringUtils.capitalize(field.getName()),
								field.getType());
						Object returnObj = getMethod.invoke(object, new Object[0]);
						returnObj = returnObj == null ? returnObj : null;
						setMethod.invoke(object, returnObj);
					} else {
						if (!Modifier.isFinal(field.getModifiers())) {
							field.set(null, new Object[0]);
						}
					}

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private void emptyDataField(Object object, String fieldName, String type) {
		try {
			Method[] methods = object.getClass().getDeclaredMethods();
			Method setMethod = object.getClass().getMethod(
					"set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), Class.forName(type));
			Method getMethod = object.getClass()
					.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
			Object returnObj = getMethod.invoke(object, new Object[0]);
			returnObj = returnObj == null ? returnObj : null;
			// 不清空id字段
			boolean isId = false;
			List<Field> fieldList = new ArrayList<Field>();
			fieldList.addAll(Arrays.asList(object.getClass().getSuperclass().getDeclaredFields()));
			fieldList.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
//			Field[] fields = ArrayStoreExcept.addAll(object.getClass().getDeclaredFields(),
//					object.getClass().getSuperclass().getDeclaredFields());

			Field[] fields = fieldList.toArray(new Field[] {});
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					for (Annotation annotation : field.getDeclaredAnnotations()) {
						if (annotation.annotationType().equals(Id.class)) {
							isId = true;
						}
					}
				}
			}

			if (!isId) {
				setMethod.invoke(object, returnObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

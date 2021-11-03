package cn.sparrow.permission.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.permission.AbstractDataPermissionPK;
import cn.sparrow.model.permission.ModelPermissionPK;
import cn.sparrow.permission.repository.ModelRepository;
import cn.sparrow.permission.service.IPermission;

// jpa级别的校验
@Component
public final class ReadPermissionListener {
	private static Logger logger = LoggerFactory.getLogger(ReadPermissionListener.class);
	private static IPermission<AbstractDataPermissionPK> dataPermissionService;
	private static IPermission<ModelPermissionPK> modelPermissionService;
//	private static ModelRepository modelRepository;

	@Autowired
	public void setModelIPermission(IPermission<ModelPermissionPK> modelPermissionService) {
		ReadPermissionListener.modelPermissionService = modelPermissionService;
	}

	@Autowired
	public void setPermissionService(IPermission<AbstractDataPermissionPK> dataPermissionService) {
		ReadPermissionListener.dataPermissionService = dataPermissionService;
	}
	
//	@Autowired
//	public void setModelRepository(ModelRepository modelRepository) {
//	    ReadPermissionListener.modelRepository = modelRepository;
//	}

	private void emptyData(Object object) {
		try {

			BeanUtils.copyProperties(object.getClass().getConstructor().newInstance(), object);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
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

	// 删除单据权限检查

	// 新建和编辑单据权限检查
//  @PreUpdate
//  @PrePersist
//  private void beforeAnyUpdate(AbstractSparrowEntity abstractEntity) {
//    String username = SecurityContextHolder.getContext().getAuthentication() == null ? ""
//        : SecurityContextHolder.getContext().getAuthentication().getName();
//    if (abstractEntity.getId() == null) {
//      logger.info("[USER AUDIT] About to add a user" + permissionService
//          .hasReaderPermission(new Model(abstractEntity.getClass().getName()), username));
//    } else {
//      logger.info("[USER AUDIT] About to update/delete user: " + abstractEntity.getId());
//    }
//  }

	// 读取单据权限检查
	@PostLoad
	public void postLoad(AbstractSparrowUuidEntity abstractEntity) {
//
		String username = SecurityContextHolder.getContext().getAuthentication() == null ? ""
				: SecurityContextHolder.getContext().getAuthentication().getName();

		// 将模型对象放进去
//		abstractEntity.setModel(modelRepository.findById(abstractEntity.getClass().getName()).orElse(null));
		
		// 1.模型读者拒绝权限检查
//    if (permissionService.hasDenyReaderPermission(new Model(abstractEntity.getClass().getName()),
//        username)) {
		if (modelPermissionService.hasPermission(new ModelPermissionPK(abstractEntity.getClass().getName(),
				PermissionEnum.READER, PermissionTypeEnum.DENY), username)) {
			String id = abstractEntity.getId();
			emptyData(abstractEntity);
			abstractEntity.setId(id);
			abstractEntity.getErrorMessage().add("模型拒绝读者: " + abstractEntity.getClass().getName());
		}

		// 2.模型读者权限检查
//    if (!permissionService.hasReaderPermission(new Model(abstractEntity.getClass().getName()),
//        username)) {
		if (!modelPermissionService.hasPermission(new ModelPermissionPK(abstractEntity.getClass().getName(),
				PermissionEnum.READER, PermissionTypeEnum.ALLOW), username)) {
			String id = abstractEntity.getId();
			emptyData(abstractEntity);
			abstractEntity.setId(id);
			abstractEntity.getErrorMessage().add("无读取模型权限: " + abstractEntity.getClass().getName());
		}
		;

		// 3.数据读者拒绝权限检查
		if (dataPermissionService.hasPermission(new AbstractDataPermissionPK(abstractEntity.getClass().getName(),
				PermissionEnum.READER, PermissionTypeEnum.DENY, abstractEntity.getId()), username)) {
			String id = abstractEntity.getId();
			emptyData(abstractEntity);
			abstractEntity.setId(id);
			abstractEntity.getErrorMessage().add("本条数据拒绝读者: " + abstractEntity.getId());
		}

		// 先检查本条数据是否配置了读者权限
		if (!dataPermissionService.hasPermission(new AbstractDataPermissionPK(abstractEntity.getClass().getName(),
				PermissionEnum.READER, PermissionTypeEnum.ALLOW, abstractEntity.getId()), username)) {
			// 4.数据读者权限检查
			// 清空对象值
			String id = abstractEntity.getId();
			emptyData(abstractEntity);
			abstractEntity.setId(id);
			abstractEntity.getErrorMessage().add("无读取本条数据的权限: " + id);
		} else {
			// TODO 5.模型属性读者权限检查
			// 有本条数据的权限，再检查是否有某个字段的读者权限
//      Model model = modelService.getModel(abstractEntity);
//      model.getModelAttributes().forEach(modelAttribute -> {
//        if(!permissionService.hasReaderPermission(abstractEntity, username, modelAttribute)) {
//          abstractEntity.getErrorMessage().add("无读字段权限: " + modelAttribute.getId().getName());
//          emptyDataField(abstractEntity, modelAttribute.getId().getName(), modelAttribute.getType());
//        }
//      });
		}
	}
}

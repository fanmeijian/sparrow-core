package cn.sparrow.permission.core.service;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.exception.DenyPermissionException;
import cn.sparrow.permission.exception.NoPermissionException;
import cn.sparrow.permission.model.common.CurrentEntityManager;
import cn.sparrow.permission.model.common.CurrentUser;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.token.PermissionToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SprEntityListener
		implements PersistEventListener, PreUpdateEventListener, PreDeleteEventListener, PostLoadEventListener {

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
	public void onPostLoad(PostLoadEvent event) {
		// TODO Auto-generated method stub

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

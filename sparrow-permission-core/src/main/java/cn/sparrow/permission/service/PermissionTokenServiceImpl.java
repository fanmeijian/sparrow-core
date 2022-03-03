package cn.sparrow.permission.service;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cn.sparrow.permission.model.Model;
import cn.sparrow.permission.model.SparrowPermissionToken;

public class PermissionTokenServiceImpl implements PermissionTokenService {

	private EntityManager entityManager;

	@Override
	public SparrowPermissionToken buildToken(@NotBlank String permissionTokenId) {
		return entityManager.find(SparrowPermissionToken.class, permissionTokenId);
	}

	@Override
	public void update(String permissionTokenId, PermissionToken permissionToken) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePermission(String permissionTokenId, PermissionToken permissionToken) {
		// TODO Auto-generated method stub

	}

	@Override
	public SparrowPermissionToken create(PermissionToken permissionToken) {
		SparrowPermissionToken sparrowPermissionToken = new SparrowPermissionToken(permissionToken);
		entityManager.getTransaction().begin();
		entityManager.persist(sparrowPermissionToken);
		entityManager.getTransaction().commit();
		return sparrowPermissionToken;
	}

	@Override
	public SparrowPermissionToken save(@NotNull PermissionToken permissionToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermissionToken getModelPermissionToken(String modelName) {
		Model model = entityManager.find(Model.class, modelName);
		if (model != null) {
			return model.getSparrowPermissionToken().getPermissionToken();
		} else {
			return null;
		}

	}

	public PermissionTokenServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public PermissionTokenServiceImpl() {

	}

}

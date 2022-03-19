package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.mgt.api.ModelAttributeService;
import cn.sparrow.permission.mgt.service.repository.ModelAttributeRepository;
import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;
import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;

@Service
public class ModelAttributeServiceImpl implements ModelAttributeService {

	@Autowired
	ModelAttributeRepository modelAttributeRepository;
	// @Autowired ModelAttributePermissionService modelAttributePermissionService;

	@Override
	public Page<ModelAttribute> all(Pageable pageable, ModelAttribute modelAttribute) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return modelAttributeRepository.findAll(Example.of(modelAttribute, matcher), pageable);
	}

	@Override
	public ModelAttribute create(@RequestBody ModelAttribute modelAttributes) {
		return modelAttributeRepository.save(modelAttributes);
	}

	@Override
	public ModelAttribute update(ModelAttributePK attributeId,Map<String, Object> map) {
		ModelAttribute source = modelAttributeRepository.getById(attributeId);
		map.remove("id");
		PatchUpdateHelper.merge(source, map);
		return modelAttributeRepository.save(source);
	}

	@Override
	public void delete(@NotNull @RequestBody final List<ModelAttributePK> ids) {
		modelAttributeRepository.deleteByIdIn(ids);
	}

	@Override
	@Transactional
	public void addPermission(ModelAttributePK attributeId, PermissionToken permissionToken) {
		ModelAttribute modelAttribute = modelAttributeRepository.findById(attributeId).get();
		modelAttribute.setSparrowPermissionToken(new SparrowPermissionToken(permissionToken));
		modelAttributeRepository.save(modelAttribute);
	}

	@Override
	@Transactional
	public void removePermission(ModelAttributePK attributeId) {
		ModelAttribute modelAttribute = modelAttributeRepository.getById(attributeId);
		modelAttribute.setSparrowPermissionToken(null);
		modelAttributeRepository.save(modelAttribute);
	}

	@Override
	public ModelAttribute get(ModelAttributePK attributePK) {
		return modelAttributeRepository.findById(attributePK).get();
	}

	@Override
	public Page<ModelAttribute> allAttributes(String modelId, Pageable pageable, ModelAttribute modelAttribute) {
		modelAttribute.getId().setModelId(modelId);
		return this.all(pageable, modelAttribute);
	}

	@Override
	@ResponseStatus(code = HttpStatus.CREATED)
	public ModelAttribute createAttribute(ModelAttribute modelAttribute) {
		return this.create(modelAttribute);
	}

	@Override
	public ModelAttribute getAttribute(String modelId, String attributeId) {
		return this.get(new ModelAttributePK(modelId, attributeId));
	}

	@Override
	public ModelAttribute updateAttribute(String modelId, String attributeId, Map<String, Object> map) {
		return this.update(new ModelAttributePK(modelId, attributeId), map);
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addAttributePermission(String modelId, String attributeId, PermissionToken permissionToken) {
		this.addPermission(new ModelAttributePK(modelId, attributeId), permissionToken);
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeAttributePermission(String modelId, String attributeId) {
		this.removePermission(new ModelAttributePK(modelId, attributeId));
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAttribute(String modelId, List<String> attributeIds) {
		List<ModelAttributePK> modelAttributePKs = new ArrayList<ModelAttributePK>();
		attributeIds.forEach(attributeId->{
			modelAttributePKs.add(new ModelAttributePK(modelId, attributeId));
		});
		this.delete(modelAttributePKs);
	}

	// @PostMapping("/modelAttributes/permissions")
	// public void addPermission(@NotNull @RequestBody final
	// ModelAttributePermission modelAttributePermission) {
	// modelAttributePermissionService.addPermissions(modelAttributePermission);
	// }
	//
	// @DeleteMapping("/modelAttributes/permissions")
	// public void delPermission(@NotNull @RequestBody final
	// ModelAttributePermission modelAttributePermission) {
	// modelAttributePermissionService.delPermissions(modelAttributePermission);
	// }

}

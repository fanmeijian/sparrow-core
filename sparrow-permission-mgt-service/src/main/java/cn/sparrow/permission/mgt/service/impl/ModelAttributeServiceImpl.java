package cn.sparrow.permission.mgt.service.impl;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cn.sparrow.permission.mgt.api.ModelAttributeService;
import cn.sparrow.permission.mgt.service.repository.ModelAttributeRepository;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;

@Service
public class ModelAttributeServiceImpl implements ModelAttributeService {

	@Autowired ModelAttributeRepository modelAttributeRepository;
//	@Autowired ModelAttributePermissionService modelAttributePermissionService;
	
@Override
	public Page<ModelAttribute> all( Pageable pageable, ModelAttribute modelAttribute) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return modelAttributeRepository.findAll(Example.of(modelAttribute, matcher), pageable);
	}
	
	@Override
	public ModelAttribute create( @RequestBody ModelAttribute modelAttributes) {
		return modelAttributeRepository.save(modelAttributes);
	}

	@Override
	public ModelAttribute update(Map<String, Object> map) {
		ModelAttribute source= modelAttributeRepository.getById((ModelAttributePK)map.get("id"));
		map.remove("id");
		PatchUpdateHelper.merge(source, map);
		return modelAttributeRepository.save(source);
	}

	@DeleteMapping("/modelAttributes/batch")
	public void delete(@NotNull @RequestBody final List<ModelAttributePK> ids) {
		modelAttributeRepository.deleteByIdIn(ids);
	}

//	@PostMapping("/modelAttributes/permissions")
//	public void addPermission(@NotNull @RequestBody final ModelAttributePermission modelAttributePermission) {
//		modelAttributePermissionService.addPermissions(modelAttributePermission);
//	}
//
//	@DeleteMapping("/modelAttributes/permissions")
//	public void delPermission(@NotNull @RequestBody final ModelAttributePermission modelAttributePermission) {
//		modelAttributePermissionService.delPermissions(modelAttributePermission);
//	}

	
}

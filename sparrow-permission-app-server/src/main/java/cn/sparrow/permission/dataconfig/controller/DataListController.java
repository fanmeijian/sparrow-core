package cn.sparrow.permission.dataconfig.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.permission.common.listener.CurrentEntityManagerFactory;
import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionExpressionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.core.api.EmployeeTokenService;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.service.ViewFilter;
import cn.sparrow.permission.dataconfig.model.DataList;
import cn.sparrow.permission.dataconfig.repository.DataListRepository;
import cn.sparrow.permission.dataconfig.service.DataListService;
import cn.sparrow.permission.model.resource.SparrowTree;
import cn.sparrow.permission.model.token.EmployeeToken;
import cn.sparrow.permission.model.token.PermissionExpression;
import cn.sparrow.permission.model.token.PermissionToken;

@RestController
public class DataListController {
	@Autowired
	DataListService dataListService;

	@Autowired
	DataListRepository dListRepository;

	@GetMapping("/dataLists/getDataListTreeByParentId/{parentId}")
	public SparrowTree<?, ?> getDataLisTreeByParen(@NotNull @PathVariable("parentId") String parentId) {
		return dataListService.getDataListTreeByParentId(parentId);
	}

	@GetMapping("/dataLists/getDataListTreeByCode/{code}")
	public SparrowTree<?, ?> getDataLisTreeByCode(@NotNull @PathVariable("code") String code) {
		return dataListService.getDataListTreeByCode(code);
	}

	@Autowired
	PermissionService permissionService;
	@Autowired
	EmployeeTokenService employeeTokenService;

	@GetMapping("/dataLists/test")
	public boolean test() {
		System.out.print(CurrentEntityManagerFactory.INSTANCE.get());
		PermissionToken permissionToken = new PermissionToken();
		EmployeeToken employeeToken = new EmployeeToken();
		employeeToken.setEmployeeId("001");
		Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowsMap = new HashMap<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>>();
		Map<PermissionTargetEnum, List<PermissionExpression<?>>> pMap = new HashMap<PermissionTargetEnum, List<PermissionExpression<?>>>();
		List<PermissionExpression<?>> pList = new ArrayList<PermissionExpression<?>>();
		PermissionExpression<String> permissionExpression = new PermissionExpression<String>();
		permissionExpression.setExpression(PermissionExpressionEnum.IN);
		List<String> ids = new ArrayList<String>();
		ids.add("002");
		ids.add("003");
		permissionExpression.setIds(ids);
		pList.add(permissionExpression);

		pMap.put(PermissionTargetEnum.EMPLOYEE, pList);
		allowsMap.put(PermissionEnum.READER, pMap);
		permissionToken.setDenyPermissions(allowsMap);
		return permissionService.hasPermission(employeeToken, permissionToken, PermissionEnum.READER);
	}

	@Autowired
	DataListRepository dataListRepository;

	@GetMapping("/dataLists/getList")
	@ViewFilter
	public List<DataList> getList() {
		return dataListRepository.findAll();
	}

}

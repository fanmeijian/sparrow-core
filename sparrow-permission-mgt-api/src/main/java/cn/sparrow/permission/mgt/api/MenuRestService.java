package cn.sparrow.permission.mgt.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.resource.Menu;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "菜单服务")
@RequestMapping("/menus")
public interface MenuRestService {

	@PostMapping("")
	@Operation(summary = "新增菜单")
	@ResponseBody
	public Menu save(@RequestBody Menu menu);

}

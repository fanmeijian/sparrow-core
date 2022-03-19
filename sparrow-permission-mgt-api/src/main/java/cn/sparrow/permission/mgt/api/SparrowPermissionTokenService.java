package cn.sparrow.permission.mgt.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "令牌管理服务")
@RequestMapping("/sparrowPermissionTokens")
public interface SparrowPermissionTokenService {
  
  @GetMapping("/{tokenId}")
  @Operation(summary = "令牌详情")
  @ResponseBody
  public SparrowPermissionToken get(@PathVariable("tokenId") String tokenId);

  @GetMapping("")
  @Operation(summary = "浏览令牌")
  @ResponseBody
  public Page<SparrowPermissionToken> all(Pageable pageable);

  @PostMapping("")
  @Operation(summary = "创建令牌")
  @ResponseBody
  public SparrowPermissionToken create(@RequestBody PermissionToken permissionToken);

  @PutMapping("/{tokenId}")
  @Operation(summary = "更新令牌")
  @ResponseBody
  public SparrowPermissionToken update(@PathVariable("tokenId") String tokenId,@RequestBody PermissionToken permissionToken);

  @GetMapping("/hasPermission")
  @Operation(summary = "是否拥有权限")
  @ResponseBody
  public boolean hasPermission(String tokenId, String username) ;
}

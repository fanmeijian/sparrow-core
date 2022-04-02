package cn.sparrow.permission.mgt.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.constant.PreserveSysroleEnum;
import cn.sparrow.permission.mgt.api.SysroleService;
import cn.sparrow.permission.mgt.api.scopes.SysroleScope;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleMenuRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleRepository;
import cn.sparrow.permission.mgt.service.repository.UserSysroleRepository;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleMenu;
import cn.sparrow.permission.model.resource.SysroleMenuPK;
import cn.sparrow.permission.model.resource.UserSysrole;
import cn.sparrow.permission.model.resource.UserSysrolePK;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysroleServiceImpl extends AbstractPreserveScope implements SysroleService,SysroleScope {
	@Autowired
	SysroleRepository sysroleRepository;
	// @Autowired SysroleUrlPermissionRepository sysroleUrlPermissionRepository;
	@Autowired
	ApiRepository urlRepository;
	@Autowired
	UserSysroleRepository userSysroleRepository;
	@Autowired
	SysroleMenuRepository sysroleMenuRepository;
	// @Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
	// @Autowired SysroleDataPermissionRepository sysroleDataPermissionRepository;

	public void delMenus(String sysroleId, List<String> menuIds) {
		sysroleMenuRepository.deleteByIdSysroleIdAndIdMenuIdIn(sysroleId, menuIds);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_SYSROLE_USER_LIST + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Page<UserSysrole> getUsers(String sysroleId, Pageable pageable) {
		return userSysroleRepository.findByIdSysroleId(sysroleId, pageable);
	}

	public void addMenus(String sysroleId, List<String> menuIds) {
		Set<SysroleMenu> sysroleMenus = new HashSet<SysroleMenu>();
		menuIds.forEach(f -> {
			sysroleMenus.add(new SysroleMenu(new SysroleMenuPK(sysroleId, f)));
		});
		sysroleMenuRepository.saveAll(sysroleMenus);
	}

	public void init() {
		sysroleRepository.save(new Sysrole("超级管理员", PreserveSysroleEnum.SYSADMIN.name()));
		log.info("Create sysrole {}", PreserveSysroleEnum.SYSADMIN.name());

		sysroleRepository.save(new Sysrole("系统管理员", PreserveSysroleEnum.ADMIN.name()));
		log.info("Create sysrole {}", PreserveSysroleEnum.ADMIN.name());

		userSysroleRepository.save(new UserSysrole(new UserSysrolePK("ROOT",
				sysroleRepository.findByCode(PreserveSysroleEnum.SYSADMIN.name()).get(0).getId())));
		log.info("Grant user {} sysrole SYSADMIN", PreserveSysroleEnum.ADMIN.name());

	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_SYSROLE_USER_ADD + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public void addPermissions(String sysroleId, List<String> usernames) {
		usernames.forEach(f -> {
			userSysroleRepository.save(new UserSysrole(sysroleId, f));
		});
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_SYSROLE_USER_REMOVE + "') or hasRole('ROLE_" + ROLE_SUPER_SYSADMIN + "')")
	public void removePermissions(String sysroleId, List<String> usernames) {
		usernames.forEach(f -> {
			userSysroleRepository.deleteById(new UserSysrolePK(f, sysroleId));
		});

	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_SYSROLE_DELETE + "') or hasRole('ROLE_" + ROLE_SUPER_SYSADMIN + "')")
	public void delete(List<String> ids) {
		sysroleRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_SYSROLE_LIST + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Page<Sysrole> all(Pageable pageable, Sysrole sysrole) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return sysroleRepository.findAll(Example.of(sysrole, matcher), pageable);
	}

	@Override
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_SYSROLE_CREATE + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Sysrole create(Sysrole sysrole) {
		return sysroleRepository.save(sysrole);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_SYSROLE_UPDATE + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Sysrole update(String sysroleId, Map<String, Object> map) {
		Sysrole source = sysroleRepository.getById(sysroleId);
		PatchUpdateHelper.merge(source, map);
		return sysroleRepository.save(source);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_SYSROLE_READ + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Sysrole get(String sysroleId) {
		return sysroleRepository.findById(sysroleId).get();
	}
}

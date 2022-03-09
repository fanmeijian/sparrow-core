package cn.sparrow.permission.mgt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.api.PermissionTokenService;
import cn.sparrow.permission.mgt.api.SparrowPermissionTokenService;
import cn.sparrow.permission.mgt.service.repository.SparrowPermissionTokenRepository;
import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;

@Service
public class SparrowPermissionTokenServiceImpl implements SparrowPermissionTokenService {

    @Autowired
    SparrowPermissionTokenRepository sparrowPermissionTokenRepository;

    @Autowired
    PermissionService permissionService;

    @Override
    public SparrowPermissionToken get(String tokenId) {
        return sparrowPermissionTokenRepository.findById(tokenId).orElse(null);
    }

    public SparrowPermissionToken create(PermissionToken permissionToken) {
        return sparrowPermissionTokenRepository.save(new SparrowPermissionToken(permissionToken));
    }

    public boolean hasPermission(String tokenId, String username) {
        return permissionService.hasPermission(username, tokenId, PermissionEnum.READER);
    }

    @Override
    public Page<SparrowPermissionToken> all(Pageable pageable) {
        return sparrowPermissionTokenRepository.findAll(pageable);
    }

    @Override
    public SparrowPermissionToken update(String tokenId,PermissionToken permissionToken) {
        SparrowPermissionToken sparrowPermissionToken = sparrowPermissionTokenRepository.getById(tokenId);
        sparrowPermissionToken.setPermissionToken(permissionToken);
        return sparrowPermissionTokenRepository.save(sparrowPermissionToken);
    }
}

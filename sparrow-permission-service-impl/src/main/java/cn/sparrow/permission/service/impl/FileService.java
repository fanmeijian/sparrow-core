package cn.sparrow.permission.service.impl;
//package cn.sparrow.permission.service;
//
//import java.io.IOException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.data.rest.core.RepositoryConstraintViolationException;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.Errors;
//import org.springframework.web.multipart.MultipartFile;
//import cn.sparrow.common.service.FileUtil;
//import cn.sparrow.common.service.StorageService;
//import cn.sparrow.model.common.PermissionEnum;
//import cn.sparrow.model.common.PermissionTypeEnum;
//import cn.sparrow.model.permission.AbstractDataPermissionPK;
//import cn.sparrow.model.permission.FilePermission;
//import cn.sparrow.model.permission.FilePermissionPK;
//import cn.sparrow.model.permission.SparrowFile;
//import cn.sparrow.model.permission.SysroleFilePermission;
//import cn.sparrow.model.permission.UserFilePermission;
//import cn.sparrow.permission.repository.FileRepository;
//import cn.sparrow.permission.repository.SysroleFilePermissionRepository;
//import cn.sparrow.permission.repository.UserFilePermissionRepository;
//
//@Service
//public class FileService {
//
//  private final StorageService storageService;
//
//  @Autowired
//  public FileService(StorageService storageService) {
//    this.storageService = storageService;
//  }
//
//  @Autowired
//  FileRepository fileRepository;
//
//  @Autowired
//  SysroleFilePermissionRepository sysroleFileRepository;
//
//  @Autowired
//  UserFilePermissionRepository userFilePermissionRepository;
//  @Autowired
//  SysroleFilePermissionRepository sysroleFilePermissionRepository;
//  
//  @Autowired FilePermissionService filePermissionService;
//  @Autowired DataPermissionService dataPermissionService;
//
//  @PreAuthorize(value = "")
//  public Resource dowload(String id) throws Exception {
//    // storageService.load(fileRepository.findById(id).orElse(null).getUrl())
//    SparrowFile file = fileRepository.findById(id).orElse(null);
//    
//    boolean allow = dataPermissionService.hasPermission(
//        new AbstractDataPermissionPK(SparrowFile.class.getName(), PermissionEnum.DOWNLOAD, PermissionTypeEnum.ALLOW, id),
//        SecurityContextHolder.getContext().getAuthentication().getName());
//    
//    boolean deny = dataPermissionService.hasPermission(
//        new AbstractDataPermissionPK(SparrowFile.class.getName(), PermissionEnum.DOWNLOAD, PermissionTypeEnum.DENY, id),
//        SecurityContextHolder.getContext().getAuthentication().getName());
//    
////    
////    if(file.getErrorMessage().size()>0) {
////      throw new Exception(file.getErrorMessage().get(0));
////    }
//    
//    if(deny||!allow) {
//      throw new Exception("无下载权限！");
//
//    }
//    
//    return storageService.loadAsResource(file.getUrl());
//  }
//
//  @PreAuthorize(value = "")
//  public void share(String fileId, FilePermission filePermission) {
//
//  }
//
//  @PreAuthorize(value = "")
//  public void forward(String fileId) {
//    // 转发一个副本给别人
//  }
//
//  public void addPermissions(FilePermission filePermission) {
//    if (filePermission.getUserFilePermissionPKs() != null) {
//      filePermission.getUserFilePermissionPKs().forEach(f -> {
//        userFilePermissionRepository.save(new UserFilePermission(f));
//      });
//    }
//
//    if (filePermission.getSysroleFilePermissionPKs() != null) {
//      filePermission.getSysroleFilePermissionPKs().forEach(f -> {
//        sysroleFilePermissionRepository.save(new SysroleFilePermission(f));
//      });
//    }
//  }
//
//  public void delPermissions(FilePermission filePermission) {
//    if (filePermission.getUserFilePermissionPKs() != null) {
//      userFilePermissionRepository.deleteByIdIn(filePermission.getUserFilePermissionPKs());
//    }
//
//    if (filePermission.getSysroleFilePermissionPKs() != null) {
//      sysroleFilePermissionRepository.deleteByIdIn(filePermission.getSysroleFilePermissionPKs());
//    }
//  }
//
//  public String upload(MultipartFile file) {
//    // upload file and caculate the hash
//    try {
//      String shaChecksum = FileUtil.getChecksum(file.getInputStream());
//      storageService.store(file.getInputStream(), shaChecksum);
//      SparrowFile sparrowFile = new SparrowFile();
//      sparrowFile.setName(file.getName());
//      sparrowFile.setFileName(file.getOriginalFilename());
//      sparrowFile.setHash(shaChecksum);
//      sparrowFile.setUrl(storageService.load(shaChecksum).toString());
//      fileRepository.save(sparrowFile);
//      return sparrowFile.getId();
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//
//    return null;
//  }
//}

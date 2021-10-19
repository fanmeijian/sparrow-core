package cn.sparrow.common.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cn.sparrow.model.permission.FilePermission;
import cn.sparrow.permission.repository.FileRepository;
import cn.sparrow.permission.service.FileService;

@RestController
public class FileController {
  @Autowired
  FileService fileService;
  @Autowired
  FileRepository fileRepository;

  @RequestMapping(value = "/files", method = RequestMethod.POST, consumes = "multipart/form-data")
  @ResponseBody
  public String upload(@RequestParam("file") MultipartFile file) throws IOException {
    return fileService.upload(file);
  }

  @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  @ResponseBody
  public ResponseEntity<Resource> download(@PathVariable String fileId) throws Exception {
    Resource file = fileService.dowload(fileId);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + fileRepository.findById(fileId).orElse(null).getFileName() + "\"").body(file);
  }
  
  @PostMapping("/files/permissions")
  public void addPermissions(@RequestBody FilePermission filePermission) {
    fileService.addPermissions(filePermission);
  }
  
  @DeleteMapping("/files/permissions")
  public void delPermissions(@RequestBody FilePermission filePermission) {
    fileService.delPermissions(filePermission);
  }
}

package cn.sparrow.common.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.sparrow.common.service.CopyInputStream;
import cn.sparrow.permission.service.FileService;

@Controller	
public class FileController {
	@Autowired
	FileService fileService;

	@RequestMapping(value = "/files", method = RequestMethod.POST, consumes = "multipart/form-data")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {
		try {
			return fileService.upload(file.getInputStream(), file.getOriginalFilename(), file.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] download(@PathVariable String fileId) throws IOException {
		byte[] b = Files.readAllBytes(Paths.get("/Users/fansword/git/sparrow-platform/d0b65fa405dcb9018f811f64b86491a4"));
//		Resource file = fileService.dowload(fileId);
		return b;
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//				.body(new CopyInputStream(file.getInputStream()).getByte());
	}
}

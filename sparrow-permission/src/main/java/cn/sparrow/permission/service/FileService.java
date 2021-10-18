package cn.sparrow.permission.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.common.service.StorageService;
import cn.sparrow.model.permission.FilePermission;
import cn.sparrow.model.permission.SparrowFile;
import cn.sparrow.permission.repository.FileRepository;
import cn.sparrow.permission.repository.SysroleFilePermissionRepository;

@Service
public class FileService {

	private final StorageService storageService;

	@Autowired
	public FileService(StorageService storageService) {
		this.storageService = storageService;
	}

	@Autowired
	FileRepository fileRepository;

	@Autowired
	SysroleFilePermissionRepository sysroleFileRepository;

	@PreAuthorize(value = "")
	public Resource dowload(String id) {
//		storageService.load(fileRepository.findById(id).orElse(null).getUrl())
		return storageService.loadAsResource(fileRepository.findById(id).orElse(null).getUrl());
	}

	@PreAuthorize(value = "")
	public void share(String fileId, FilePermission filePermission) {

	}

	@PreAuthorize(value = "")
	public void forward(String fileId) {
		// 转发一个副本给别人
	}

	@GetMapping("/files")
	@ResponseBody
	public Iterable<SparrowFile> listUploadedFiles() {
		// 会显示匿名用户和认证用户的可用文件信息；list权限，即能否看到列表
		return fileRepository.findAll();

	}

	public String upload(InputStream file, String fileName, String name) {
		// upload file and caculate the hash
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");

			String shaChecksum = getFileChecksum(digest, file);
			storageService.store(file, shaChecksum);
			SparrowFile sparrowFile = new SparrowFile();
			sparrowFile.setName(name);
			sparrowFile.setFileName(fileName);
			sparrowFile.setHash(shaChecksum);
			sparrowFile.setUrl(storageService.load(shaChecksum).toString());
			fileRepository.save(sparrowFile);
			return sparrowFile.getId();
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private String getFileChecksum(MessageDigest digest, InputStream fis) throws IOException {
		// Get file input stream for reading the file content
//	    FileInputStream fis = new FileInputStream(file);

		// Create byte array to read data in chunks
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		// Read file data and update in message digest
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		;

		// close the stream; We don't need it now.
		fis.close();

		// Get the hash's bytes
		byte[] bytes = digest.digest();

		// This bytes[] has bytes in decimal format;
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		// return complete hash
		return sb.toString();
	}

}

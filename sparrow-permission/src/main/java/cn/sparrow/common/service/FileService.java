package cn.sparrow.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.sparrow.model.permission.SparrowFile;
import cn.sparrow.permission.repository.FileRepository;
import cn.sparrow.permission.repository.SysroleFileRepository;

/***
 * perform the file upload, download and permission check;
 * 
 * @author sword
 *
 */

public class FileService {

	private final StorageService storageService;

	@Autowired
	public FileService(StorageService storageService) {
		this.storageService = storageService;
	}

	@Autowired
	FileRepository fileRepository;

	@Autowired
	SysroleFileRepository sysroleFileRepository;

	/***
	 * 显示当前用户可以浏览的文件列表；同时包含了匿名用户可以查看的，及认证用户可以查看的列表。
	 * 
	 * @return list
	 * 
	 */
	@GetMapping("/files")
	@ResponseBody
	public Iterable<SparrowFile> listUploadedFiles() {
		// 会显示匿名用户和认证用户的可用文件信息；list权限，即能否看到列表
		return fileRepository.findAll();

	}

	/***
	 * 显示当前用户可以浏览的文件列表；同时包含了匿名用户可以查看的，及认证用户可以查看的列表。
	 * 
	 * @return list
	 * 
	 */
//	@GetMapping("/myFiles")
//	@ResponseBody
//	public List<SparrowFile> myFiles() {
//		// 会显示匿名用户和认证用户的可用文件信息；list权限，即能否看到列表
//		return fileRepository.listByPermission(SecurityContextHolder.getContext().getAuthentication().getName());
//
//	}
//
//	/**
//	 * POST {"username":"sysadmin","fileId":"1","permission":"DOWNLOAD"}
//	 * 
//	 * @param uFilePK
//	 * @return
//	 */
//	@PostMapping("/filePermission")
//	@ResponseBody
//	public UserFile filePermission(@RequestBody UserFilePK uFilePK) {
//		UserFile userFile = new UserFile();
//		userFile.setId(uFilePK);
//		return userFileRepository.save(userFile);
//
//	}
//
//
//	/**
//	 * 根据权限确定用户是否可以下载文件
//	 * 
//	 * @param filename
//	 * @return
//	 */
//	@GetMapping("/files/{fileId}")
//	@ResponseBody
//	public ResponseEntity<Resource> serveFile(@PathVariable String fileId) {
//		SparrowFile swdFile = fileRepository
//				.getByUsernameAndFileId(SecurityContextHolder.getContext().getAuthentication().getName(), fileId);
//		if (swdFile != null) {
//			Resource file = storageService.loadAsResource(swdFile.getName());
//			return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//					.body(file);
//		} else {
//			return null;
//		}
//	}
//
//	/***
//	 * 
//	 * 上传文件，暂时不做控制和限制
//	 * 
//	 * @param file
//	 * @return
//	 */
//	@PostMapping("/files")
//	@ResponseBody
//	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
//
//		// upload file and caculate the hash
//		try {
//			MessageDigest digest = MessageDigest.getInstance("MD5");
//
//			String shaChecksum = getFileChecksum(digest, file.getInputStream());
//			storageService.store(file, shaChecksum);
//			SparrowFile swdFile = new SparrowFile();
//			swdFile.setName(file.getOriginalFilename());
//			swdFile.setHash(shaChecksum);
//			swdFile.setUrl(storageService.load(file.getOriginalFilename()).toString());
//			fileRepository.save(swdFile);
//			return shaChecksum;
////			redirectAttributes.addFlashAttribute("message",
////					"You successfully uploaded " + file.getOriginalFilename() + "!");
//		} catch (NoSuchAlgorithmException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//	
//
////
//	@ExceptionHandler(StorageFileNotFoundException.class)
//	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//		return ResponseEntity.notFound().build();
//	}
//
//	private String getFileChecksum(MessageDigest digest, InputStream fis) throws IOException {
//		// Get file input stream for reading the file content
////	    FileInputStream fis = new FileInputStream(file);
//
//		// Create byte array to read data in chunks
//		byte[] byteArray = new byte[1024];
//		int bytesCount = 0;
//
//		// Read file data and update in message digest
//		while ((bytesCount = fis.read(byteArray)) != -1) {
//			digest.update(byteArray, 0, bytesCount);
//		}
//		;
//
//		// close the stream; We don't need it now.
//		fis.close();
//
//		// Get the hash's bytes
//		byte[] bytes = digest.digest();
//
//		// This bytes[] has bytes in decimal format;
//		// Convert it to hexadecimal format
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < bytes.length; i++) {
//			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//		}
//
//		// return complete hash
//		return sb.toString();
//	}

}

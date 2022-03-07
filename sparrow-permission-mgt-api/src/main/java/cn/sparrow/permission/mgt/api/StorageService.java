package cn.sparrow.permission.mgt.api;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	void init();

	void store(MultipartFile file,String fileName);
	
	void store(MultipartFile file);
	
	void store(InputStream file, String fileName);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();
}

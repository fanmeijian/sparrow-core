package cn.sparrow.common.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.sparrow.common.exception.StorageException;
import cn.sparrow.common.exception.StorageFileNotFoundException;

@Component
public class StorageServiceImpl implements StorageService {
	private final Path rootLocation;

	public StorageServiceImpl() {
		this.rootLocation = Paths.get(System.getProperty("user.dir"));
	}

	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		store(file, filename);
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
	
	public byte[] getFileByte(String filename) {
		Path path = rootLocation.resolve(filename);
		try {
			return this.getClass().getResourceAsStream(path.toString()).readAllBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);

		}

	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public void store(MultipartFile file, String fileName) {
		String filename = fileName;
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}

	}

	@Override
	public void store(InputStream file, String fileName) {
		try {
			CopyInputStream cis = new CopyInputStream(file);

			Files.copy(cis.getCopy(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

//class CopyInputStream
//{
//    private InputStream _is;
//    private ByteArrayOutputStream _copy = new ByteArrayOutputStream();
//
//    /**
//     * 
//     */
//    public CopyInputStream(InputStream is)
//    {
//        _is = is;
//
//        try
//        {
//            copy();
//        }
//        catch(IOException ex)
//        {
//            // do nothing
//        }
//    }
//
//    private int copy() throws IOException
//    {
//        int read = 0;
//        int chunk = 0;
//        byte[] data = new byte[256];
//
//        while(-1 != (chunk = _is.read(data)))
//        {
//            read += data.length;
//            _copy.write(data, 0, chunk);
//        }
//
//        return read;
//    }
//
//    public InputStream getCopy()
//    {
//        return (InputStream)new ByteArrayInputStream(_copy.toByteArray());
//    }
//}

package cn.sparrow.common.idconverter;

import java.io.Serializable;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import cn.sparrow.model.permission.UserFile;
import cn.sparrow.model.permission.UserFilePK;

@Component
public class UserFilePermissionIdConverter implements BackendIdConverter {

	@Override
	public boolean supports(Class<?> delimiter) {
		return UserFile.class.equals(delimiter);
	}

	@Override
	public Serializable fromRequestId(String id, Class<?> entityType) {
	    String[] parts = id.split(",");
	    UserFilePK pk = new UserFilePK();
	    pk.setFileId(parts[0]);
	    pk.setPermission(parts[1]);
	    pk.setUsername(parts[2]);

	    return pk;
	}

	@Override
	public String toRequestId(Serializable id, Class<?> entityType) {
		UserFilePK pk = (UserFilePK)id;
	    return String.format("%s,%s,%s", pk.getFileId(),pk.getPermission(),pk.getUsername());
	}

}

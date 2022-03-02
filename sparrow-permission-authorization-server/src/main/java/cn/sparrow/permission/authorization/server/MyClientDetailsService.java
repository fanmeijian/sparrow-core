package cn.sparrow.permission.authorization.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.authorization.server.model.OauthClient;
import cn.sparrow.permission.authorization.server.repository.OauthClientRepository;

@Component
public class MyClientDetailsService implements ClientDetailsService {

	@Autowired
	OauthClientRepository oauthClientRepository;
//	@Autowired
//	UserDetailsService userDetailsService;
//	@Autowired
//	UserDetails userDetails;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		OauthClient client = oauthClientRepository.findById(clientId).get();

//		userDetails.getAuthorities();
		BaseClientDetails details = new BaseClientDetails(client.getClientId(),
				"",
				"any",
				client.getGrantType(),
				"",
				client.getRedirectUri());
        details.setClientSecret(client.getClientSecret());
        details.addAdditionalInformation("subject", "sysadmin");
        return details;
	}

}

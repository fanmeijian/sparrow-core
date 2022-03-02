package cn.sparrow.permission.authorization.server;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

//@RestController
public class OauthClientController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	HttpServletRequest servletContext;

	/***
	 * 修改密码
	 * 
	 * @author fanmj
	 *
	 */
//	@PostMapping("/oauth/password")
//	public ApiResponse changePassword(@RequestBody final User user) {
//		Optional<User> opticalUser = userRepository.findById(user.getUsername());
//		if (opticalUser.isEmpty())
//			return HttpBusinessStatusCode.USER_NOT_FOUND;
//		loginLogService.loginLog(user.getUsername(), servletContext.getRemoteAddr());
//		User swdUser = opticalUser.get();
//		if (!user.getPassword().equals(user.getSecondPassword()))
//			return HttpBusinessStatusCode.SECOND_PASSWORD_NOT_MATCH;
//		if (!passwordEncoder.matches(user.getOldPassword(), swdUser.getPassword()))
//			return HttpBusinessStatusCode.OLD_PASSWORD_NOT_MATCH;
//		opticalUser.get().setPassword(passwordEncoder.encode(user.getPassword()));
//		userRepository.save(opticalUser.get());
//		return ApiResponseFactory.getNormalReponse();
//	}

	/***
	 * 重置密码
	 * 
	 * @author fanmj
	 *
	 */
//	@PatchMapping("/oauth/password")
//	public ApiResponse resetPassword(@RequestBody final User user) {
//		Optional<User> opticalUser = userRepository.findById(user.getUsername());
//		if (opticalUser.isEmpty())
//			return HttpBusinessStatusCode.USER_NOT_FOUND;
//		User swdUser = opticalUser.get();
//		swdUser.setPassword(passwordEncoder.encode(user.getPassword()));
//		userRepository.save(swdUser);
//		return ApiResponseFactory.getNormalReponse();
//	}
}

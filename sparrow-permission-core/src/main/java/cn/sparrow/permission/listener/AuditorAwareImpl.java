//package cn.sparrow.permission.listener;
//
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.AuditorAware;
//
///**
// * 
// * 
// * @author fansword
// *
// */
//public class AuditorAwareImpl implements AuditorAware<String> {
//	final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Override
//	public Optional<String> getCurrentAuditor() {
//		return null;
////		try {
////			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////			if (authentication instanceof AnonymousAuthenticationToken) {
////				return Optional.of("SparrowSystem");
////			} else {
////				if (authentication == null) {
////					return Optional.of("SparrowSystem");
////				}
////				return Optional.of(authentication.getName());
////			}
////		} catch (Exception ex) {
////			logger.error("get user Authentication failed: " + ex.getMessage(), ex);
////			return Optional.of("SparrowSystem");
////		}
//	}
//}

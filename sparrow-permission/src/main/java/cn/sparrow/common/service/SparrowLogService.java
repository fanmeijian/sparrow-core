package cn.sparrow.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.common.repository.OperationLogRepository;


@Service
public class SparrowLogService {
	@Autowired
	OperationLogRepository operationLogRepository;
	
//	@Autowired
//	Marshaller marshaller;
	
//	@GetMapping("/api/opLogs")
//	public Page<OperationLog> opLogs(@PageableDefault Pageable p){ 
//		List<OperationLog> operationLogs= new ArrayList<OperationLog>();
//		Page<OperationLog> swdOperationLog = operationLogRepository.findAll(p);
//		swdOperationLog.forEach(o-> {
//			OperationLog operationLog=new OperationLog(o);
////			try {
//////				operationLog.setParams(marshaller.unmarshal(o.getModelBytearray(), null));
////				o.setParams(marshaller.unmarshal(o.getModelBytearray(), null));
////			} catch (IgniteCheckedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			operationLogs.add(operationLog);
//		});
//		return swdOperationLog;
//	}
	
//	@Override
//	  public void operationLog(HttpServletRequest request, String opName, Object[] opArgs,
//	      Object result) {
//	    OperationLog operationLog = new OperationLog();
//
//	    operationLog.setOpTime(new Date());
//	    operationLog.setUri("[" + request.getMethod() + "]" + request.getServletPath());
//	    operationLog.setIp(request.getRemoteAddr());
//	    // operationLog.setUsername(request.getRemoteUser());
//	    operationLog.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//	    try {
//	      operationLog.setModelBytearray(marsh.marshal(opArgs));
//	    } catch (IgniteCheckedException e) {
//	      // TODO Auto-generated catch block
//	      e.printStackTrace();
//	    }
//	    operationLogRepository.save(operationLog);
//
//	  }
}

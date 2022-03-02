package cn.sparrow.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.permission.repository.app.FlowNoRepository;


@RestController
public class FlowNoServiceImpl {

	@Autowired
	FlowNoRepository flowNoRepository;
	
//	
//	@GetMapping("/api/flowNo/{appId}/{code}")
//	public String flowNo(@PathVariable(name = "appId") String appId,@PathVariable(name = "code") String code) {
//		
//		FlowNoPK flowNoPK = new FlowNoPK();
//		flowNoPK.setAppId(appId);
//		flowNoPK.setCode(code);
//		
//		return getFlowNo(flowNoPK);
//	}
//	
//	private synchronized String getFlowNo(FlowNoPK flowNoPK) {
//		FlowNo swdFlowNo = flowNoRepository.findById(flowNoPK).orElse(null);
//		if(swdFlowNo==null) {
//			return "{'msg':'尚未设置编号规则'}";
//		}else {
//			//锁定读和序列化执行代码
//			synchronized (swdFlowNo) {
//				Map<String,Object> map =new HashMap<String, Object>();
//				map.put("sequenceNo", swdFlowNo.getSequenceNo());
//				map.put("YEAR", Calendar.getInstance().get(Calendar.YEAR));
//				int month = Calendar.getInstance().get(Calendar.MONTH)+1;
//				int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//				map.put("MONTH", month<10?"0"+ String.valueOf(month):month);
//				map.put("DAY", day<10?"0"+String.valueOf(day):day);
//				String noString= MVEL.evalToString(swdFlowNo.getEl(),map);
//				swdFlowNo.setSequenceNo(swdFlowNo.getSequenceNo().add(1L));
//				flowNoRepository.save(swdFlowNo);
//				return noString;
//			}
//		}
//	}
	
	
}

package com.ultimate.upms.api.feign;

import com.ultimate.self.common.core.constant.ServiceNameConstants;
import com.ultimate.self.common.core.util.R;
import com.ultimate.upms.api.dataobject.AdminUserDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author kanwj
 * @ClassName CustomerController
 * @description: TODO
 * @date 2023年07月26日
 * @version: 1.0
 */
@FeignClient(contextId = "RemoteUserService", value = ServiceNameConstants.ADMIN_SERVER)
public interface RemoteUserService {


	/**
	 * @description
	 * @author  kanwj 
	 * @date    2023/7/26 15:04
	 * @param	username	
	 * @return  com.qiaqiafood.sfa.common.core.util.R<com.qiaqiafood.sfa.org.dto.UserInfo>
	*/
	@GetMapping("/user/getUserByUserName/{username}")
	R<AdminUserDO> getUserByUserName(@PathVariable("username") String username);

}

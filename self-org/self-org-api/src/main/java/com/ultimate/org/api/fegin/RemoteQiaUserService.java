package com.ultimate.org.api.fegin;

import com.ultimate.org.api.dto.OrgDealerDto;
import com.ultimate.org.api.dto.UserInfo;
import com.ultimate.org.api.dto.UserOrgDto;
import com.ultimate.org.api.dto.UserQry;
import com.ultimate.org.api.entity.User;
import com.ultimate.self.common.core.constant.SecurityConstants;
import com.ultimate.self.common.core.constant.ServiceNameConstants;
import com.ultimate.self.common.core.util.R;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kanwj
 * @ClassName CustomerController
 * @description: TODO
 * @date 2023年07月26日
 * @version: 1.0
 */
@FeignClient(contextId = "RemoteQiaUserService", value = ServiceNameConstants.ADMIN_SERVER)
public interface RemoteQiaUserService {

	/**
	 * @description
	 * @author  kanwj 
	 * @date    2023/7/26 15:04 
	 * @param	username	
	 * @param	from	
	 * @return  com.qiaqiafood.sfa.common.core.util.R<com.qiaqiafood.sfa.org.entity.User> 
	*/
	@GetMapping("/qiauser/{username}")
	R<SecurityProperties.User> custom(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * @description
	 * @author  kanwj 
	 * @date    2023/7/26 15:04
	 * @param	username	
	 * @param	from	
	 * @return  com.qiaqiafood.sfa.common.core.util.R<com.qiaqiafood.sfa.org.dto.UserInfo> 
	*/
	@GetMapping("/qiauser/dealer/{username}")
	R<UserInfo> getUserByUserName(@PathVariable("username") String username, @RequestParam(value = "source", required = false) String source, @RequestHeader(SecurityConstants.FROM) String from);

	@PostMapping("/qiauser/getUserByCode")
	R<List<User>> getUserByCodeList(@RequestParam("userCode") List<String> userCode, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询某个用户所属组织列表
	 * @param userQry 工号
	 * @return
	 */
	@PostMapping("/qiauser/getOrgByUserCode")
	R<List<OrgDealerDto>> getOrgByUserCode(@RequestBody UserQry userQry);

	/**
	 * 查询某个用户的所有下属
	 * @param userQry 工号
	 * @return
	 */
	@PostMapping("/qiauser/getUserOrgByCode")
	R<List<UserOrgDto>> getUserOrgByCode(@RequestBody UserQry userQry);
}

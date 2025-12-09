package com.ultimate.self.upms.api.feign;

import com.ultimate.self.common.framework.constant.RpcConstants;
import com.ultimate.self.common.framework.pojo.CommonResult;
import com.ultimate.self.upms.api.vo.UserProfileRespVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "RemoteUserService", value = RpcConstants.SYSTEM_NAME)
@Tag(name = "RPC 服务 - 用户")
public interface RemoteUserService {

	/**
	 * 描述：获取用户相关信息
	 * 作者：kanwj
	 * 日期：2025/12/2 10:37
	 */

	/**
	 * 描述：getUserByUserName
	 * 作者：kanwj
	 * 日期：2025/12/2 10:55
	 * 入参：
	 * 出参：
	 */
	@GetMapping("/user/getUserByUserName/{username}")
	CommonResult<UserProfileRespVO> getUserByUserName(@PathVariable("username") String username);

}

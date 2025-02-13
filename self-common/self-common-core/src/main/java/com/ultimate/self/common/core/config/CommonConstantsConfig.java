package com.ultimate.self.common.core.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author: lijun002
 * @description: 动态化配置，如clientId
 * @date: 2023/6/16
 */
@Configuration
@RefreshScope
@Data
@ToString
public class CommonConstantsConfig {
	/**
	 * clientId,区分不同客户端，登录鉴权等场景使用
	 */
	@Value("${sfa.clientId:sfa}")
	private String clientId;

	/**
	 * 组织对应经销商缓存时间，单位秒
	 */
	@Value("${expire.orgDealerExpireTime:300}")
	private Long orgDealerExpireTime;


	/**
	 * 人员对应组织缓存时间，单位秒
	 */
	@Value("${expire.userOrgExpireTime:300}")
	private Long userOrgExpireTime;

	/**
	 * 任务相关缓存时间，单位秒
	 */
	@Value("${expire.taskExpireTime:300}")
	private Long taskExpireTime;

	/**
	 * sfaUser缓存时间，单位秒
	 */
	@Value("${expire.sfaUserExpireTime:604800}")
	private Long sfaUserExpireTime;

	/**
	 * sfaUser缓存时间，单位秒
	 */
	@Value("${check.active.code:A0251,A057,A032,A027,A090,A180,A129,A131,A162,A045,A022,A064,A191,A011,A158,A050,A193,A026,A124,A061}")
	private List<String> activeCode;

	@Value("${check.active.reduce:500}")
	private Integer reduce;
}

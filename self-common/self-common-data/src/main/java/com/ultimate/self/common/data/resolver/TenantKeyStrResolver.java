package com.ultimate.self.common.data.resolver;

import com.ultimate.self.common.core.util.KeyStrResolver;
import com.ultimate.self.common.data.tenant.TenantContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author sfa
 * @date 2020/9/29
 * <p>
 * 租户字符串处理（方便其他模块获取）
 */
@Component
public class TenantKeyStrResolver implements KeyStrResolver {

	/**
	 * 传入字符串增加 租户编号:in
	 * @param in 输入字符串
	 * @param split 分割符
	 * @return
	 */
	@Override
	public String extract(String in, String split) {
		return TenantContextHolder.getTenantId() + split + in;
	}

	/**
	 * 返回当前租户ID
	 * @return
	 */
	@Override
	public String key() {
		return String.valueOf(TenantContextHolder.getTenantId());
	}

}

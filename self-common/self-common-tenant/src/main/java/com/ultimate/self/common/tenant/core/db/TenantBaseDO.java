package com.ultimate.self.common.tenant.core.db;

import com.ultimate.self.common.mybatis.core.dataobject.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述：拓展多租户的 BaseDO 基类
 * 作者：kanwj
 * 日期：2025/9/11 10:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantBaseDO extends BaseDo {
    /**
     * 多租户编号
     */
    private Long tenantId;
}

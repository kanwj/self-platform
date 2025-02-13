package com.ultimate.org.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijun
 * @ClassName
 * @description:
 * @date 2023年08月15日
 * @version: 1.0
 */
@Schema
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrgDto {

	@Schema(description = "ID")
	private Long id;

	@Schema(description = "组织编码")
	private String orgCode;

	@Schema(description = "组织名称")
	private String orgName;

	@Schema(description = "上级组织编码")
	private String parentOrgCode;

	@Schema(description = "上级组织名称")
	private String parentOrgName;

	@Schema(description = "用户编码")
	private String code;

	@Schema(description = "用户姓名")
	private String name;

	@Schema(description = "联系电话")
	private String mobile;

	@Schema(description = "组织级别")
	private String orgLevel;
}

package com.ultimate.org.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @author lijun002
 * @ClassName
 * @description:
 * @date 2023年08月15日
 * @version: 1.0
 */
@Data
@Schema
@Accessors(chain = true)
@NoArgsConstructor
public class UserQry {

	private String userCode;

	private String name;

	private Integer pageNum;

	private Integer pageSize;

	private Set<String> uniqueOrgs;

	@Schema(name = "组织编码")
	private String orgCode;
	@Schema(name = "组织级别")
	private String orgLevel;

	public UserQry(String userCode) {
		this.userCode = userCode;
	}
}

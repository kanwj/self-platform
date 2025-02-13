package com.ultimate.org.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lijun
 * @ClassName
 * @description:
 * @date 2023年08月15日
 * @version: 1.0
 */
@Schema
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrgDealerDto implements Serializable {

	private static final long serialVersionUID = 6064711473148297435L;

	@Schema(name = "组织编码")
	private String code;

	@Schema(name = "组织级别")
	private String levelCode;

	@Schema(name = "经销商编码")
	private String dealerCode;

	@Schema(name = "组织名称")
	private String name;

	public OrgDealerDto(String code, String levelCode) {
		this.code = code;
		this.levelCode = levelCode;
	}
}

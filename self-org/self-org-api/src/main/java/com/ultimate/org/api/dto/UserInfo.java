package com.ultimate.org.api.dto;

import com.ultimate.org.api.entity.Dealer;
import com.ultimate.org.api.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author sfa
 * @date 2017/11/11
 */
@Data
@Schema(description = "用户信息")
@ToString
public class UserInfo implements Serializable {

	/**
	 * 用户基本信息
	 */
	@Schema(description = "用户基本信息")
	private User user;

	/**
	 * 权限标识集合
	 */
	@Schema(description = "权限标识集合")
	private String[] permissions;

	/**
	 * 角色集合
	 */
	@Schema(description = "角色标识集合")
	private Long[] roles;

	@Schema(description = "用户所属经销商")
	private List<Dealer> dealerList;

	@Schema(description = "用户所属经销商编码集合")
	private Set<String> dealerCodeList;

	@Schema(description = "用户所属组织集合")
	private List<OrgDealerDto> orgDealerList;

	private String orgCode;

	private String orgName;

	private String orgLevelCode;
}

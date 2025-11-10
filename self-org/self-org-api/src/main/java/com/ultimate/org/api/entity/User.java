package com.ultimate.org.api.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ultimate.self.common.mybatis.handler.CharToBooleanTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @description: TODO
 * @author kanwj
 * @date 2023年07月18日
 * @version: 1.0
 */
@Data
@TableName(value = "T_ORG_USER")
public class User implements Serializable {

	private static final long serialVersionUID = 8606378977233611397L;

	@Schema(description = "ID")
	@TableField("ID")
	private Long id;

	@Schema(description = "创建时间")
	@TableField("CREATE_DATE")
	private Date createDate;

	@Schema(description = "创建人")
	@TableField("CREATOR")
	private String creator;

	@Schema(description = "是否删除")
	@TableField(value = "IS_DELETED", typeHandler = CharToBooleanTypeHandler.class, jdbcType = JdbcType.CHAR)
	private Boolean isDeleted;

	@Schema(description = "修改时间")
	@TableField("LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Schema(description = "乐观锁")
	@TableField("LOCK_VERSION")
	private Long lockVersion;

	@Schema(description = "修改用户")
	@TableField("MODIFY_USER")
	private String modifyUser;

	@Schema(description = "出生日期")
	@TableField("BIRTHDAY")
	private Date birthday;

	@Schema(description = "身份证号码")
	@TableField("CERTIFICATE_NO")
	private String certificateNo;

	@Schema(description = "人员工号")
	@TableField("CODE")
	private String code;

	@Schema(description = "现居住地址")
	@TableField("CURRENT_LIVING_ADDRESS")
	private String currentLivingAddress;

	@Schema(description = "现居住地址邮编")
	@TableField("CURRENT_LIVING_ZIPCODE")
	private String currentLivingZipcode;

	@Schema(description = "人员部门")
	@TableField("DEPARTMENT_CODE")
	private String departmentCode;

	@Schema(description = "人员部门")
	@TableField("DEPARTMENT_NAME")
	private String departmentName;

	@Schema(description = "电子邮件")
	@TableField("EMAIL")
	private String email;

	@Schema(description = "组织生效的结束时间")
	@TableField("END_DATE")
	private String endDate;

	@Schema(description = "办公传真")
	@TableField("FAX")
	private String fax;

	@Schema(description = "性别")
	@TableField("GENDER")
	private String gender;

	@Schema(description = "籍贯")
	@TableField("HOMETOWN")
	private String hometown;

	@Schema(description = "是否启用")
	@TableField(value = "IS_ENABLED",typeHandler = CharToBooleanTypeHandler.class, jdbcType = JdbcType.CHAR)
	private Boolean isEnabled;

	@Schema(description = "是否开通账号")
	@TableField(value = "IS_OPEN",typeHandler = CharToBooleanTypeHandler.class, jdbcType = JdbcType.CHAR)
	private Boolean isOpen;

	@Schema(description = "备用联系人")
	@TableField("LINK_MAN")
	private String linkMan;

	@Schema(description = "描述")
	@TableField("MEMO")
	private String memo;

	@Schema(description = "手机号码")
	@TableField("MOBILE")
	private String mobile;

	@Schema(description = "MSN账号")
	@TableField("MSN")
	private String msn;

	@Schema(description = "岗位产品范围编码")
	@TableField("MT_RANGE")
	private String mtRange;

	@Schema(description = "人员姓名")
	@TableField("NAME")
	private String name;

	@Schema(description = "民族")
	@TableField("NATION")
	private String nation;

	@Schema(description = "组织系统编码")
	@TableField("ORG_CODE")
	private String orgCode;

	@Schema(description = "组织系统名称")
	@TableField("ORG_NAME")
	private String orgName;

	@Schema(description = "组织系统编码")
	@TableField("ORG_ORG_CODE")
	private String orgOrgCode;

	@Schema(description = "登录密码")
	@TableField("PASSWORD")
	private String password;

	@Schema(description = "办公室电话")
	@TableField("PHONE")
	private String phone;

	@Schema(description = "人员职位")
	@TableField("POSITION_CODE")
	private String positionCode;

	@Schema(description = "人员职位")
	@TableField("POSITION_NAME")
	private String positionName;

	@Schema(description = "qq账号")
	@TableField("QQ")
	private String qq;

	@Schema(description = "组织生效的开始时间")
	@TableField("START_DATE")
	private Date startDate;

	@Schema(description = "用户名")
	@TableField("USERNAME")
	private String username;

	@Schema(description = "工作驻地")
	@TableField("WORK_STATION")
	private String workStation;

	@Schema(description = "加入公司时间")
	@TableField("ATTEND_GROUP_DATE")
	private Date attendGroupDate;

	@Schema(description = "销售工作开始时间")
	@TableField("SALES_WORK_START_DATE")
	private Date salesWorkStartDate;

	@Schema(description = "历史部门")
	@TableField("OLD_DEPARTMENT_CODE")
	private String oldDepartmentCode;

	@Schema(description = "当前学历")
	@TableField("CURRENT_EDUCATION_LEVEL")
	private String currentEducationLevel;

	@Schema(description = "当前专业")
	@TableField("CURRENT_MAJOR")
	private String currentMajor;

	@Schema(description = "当前毕业学校")
	@TableField("CURRENT_SCHOOL")
	private String currentSchool;

	@Schema(description = "工作前学历")
	@TableField("FIRST_EDUCATION_LEVEL")
	private String firstEducationLevel;

	@Schema(description = "离职时间")
	@TableField("LEAVE_GROUP_DATE")
	private Date leaveGroupDate;

	@Schema(description = "工作前专业")
	@TableField("MAJOR")
	private String major;

	@Schema(description = "关联员工号")
	@TableField("RELATION_CODE")
	private String relationCode;

	@Schema(description = "工作前毕业学校")
	@TableField("SCHOOL")
	private String school;

	@Schema(description = "人员级别")
	@TableField("USER_RANK")
	private String userRank;

	@Schema(description = "用工性质")
	@TableField("WORK_TYPE")
	private String workType;

	@Schema(description = "离职手续办理时间")
	@TableField("LEAVE_PROCEDURE_DATE")
	private String leaveProcedureDate;

	@Schema(description = "婚姻状况")
	@TableField("MARITAL_STATUS")
	private String maritalStatus;

	@Schema(description = "序列号")
	@TableField("SEQ_NUM")
	private String seqNum;

	@Schema(description = "app登录设备号")
	@TableField("IMEI")
	private String imei;

	@Schema(description = "文件id")
	@TableField("FILE_ID")
	private Long fileId;

	@Schema(description = "民族分组")
	@TableField("NATION_CODE")
	private String nationCode;

	@Schema(description = "工资等级")
	@TableField("SLGRP")
	private String slgrp;

	@Schema(description = "盐值")
	@TableField("SALT")
	private String salt;

	@Schema(description = "上级组织编码")
	@TableField("PARENT_ORG_CODE")
	private String parentOrgCode;

	@Schema(description = "上级组织名称")
	@TableField("PARENT_ORG_NAME")
	private String parentOrgName;

	@Schema(description = "是否在职")
	@TableField(value = "IS_ON_JOB",typeHandler = CharToBooleanTypeHandler.class, jdbcType = JdbcType.CHAR)
	private Boolean isOnJob;

	@Schema(description = "修改用户")
	@TableField("MODIFY_USER")
	private String modifyUse;

}

package com.ultimate.org.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName Dealer
 * @description: TODO
 * @author kanwj
 * @date 2023年07月18日
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "经销商基础信息表")
@TableName("T_ORG_DEALER")
@NoArgsConstructor
public class Dealer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID")
    private Long id;

    @TableField(value = "CREATOR")
    private String creator;

    @TableField(value = "CREATE_DATE")
    private LocalDateTime createDate;

    @TableField(value = "MODIFY_USER")
    private String modifyUser;

    @TableField(value = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    @TableField(value = "IS_DELETED")
    private String isDeleted;

    @Schema(name = "纳爱斯对经销商编码 (所有的同步及关联都用NICECODE做关联)")
    @TableField("CODE")
    private String code;

    @Schema(name = "经销商全称")
    @TableField("NAME")
    private String name;

    @Schema(name = "经销商简称")
    @TableField("SHORT_NAME")
    private String shortName;

    @Schema(name = "经销商类别")
    @TableField("DEALER_CATAGORY")
    private String dealerCatagory;

    @Schema(name = "父级经销商编码")
    @TableField("PARENT_CODE")
    private String parentCode;

    @Schema(name = "父级经销商名称")
    @TableField("PARENT_NAME")
    private String parentName;

    @Schema(name = "分公司编码")
    @TableField("BRANCH_CODE")
    private String branchCode;

    @Schema(name = "分公司名称")
    @TableField("BRANCH_NAME")
    private String branchName;

    @Schema(name = "大区编码")
    @TableField("SALES_AREA")
    private String salesArea;

    @Schema(name = "经销商类型")
    @TableField("DEALER_TYPE")
    private String dealerType;

    @Schema(name = "最小送货吨位")
    @TableField("LEAST_WEIGHT")
    private Integer leastWeight;

    @Schema(name = "是否可供为供应商")
    @TableField("IS_SUPPLIER")
    private Boolean isSuppiler;

    @Schema(name = "是否分公司经销商")
    @TableField("IS_BRANCH_DEALER")
    private Boolean isBranchDealer = Boolean.FALSE;

    @Schema(name = "全球位置编码")
    @TableField("GLN_CODE")
    private String glnCode;

    @Schema(name = "行政区域编码")
    @TableField("AREA_CODE")
    private String areaCode;

    @Schema(name = "详细地址")
    @TableField("STREET_ADDR")
    private String streetAddr;

    @Schema(name = "邮政编码")
    @TableField("ZIPCODE")
    private String zipcode;

    @Schema(name = "联系人")
    @TableField("CONTACT_PERSON")
    private String contactPerson;

    @Schema(name = "固定电话")
    @TableField("PHONE")
    private String phone;

    @Schema(name = "电话区号")
    @TableField("PHONE_AREA_CODE")
    private String phoneAreaCode;

    @Schema(name = "移动电话")
    @TableField("HANDSET")
    private String handset;

    @Schema(name = "法人代表")
    @TableField("LEGAL_PERSON")
    private String legalPerson;

    @Schema(name = "法人联系电话")
    @TableField("LEGAL_TEL")
    private String legalTel;

    @Schema(name = "客户经理编码")
    @TableField("MANAGER_CODE")
    private String managerCode;

    @Schema(name = "客户经理名称")
    @TableField("MANAGER_NAME")
    private String managerName;

    @Schema(name = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @Schema(name = "标注编码")
    @TableField("BZCODE")
    private String bzcode;

    @Schema(name = "是否承运商")
    @TableField("IS_SELF_SUPPLIER")
    private Boolean isSelfSupplier;

    @Schema(name = "是够开通经销商系统账号")
    @TableField("IS_OPEN")
    private Boolean isOpen = false;

    @Schema(name = "是够开通移动商务系统账号")
    @TableField("IS_NSFA")
    private Boolean isNsfa = false;

    @Schema(name = "是否开通财务接口")
    @TableField("IS_OPEN_FINANCIAL")
    private Boolean isOpenFinancial = false;

    @Schema(name = "是否纳爱斯报表接口")
    @TableField("IS_OPEN_NDLS_REPORT")
    private Boolean isOpenNdlsReport = true;

    @Schema(name = "是否开通信用贷款")
    @TableField("IS_OPEN_NSCF")
    private Boolean isOpenNSCF = false;

    @Schema(name = "信用贷款对应放款银行")
    @TableField("NSFC_BANK")
    private String nsfcBank;

    @Schema(name = "邮编")
    @TableField("POSTL_COD1")
    private String postlCod1;

    @Schema(name = "国家")
    @TableField("COUNTRY")
    private String country;

    @Schema(name = "省（地区（省、市、县））")
    @TableField("PROVINCE")
    private String province;

    @Schema(name = "省（地区（省、市、县））")
    @TableField("PROVINCE_ID")
    private String provinceId;

    @Schema(name = "市（城市）")
    @TableField("CITY")
    private String city;

    @Schema(name = "市（城市）")
    @TableField("CITY_ID")
    private String cityId;

    @Schema(name = "县（区域）")
    @TableField("COUNTRY")
    private String county;

    @Schema(name = "县（区域")
    @TableField("COUNTY_ID")
    private String countyId;

    @Schema(name = "镇")
    @TableField("TOWN")
    private String town;

    @Schema(name = "镇")
    @TableField("TOWN_ID")
    private String townId;

    @Schema(name = "传真")
    @TableField("FAX")
    private String fax;

    @Schema(name = "电子邮箱")
    @TableField("SMTP")
    private String smtp;

    @Schema(name = "属性1")
    @TableField("ATTRIBUTE")
    private String attribute;

    @Schema(name = "Nielsen标识")
    @TableField("NIELSEN_ID")
    private String nielsenId;

    @Schema(name = "属性10")
    @TableField("ATTRIB10")
    private String attrib10;

    @Schema(name = "是否冻结")
    @TableField("IS_FROZEN")
    private Boolean isFrozen = false;

    @Schema(name = "市场竞争状态")
    @TableField("MARKET_STATUS")
    private String marketStatus;

    /*@Schema(name = "市场竞争状态")
    @Transient
    private String marketStatusName;

    @Schema(name = "经销商类型名称，前端展示用")
    @Transient
    private String dealerTypeName;

    @Schema(name = "银行名称")
    @Transient
    private String bankName;

    @Schema(name = "结算单位编码")
    @Transient
    private String billToCode;

    @Schema(name = "是否开通信贷银行")
    @Transient
    private Boolean isBank;

    @Schema(name = "价格套code")
    @Transient
    private String seriesCode;

    @Schema(name = "价格套")
    @Transient
    private String seriesCodeName;*/

}

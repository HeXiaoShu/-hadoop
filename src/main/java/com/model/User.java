package com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * User
 * @author xiaoshu
 * @date 2021-05-01 21:05:04
 */
@ApiModel(value = "User-用户")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 手机-账号 */
    @ApiModelProperty(value = "手机-账号",required = true)
    private String phone;

    /** 密码 */
    @ApiModelProperty(value = "密码",required = true)
    private String pwd;

    /** 用户名 */
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    /** 头像路径 */
    @ApiModelProperty(value = "头像路径")
    private String headImg;

    /** 用户状态 1正常 0注销 */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;



}
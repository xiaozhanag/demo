package com.example.demo.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @description:
 * @author: zrs
 * @createDate: 2023/2/17 11:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tb_user")
public class Stu {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id", name = "id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "角色Id", name = "roleId", example = "1")
    private Integer roleId;

    @ApiModelProperty(value = "用户名称", name = "userName", example = "1")
    private String userName;

    @ApiModelProperty(value = "登录名", name = "loginName", example = "1")
    private String loginName;

    @ApiModelProperty(value = "密码", name = "password", example = "1")
    private String password;

    @ApiModelProperty(value = "私钥", name = "secret", example = "1")
    private String secret;

    @ApiModelProperty(value = "角色编号", name = "roleCode", example = "1")
    private String roleCode;

    @ApiModelProperty(value = "手机号", name = "mobile", example = "1")
    private String mobile;

    @ApiModelProperty(value = "所属区域Id", name = "regionId", example = "1")
    private Integer regionId;

    @ApiModelProperty(value = "区域名称", name = "regionName", example = "1")
    private String regionName;

    @ApiModelProperty(value = "是否启用", name = "status", example = "1")
    private Integer status;

    @ApiModelProperty(value = "图片", name = "image", example = "1")
    private String image;


}

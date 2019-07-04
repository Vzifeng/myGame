package com.example.game.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 15:15 2019/6/14
 * @Version ： $version$
 */
@Data
public class RegisterVo {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "性别不能为空")
    private Integer userSex;
    @NotBlank(message = "电话号码不能为空")
    private String userPhone;
    @NotBlank(message = "地址不能为空")
    private String userAddr;

    private String userRegistrationTime;
    @NotBlank(message = "密码不能为空")
    private String password;

    private Integer userId;
    @NotBlank(message = "角色信息不能为空")
    private String roleId;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String roleName;

    private String gameName;



}

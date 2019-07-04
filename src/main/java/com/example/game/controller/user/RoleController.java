package com.example.game.controller.user;

import com.example.game.common.Pager;
import com.example.game.controller.BaseController;
import com.example.game.po.Role;
import com.example.game.response.CommonResponse;
import com.example.game.service.RoleService;
import com.example.game.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 17:23 2019/6/17
 * @Version ： $version$
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;

    //新增角色
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse addRole(@RequestBody Role role){
        Integer num = roleService.addRole(role);
        return CommonResponse.create(num);
    }
    //启用停用
    @RequestMapping(value = "/disableOrNoEnable",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse disableOrNoEnable(@RequestBody Role role){
        Integer num = roleService.disableOrNoEnable(role);
        return CommonResponse.create(num);
    }
    //角色列表
    @RequestMapping(value = "/roleList",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse roleList(@RequestBody RoleVo roleVo){
        Pager pager = roleService.roleList(roleVo);
        return CommonResponse.create(pager);
    }
}

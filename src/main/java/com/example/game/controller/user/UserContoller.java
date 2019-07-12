package com.example.game.controller.user;

import com.example.game.common.Pager;
import com.example.game.controller.BaseController;
import com.example.game.error.BusnessException;
import com.example.game.po.User;
import com.example.game.response.CommonResponse;
import com.example.game.service.UserService;
import com.example.game.vo.RegisterVo;
import com.example.game.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 11:10 2019/2/19
 * @Version ： $version$
 */
@Controller
@RequestMapping(value = "/user")
public class UserContoller extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserContoller.class);

    @Autowired
    UserService userService;

    //用户注册
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse userRegister(@RequestBody RegisterVo registerVo) throws BusnessException {
        userService.userRegister(registerVo);
        return CommonResponse.create("注册成功");
    }

    //用户登陆
    @RequestMapping(value = "/logIn",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse logIn(@RequestBody RegisterVo registerVo) throws BusnessException {
        userService.logIn(registerVo);
        return CommonResponse.create("登录成功");
    }

    //用户注销


    //用户查询--列表
    @RequestMapping(value = "/userList",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse userList(@RequestBody(required = false) RegisterVo registerVo){
        Pager pager = null;
        try {
            pager = userService.userList(registerVo);
        } catch (Exception e) {
            LOGGER.error("查询用户列表失败",e);
        }
        return CommonResponse.create(pager);
    }

    //用户查询--id
    @RequestMapping(value = "/selectUserById",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse selectUserById(Integer userId){
        UserVo userVo = null;
        try {
            userVo = userService.selectUserById(userId);
        } catch (Exception e) {
            LOGGER.error("获取用户失败",e);
        }
        return CommonResponse.create(userVo);
    }
    //修改密码--id


    //给用户添加游戏
    @RequestMapping(value = "/addGameforUser",method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse addGameforUser(@RequestParam String gameIds){
        Integer num = null;
        try {
            num = userService.addGameforUser(gameIds);
        } catch (Exception e) {
            LOGGER.error("新增游戏失败",e);
        }
        return CommonResponse.create(num);
    }

}

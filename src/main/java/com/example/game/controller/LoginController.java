package com.example.game.controller;

import com.example.game.error.BusinessErrorEnum;
import com.example.game.error.BusnessException;
import com.example.game.po.User;
import com.example.game.response.CommonResponse;
import com.example.game.utils.SessionUtil;
import com.example.game.vo.MyVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 15:11 2019/6/27/0027
 * @Version ： $version$
 */
@Controller
public class LoginController {

    @RequestMapping("/getlogin")
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/testThymeleaf",method = RequestMethod.GET)
    public String testThymeleaf(Model model){
        model.addAttribute("haha","shiro_test");
        return "test";
    }
    @RequestMapping("/exit")
    public String exit(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login";
    }

    @RequestMapping(value = "/add")
    public String add(){
        SessionUtil.getUser();
        return "/user/ad";
    }

    @RequestMapping(value = "/update")
    public String update(){
        SessionUtil.getUser();
        return "/user/ad";
    }

    @RequestMapping(value = "/unauthor")
    public String unauthor(){
        return "/user/unauthor";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest httpServletRequest,
                                @RequestBody Map<String,Object> param,Model model) throws BusnessException {
        /**
         * 使用shrio编写认证操作
         */
        //获取subject
        String name = (String) param.get("name");
        String password = (String) param.get("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        //String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        //String parameter = httpServletRequest.getParameter("vrifyCode");
       /* if(!captchaId.equals(parameter)){
            model.addAttribute("info","验证码不正确");
            return "login";
        }else {*/
            //执行登陆方法
            try {
                subject.login(token);
                //登陆成功
                return "redirect:/testThymeleaf";
            } catch (UnknownAccountException e) {
                //用户名不存在
                model.addAttribute("msg","用户名不存在");
                return "login";
            }catch (IncorrectCredentialsException e){
                //密码错误
                model.addAttribute("msg","密码错误");
                return "login";
            }
       /* }*/
    }

}

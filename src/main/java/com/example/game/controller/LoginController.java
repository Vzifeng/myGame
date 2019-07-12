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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/testThymeleaf")
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
                                String name,String password,RedirectAttributes redirectAttributes) throws BusnessException {
        /**
         * 使用shrio编写认证操作
         */
        //获取subject
       /* String name = (String) param.get("name");
        String password = (String) param.get("password");*/
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
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("msg","用户名或者密码错误");
                return "redirect:/getlogin";
            }
       /* }*/
    }

    public static void main(String[] args) {
        Map<String,Object> map1 = new HashMap <>();
        Map<String,Object> map2 = new HashMap <>();
        map1.put("first","第一");
        map1.put("secon","第二");
        map2.put("third","第三");
        map2.put("third","第四");
        List<String> ids = new ArrayList <>();
        for (String str : ids){
            System.out.println(str+"sssssssssssssssss");
        }

        ArrayList<String> listA= new ArrayList<String>();
        listA.add("Tom");
        ArrayList<String> listB= new ArrayList<String>();
        listB.add("Tom");
        //若listA和listB中有相同元素，则listA中保留相同元素，反之listA为空
        listA.retainAll(listB);
        if(listA.size()>0){
            System.out.println("这两个集合有相同的交集");
        }else{
            System.out.println("这两个集合没有相同的交集");
        }

        map2.putAll(map1);
        for (Map.Entry<String,Object> entry : map2.entrySet()){
            System.out.println("key:"+entry.getKey()+" and value:"+entry.getValue());
        }

    }

}

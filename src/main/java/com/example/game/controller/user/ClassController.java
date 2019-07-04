package com.example.game.controller.user;

import com.example.game.po.Class;
import com.example.game.response.CommonResponse;
import com.example.game.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 16:40 2019/6/21/0021
 * @Version ： $version$
 */
@Controller
public class ClassController {

    @Autowired
    ClassService classService;
    @RequestMapping(value = "/class")
    @ResponseBody
    public CommonResponse classList(){
        List<Class> list = classService.classList();
        return CommonResponse.create(list);
    }
}

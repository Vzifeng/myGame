package com.example.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 9:57 2019/6/21/0021
 * @Version ： $version$
 */
@Controller
@RequestMapping(value = "/html")
public class HtmlController {

    @RequestMapping(value = "/test")
    public String testHtml(Model model, String name){
        model.addAttribute("name",name);
        return "test";
    }
}

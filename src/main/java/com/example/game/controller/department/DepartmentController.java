package com.example.game.controller.department;

import com.example.game.controller.BaseController;
import com.example.game.response.CommonResponse;
import com.example.game.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 9:41 2019/11/28
 * @Version ： 1.0
 */
@RestController
@RequestMapping("/departmentController")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询公司部门组织树
     * @return
     */
    @PostMapping("/departmentTree")
    public CommonResponse departmentTree(){
        List<Map<String,Object>> list = departmentService.departmentTree();
        return CommonResponse.create(list);
    }
}

package com.example.game.controller.material;

import com.example.game.common.Pager;
import com.example.game.controller.BaseController;
import com.example.game.error.BusnessException;
import com.example.game.po.GameMaterial;
import com.example.game.response.CommonResponse;
import com.example.game.service.MaterialService;
import com.example.game.vo.MaterialVo;
import com.example.game.vo.response.ResponseMaterialVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 11:30 2019/2/19
 * @Version ： $version$
 */
@Controller
@RequestMapping(value = "/material")
public class MaterialController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    MaterialService materialService;

    //新增材料
    @RequestMapping(value = "/addMaterial",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse addMaterial(@RequestBody MaterialVo materialVo){
        Integer num = null;
        try {
            num = materialService.addMaterial(materialVo);
        } catch (BusnessException e) {
            LOGGER.error("新增材料失败",e);
        }
        return CommonResponse.create(num);
    }

    //修改材料
    @RequestMapping(value = "/updateMaterial")
    @ResponseBody
    public CommonResponse updateMaterial(@RequestBody GameMaterial gameMaterial){
        Integer num = null;
        try {
            num = materialService.updateMaterial(gameMaterial);
        } catch (Exception e) {
            LOGGER.error("修改材料信息失败",e);
        }
        return CommonResponse.create(num);
    }
    //删除材料
    @RequestMapping(value = "/deleteMaterial")
    @ResponseBody
    public CommonResponse deleteMaterial(@RequestParam String materialIds){
        Integer num = null;
        try {
            num = materialService.deleteMaterial(materialIds);
        } catch (Exception e) {
            LOGGER.error("删除材料信息失败",e);
        }
        return CommonResponse.create(num);
    }

    //材料列表
    @RequestMapping(value = "/materialList",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse materialList(@RequestBody MaterialVo materialVo){
        Pager pager = null;
        try {
            pager = materialService.materialList(materialVo);
        } catch (Exception e) {
            LOGGER.error("查询材料列表失败",e);
        }

        return CommonResponse.create(pager);
    }
    //材料详情
    @RequestMapping(value = "/detailMaterial",method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse detailMaterial(@RequestParam Integer materialId){
        ResponseMaterialVo result = null;
        try {
            result = materialService.detailMaterial(materialId);
        } catch (Exception e) {
            LOGGER.error("查询材料详情失败",e);
        }
        return CommonResponse.create(result);
    }
}

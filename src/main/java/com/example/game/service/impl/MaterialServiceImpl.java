package com.example.game.service.impl;

import com.example.game.common.Pager;
import com.example.game.dao.GameMaterialMapper;
import com.example.game.error.BusinessErrorEnum;
import com.example.game.error.BusnessException;
import com.example.game.po.Game;
import com.example.game.po.GameMaterial;
import com.example.game.service.MaterialService;
import com.example.game.validator.ValidationResult;
import com.example.game.validator.ValidatorImpl;
import com.example.game.vo.MaterialVo;
import com.example.game.vo.response.ResponseMaterialVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 10:59 2019/7/1/0001
 * @Version ： $version$
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MaterialServiceImpl.class);
    @Autowired
    GameMaterialMapper gameMaterialMapper;

    @Autowired
    ValidatorImpl validator;

    @Override
    @Transactional
    public Integer addMaterial(MaterialVo materialVo) throws BusnessException {
        if (materialVo == null){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"参数不能为空");
        }
        //参数校验
        ValidationResult result = null;
        try {
            result = validator.validate(materialVo);
        } catch (Exception e) {
            LOGGER.error("获取校验信息失败",e);
        }
        if (result.isHasErrors()){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
        }
        Integer num = gameMaterialMapper.addMaterial(materialVo);
        return num;
    }

    @Override
    @Transactional
    public Integer updateMaterial(GameMaterial gameMaterial) throws BusnessException {
        if (gameMaterial == null){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"参数不能为空");
        }
        if (gameMaterial.getId() == null){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"材料Id不能为空");
        }
        Integer num = gameMaterialMapper.updateByPrimaryKeySelective(gameMaterial);
        return num;
    }

    @Override
    @Transactional
    public Integer deleteMaterial(String materialIds) {
        String[] ids = materialIds.split(",",0);
        Map<String,Object> params = new HashMap <>();
        params.put("ids",ids);
        Integer num = gameMaterialMapper.deleteMaterial(ids);
        return num;
    }

    @Override
    public Pager materialList(MaterialVo materialVo) {
        Pager pager = new Pager();
        Integer curPage = materialVo.getCurPage();
        Integer pageSize = materialVo.getPageSize();
        Page<ResponseMaterialVo> page = PageHelper.startPage(curPage,pageSize);
        gameMaterialMapper.materialList(materialVo);
        List<ResponseMaterialVo> list = page.getResult();
        pager.setList(list);
        pager.setCurPage(curPage);
        pager.setPageSize(pageSize);
        pager.setTotalRow((int) page.getTotal());
        pager.setTotalPage(page.getPages());
        return pager;
    }

    @Override
    public ResponseMaterialVo detailMaterial(Integer materialId) {
        return gameMaterialMapper.detailMaterial(materialId);
    }
}

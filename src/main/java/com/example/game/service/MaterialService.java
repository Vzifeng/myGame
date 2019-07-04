package com.example.game.service;

import com.example.game.common.Pager;
import com.example.game.error.BusnessException;
import com.example.game.po.GameMaterial;
import com.example.game.vo.MaterialVo;
import com.example.game.vo.response.ResponseMaterialVo;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 10:59 2019/7/1/0001
 * @Version ： $version$
 */
public interface MaterialService {
    Integer addMaterial(MaterialVo materialVo) throws BusnessException;

    Integer updateMaterial(GameMaterial gameMaterial) throws BusnessException;

    Integer deleteMaterial(String materialIds);

    Pager materialList(MaterialVo materialVo);

    ResponseMaterialVo detailMaterial(Integer materialId);
}

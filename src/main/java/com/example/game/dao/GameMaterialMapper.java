package com.example.game.dao;

import com.example.game.po.GameMaterial;
import com.example.game.vo.MaterialVo;
import com.example.game.vo.response.ResponseMaterialVo;

import java.util.List;

public interface GameMaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameMaterial record);

    int insertSelective(GameMaterial record);

    GameMaterial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameMaterial record);

    int updateByPrimaryKey(GameMaterial record);

    Integer addMaterial(MaterialVo materialVo);

    Integer deleteMaterial(String[] ids);

    List<ResponseMaterialVo> materialList(MaterialVo materialVo);

    ResponseMaterialVo detailMaterial(Integer materialId);
}
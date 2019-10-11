package com.example.game.dao;

import com.example.game.po.UserMaterial;
import org.apache.ibatis.annotations.Param;

public interface UserMaterialMapper {

    Integer deleteMaterial(@Param("ids") String[] ids);
}
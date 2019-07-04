package com.example.game.dao;

import com.example.game.po.UserGameKey;

public interface UserGameMapper {
    int deleteByPrimaryKey(UserGameKey key);

    int insert(UserGameKey record);

    int insertSelective(UserGameKey record);
}
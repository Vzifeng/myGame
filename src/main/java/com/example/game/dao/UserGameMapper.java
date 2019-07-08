package com.example.game.dao;

import com.example.game.po.UserGameKey;

import java.util.List;

public interface UserGameMapper {
    int deleteByPrimaryKey(UserGameKey key);

    int insert(UserGameKey record);

    int insertSelective(UserGameKey record);

    List<UserGameKey> selectByGameId(Integer gameId);
}
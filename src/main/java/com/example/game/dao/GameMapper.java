package com.example.game.dao;

import com.example.game.po.Game;
import com.example.game.vo.GameVo;
import com.example.game.vo.response.ResponseGameVo;

import java.util.List;
import java.util.Map;

public interface GameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Game record);

    int insertSelective(Game record);

    Game selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);

    List<Game> gameListPage(GameVo gameVo);

    ResponseGameVo singlGame(Integer id);

    List <ResponseGameVo> selectGameByUserId(Integer userId);

    List<Map<String,Object>> testDataSource();

    List<Game> testDataSource2();
}
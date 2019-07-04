package com.example.game.service;

import com.example.game.common.Pager;
import com.example.game.error.BusnessException;
import com.example.game.vo.GameVo;
import com.example.game.vo.response.ResponseGameVo;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 9:16 2019/6/21/0021
 * @Version ： $version$
 */
public interface GameService {
    int addNewGame(GameVo gameVo) throws BusnessException;

    Pager gameListPage(GameVo gameVo);

    ResponseGameVo singlGame(Integer id) throws BusnessException;
}

package com.example.game.service.impl;

import com.example.game.common.Pager;
import com.example.game.dao.GameMapper;
import com.example.game.error.BusinessErrorEnum;
import com.example.game.error.BusnessException;
import com.example.game.po.Game;
import com.example.game.service.GameService;
import com.example.game.validator.ValidationResult;
import com.example.game.validator.ValidatorImpl;
import com.example.game.vo.GameVo;
import com.example.game.vo.response.ResponseGameVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 9:17 2019/6/21/0021
 * @Version ： $version$
 */
@Service
public class GameServiceImpl implements GameService {

    public static  final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    GameMapper gameMapper;

    @Autowired
    ValidatorImpl validator;
    @Override
    @Transactional
    public int addNewGame(GameVo gameVo) throws BusnessException {
        ValidationResult result = null;
        try {
            result = validator.validate(gameVo);
        } catch (Exception e) {
            LOGGER.error("获取校验信息失败");
        }
        if (result.isHasErrors()){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
        }
        Game game = new Game();
        BeanUtils.copyProperties(gameVo,game);
        Integer num = gameMapper.insert(game);
        return num;
    }

    @Override
    public Pager gameListPage(GameVo gameVo) {
        Pager pager = new Pager();
        Page page = PageHelper.startPage(gameVo.getCurPage(),gameVo.getPageSize());
        try {
            gameMapper.gameListPage(gameVo);
        } catch (Exception e) {
            LOGGER.error("获取游戏列表失败",e);
        }
        pager.setCurPage(gameVo.getCurPage());
        pager.setPageSize(gameVo.getPageSize());
        pager.setTotalPage(page.getPages());
        pager.setTotalRow((int) page.getTotal());
        pager.setList(page.getResult());
        return pager;
    }

    @Override
    public ResponseGameVo singlGame(Integer id) throws BusnessException {
        if (id == null || id < 1){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"参数不合法");
        }
        ResponseGameVo responseGameVo = null;
        try {
            responseGameVo = gameMapper.singlGame(id);
        } catch (Exception e) {
            LOGGER.error("查询单款游戏信息失败",e);
        }
        return responseGameVo;
    }
}

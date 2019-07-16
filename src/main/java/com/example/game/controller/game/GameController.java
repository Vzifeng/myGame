package com.example.game.controller.game;

import com.example.game.common.Pager;
import com.example.game.controller.BaseController;
import com.example.game.error.BusnessException;
import com.example.game.response.CommonResponse;
import com.example.game.service.GameService;
import com.example.game.vo.GameVo;
import com.example.game.vo.response.ResponseGameVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 11:27 2019/2/19
 * @Version ： $version$
 */
@Controller
@RequestMapping(value = "/game")
public class GameController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    GameService gameService;

    //添加新游戏
    @RequestMapping(value = "/addNewGame",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse addNewGame(@RequestParam GameVo gameVo) throws BusnessException {
        Integer num = gameService.addNewGame(gameVo);
        return CommonResponse.create(num);
    }
    //查询游戏列表
    @RequestMapping(value = "/gameListPage",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse gameListPage(@RequestBody GameVo gameVo){
        Pager pager = null;
        try {
            pager = gameService.gameListPage(gameVo);
        } catch (Exception e) {
            LOGGER.error("错误",e);
        }
        return CommonResponse.create(pager);
    }
    //查询单个游戏
    @RequestMapping(value = "/singlGame",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse singlGame(@RequestParam Integer id) throws BusnessException {
        ResponseGameVo responseGameVo = gameService.singlGame(id);
        return CommonResponse.create(responseGameVo);
    }

    @RequestMapping(value = "/deleteGameById",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse deleteGameById(@RequestParam(value = "ids") String ids) throws BusnessException {
        Map<String, Object>  map = gameService.deleteGameById(ids);
        return CommonResponse.create(map);
    }

    @RequestMapping(value = "/testDataSource",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse testDataSource(){
        return CommonResponse.create(gameService.testDataSource());
    }

    @RequestMapping(value = "/testDataSource2",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse testDataSource2(){
        return CommonResponse.create(gameService.testDataSource2());
    }


}

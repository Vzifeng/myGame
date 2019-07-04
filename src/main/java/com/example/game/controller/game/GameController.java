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
    public CommonResponse gameListPage(@RequestParam(required = false) String[] parameter,
                                       @RequestParam(required = true) Integer curPage,
                                       @RequestParam(required = true) Integer pageSiz){
        /*@RequestParam(required = false) String[] parameter,
    @RequestParam(required = true) Integer curPage,
    @RequestParam(required = true) Integer pageSize*/
        GameVo gameVo = new GameVo();
        Pager pager = gameService.gameListPage(gameVo);
        return CommonResponse.create(pager);
    }
    //查询单个游戏
    @RequestMapping(value = "/singlGame",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse singlGame(@RequestParam Integer id) throws BusnessException {
        ResponseGameVo responseGameVo = gameService.singlGame(id);
        return CommonResponse.create(responseGameVo);
    }

    //这是分支提交测试


}

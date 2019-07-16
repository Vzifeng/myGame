package com.example.game.service.impl;

import com.example.game.common.Pager;
import com.example.game.dao.GameMapper;
import com.example.game.dao.GameMaterialMapper;
import com.example.game.dao.UserGameMapper;
import com.example.game.dataSource.DataSource;
import com.example.game.dataSource.DataSourceType;
import com.example.game.error.BusinessErrorEnum;
import com.example.game.error.BusnessException;
import com.example.game.po.Game;
import com.example.game.po.UserGameKey;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    GameMaterialMapper gameMaterialMapper;

    @Autowired
    UserGameMapper userGameMapper;

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

    @Override
    @Transactional
    public Map<String, Object> deleteGameById(String ids) throws BusnessException {
        String [] id = ids.split(",",0);
        Integer num = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Object> map = new HashMap <>();
        for (int i=0;i<id.length;i++){
            List<UserGameKey> list = userGameMapper.selectByGameId(Integer.valueOf(id[i]));
            if (list.size()>0){
                try {
                    stringBuffer.append("{游戏id:"+id[i]+",该游戏还有用户,无法删除}"+",");
                } catch (Exception e) {
                    LOGGER.error("失败",e);
                }
                continue;
            }
            try {
                gameMapper.deleteByPrimaryKey(Integer.valueOf(id[i]));
                gameMaterialMapper.deleteMaterialByGameId(Integer.valueOf(id[i]));

            } catch (NumberFormatException e) {
                LOGGER.error("删除游戏失败",e);
            }
            num++;
        }
        map.put("deleteNum",num);
        map.put("errorMsg",stringBuffer.substring(0,stringBuffer.length()-1));
        return map;
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Map<String,Object>> testDataSource() {
        List<Map<String,Object>> list = null;
        try {
            list = gameMapper.testDataSource();
        } catch (Exception e) {
            LOGGER.error("错误",e);
        }
        for (Map<String,Object> map : list){
            for (Map.Entry<String,Object> entry : map.entrySet()){
                System.out.println(entry.getKey()+"---------"+entry.getValue());
            }
        }
        return list;
    }

    @Override
    @DataSource(value = DataSourceType.MASTER)
    public List<Game> testDataSource2() {
        List<Game> list = null;
        try {
            list = gameMapper.testDataSource2();
        } catch (Exception e) {
            LOGGER.error("错误",e);
        }
        for (Game game : list){
            System.out.println(game.toString());
        }
        return list;
    }
}

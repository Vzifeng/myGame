package com.example.game.service.impl;

import com.example.game.common.Pager;
import com.example.game.dao.*;
import com.example.game.error.BusinessErrorEnum;
import com.example.game.error.BusnessException;
import com.example.game.po.*;
import com.example.game.service.UserService;
import com.example.game.utils.DateUtil;
import com.example.game.utils.EncodeByMd5Util;
import com.example.game.utils.RegulaExpression;
import com.example.game.utils.SessionUtil;
import com.example.game.validator.ValidationResult;
import com.example.game.validator.ValidatorImpl;
import com.example.game.vo.RegisterVo;
import com.example.game.vo.UserVo;
import com.example.game.vo.response.ResponseGameVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 16:57 2019/4/18
 * @Version ： $version$
 */
@Service
public class UserServiceImpl implements UserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = true)
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserPasswordMapper userPasswordMapper;

    @Autowired
    GameMapper gameMapper;

    @Autowired
    UserGameMapper userGameMapper;

    @Autowired
    ValidatorImpl validator;

    @Override
    //@Cacheable(cacheNames = "user")
    public UserVo selectUserById(Integer userId) throws BusnessException {
        if (userId == null || userId < 1){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"参数不合法");
        }
        User user = userMapper.selectByPrimaryKey(userId);
        List<ResponseGameVo> responseGameVoList = gameMapper.selectGameByUserId(userId);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setResponseGameVoList(responseGameVoList);
        return userVo;
    }

    @Override
    @Transactional
    public void userRegister(RegisterVo registerVo) throws BusnessException {
        if (registerVo == null){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"参数不能为空");
        }
        ValidationResult result = null;
        try {
            result = validator.validate(registerVo);
        } catch (Exception e) {
            LOGGER.error("获取校验信息失败",e);
        }
        if (result.isHasErrors()){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
        }
        boolean b = RegulaExpression.isPhoneNum(registerVo.getUserPhone());
        if (!b){
            throw new BusnessException(BusinessErrorEnum.IS_NOT_PHONE_NUM,"不是正确的手机号");
        }
        registerVo.setUserRegistrationTime(DateUtil.dateToString1(new Date()));
        User user = new User();
        BeanUtils.copyProperties(registerVo,user);
        userMapper.insert(user);

        String roles = registerVo.getRoleId();
        String[] roleIds = roles.split(",",0);
        for(int i = 0; i<roleIds.length;i++){
            UserRoleKey userRole = new UserRoleKey();
            userRole.setRoleId(Integer.valueOf(roleIds[i]));
            userRole.setUserId(user.getId());
            userRoleMapper.insert(userRole);
        }

        UserPassword userPassword = new UserPassword();
        userPassword.setPassword(EncodeByMd5Util.encodeByMd5(registerVo.getPassword()));
        userPassword.setUserId(user.getId());
        userPasswordMapper.insert(userPassword);


    }

    @Override
    public void logIn(RegisterVo registerVo) throws BusnessException {
        if (registerVo == null){
            throw new BusnessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"参数不能为空");
        }
        ValidationResult result = null;
        try {
            result = validator.validate(registerVo);
        } catch (Exception e) {
            LOGGER.error("获取校验信息失败",e);
        }
        boolean b = RegulaExpression.isPhoneNum(registerVo.getUserPhone());
        if (!b){
            throw new BusnessException(BusinessErrorEnum.IS_NOT_PHONE_NUM,"不是正确的手机号");
        }
        //通过手机号查询
        RegisterVo user = userMapper.selectUserByPhone(registerVo.getUserPhone());
        if (user == null){
            throw new BusnessException(BusinessErrorEnum.USER_NOT_EXIST,"该手机号未注册");
        }
        String password = EncodeByMd5Util.encodeByMd5(registerVo.getPassword());
        if(!user.getPassword().equals(password)){
            throw new BusnessException(BusinessErrorEnum.USER_OR_PASSWORD_ERROR,"手机号或者密码错误");
        }
    }

    @Override
    public Pager userList(RegisterVo registerVo) {
        Integer curPage = registerVo.getPageNum();
        Integer pageSize = registerVo.getPageSize();
        Pager pager = new Pager();
        Page<UserVo> page = PageHelper.startPage(curPage,pageSize);
        userMapper.userList(registerVo);
        pager.setCurPage(registerVo.getPageNum());
        pager.setPageSize(registerVo.getPageSize());
        pager.setTotalPage(page.getPages());
        pager.setTotalRow((int) page.getTotal());
        pager.setList(page.getResult());
        return pager;
    }

    @Override
    public User selectUserByUserName(String userName) {
        User user = userMapper.selectUserByUserName(userName);
        return user;
    }

    @Override
    public UserPassword getPasswordByUserId(Integer id) {
        UserPassword userPassword = userPasswordMapper.getPasswordByUserId(id);
        return userPassword;
    }

    @Override
    @Transactional
    public Integer addGameforUser(String gameIds) throws BusnessException {
        String [] ids = gameIds.split(",",0);
        Integer num = 0;
        for (int i=0;i<ids.length;i++){
            Game game = gameMapper.selectByPrimaryKey(Integer.valueOf(ids[i]));
            if (game == null){
                throw new BusnessException(BusinessErrorEnum.GAME_NOT_EXIST,"该游戏不存在");
            }
            Integer userId = SessionUtil.getUserId();
            UserGameKey userGameKey = new UserGameKey();
            userGameKey.setUserId(userId);
            userGameKey.setGameId(Integer.valueOf(ids[i]));
            userGameMapper.insert(userGameKey);
            num++;
        }
        return num;
    }

    @Override
    public Integer testDelete(Map <String, Object> params) {
        int num = userMapper.testDelete(params);
        return num;
    }
}

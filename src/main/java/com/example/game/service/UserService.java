package com.example.game.service;

import com.example.game.common.Pager;
import com.example.game.error.BusnessException;
import com.example.game.po.User;
import com.example.game.po.UserPassword;
import com.example.game.vo.RegisterVo;
import com.example.game.vo.UserVo;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 16:57 2019/4/18
 * @Version ： $version$
 */
public interface UserService {

    UserVo selectUserById(Integer userId) throws BusnessException;

    void userRegister(RegisterVo registerVo) throws BusnessException;

    void logIn(RegisterVo registerVo) throws BusnessException;

    Pager userList(RegisterVo registerVo);

    User selectUserByUserName(String userName);

    UserPassword getPasswordByUserId(Integer id);

    Integer addGameforUser(String gameIds) throws BusnessException;
}

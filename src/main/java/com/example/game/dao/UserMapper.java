package com.example.game.dao;

import com.example.game.po.User;
import com.example.game.vo.RegisterVo;
import com.example.game.vo.UserVo;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    RegisterVo selectUserByPhone(String userPhone);

    List<UserVo> userList(RegisterVo registerVo);

    User selectUserByUserName(String userName);

    int testDelete(Map<String,Object> params);
}
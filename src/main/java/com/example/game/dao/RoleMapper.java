package com.example.game.dao;

import com.example.game.po.Role;
import com.example.game.vo.RoleVo;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByUserId(Integer userId);

    Integer disableOrNoEnable(Role role);

    List<Role> roleList(RoleVo roleVo);

    List<Role> getRolesByUserId(Integer id);
}
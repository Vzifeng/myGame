package com.example.game.service;

import com.example.game.common.Pager;
import com.example.game.po.Role;
import com.example.game.vo.RoleVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 17:11 2019/4/18
 * @Version ： $version$
 */
public interface RoleService {

    Set<String> selectRoleByUserId(Integer userId);

    Integer addRole(Role role);

    Integer disableOrNoEnable(Role role);

    Pager roleList(RoleVo roleVo);

    List<Role> getRolesByUserId(Integer id);
}

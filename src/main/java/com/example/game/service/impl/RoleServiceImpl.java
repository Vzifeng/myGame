package com.example.game.service.impl;

import com.example.game.common.Pager;
import com.example.game.dao.RoleMapper;
import com.example.game.po.Role;
import com.example.game.service.RoleService;
import com.example.game.utils.MapUtil;
import com.example.game.vo.RoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 17:11 2019/4/18
 * @Version ： $version$
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Set<String> selectRoleByUserId(Integer userId) {
        List<Role> roleList = roleMapper.selectRoleByUserId(userId);
        return null;
    }

    @Override
    public Integer addRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public Integer disableOrNoEnable(Role role) {
        return roleMapper.disableOrNoEnable(role);
    }

    @Override
    public Pager roleList(RoleVo roleVo) {
        Pager pager = new Pager();
        Page<Role> page = PageHelper.startPage(roleVo.getCurPage(),roleVo.getPageSize());
        roleMapper.roleList(roleVo);
        pager.setTotalRow((int) page.getTotal());
        pager.setTotalPage(page.getPages());
        pager.getCurPage(roleVo.getCurPage());
        pager.setList(page.getResult());
        return pager;
    }

    @Override
    public List <Role> getRolesByUserId(Integer id) {
        List<Role> list = roleMapper.getRolesByUserId(id);
        return list;
    }

}

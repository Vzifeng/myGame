package com.example.game.service.impl;

import com.example.game.dao.PermissionMapper;
import com.example.game.po.Permission;
import com.example.game.service.PermissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:27 2019/6/27/0027
 * @Version ： $version$
 */
@Service
public class PermissonServiceImpl implements PermissonService {

    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public List<Permission> getPermissonByRoleId(Integer id) {
        List<Permission> list = permissionMapper.getPermissonByRoleId(id);
        return list;
    }
}

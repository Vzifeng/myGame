package com.example.game.service;

import com.example.game.po.Permission;

import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:27 2019/6/27/0027
 * @Version ： $version$
 */
public interface PermissonService {
    List<Permission> getPermissonByRoleId(Integer id);
}

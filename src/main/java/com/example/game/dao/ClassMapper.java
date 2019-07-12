package com.example.game.dao;

import com.example.game.po.Class;

import java.util.List;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 16:36 2019/6/21/0021
 * @Version ： $version$
 */
public interface ClassMapper {
    List<Class> classList();

    List<Class> classList1();

    List<Map<String,Object>> classList2();
}

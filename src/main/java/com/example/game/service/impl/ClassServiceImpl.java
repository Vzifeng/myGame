package com.example.game.service.impl;

import com.example.game.dao.ClassMapper;
import com.example.game.po.Class;
import com.example.game.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 16:41 2019/6/21/0021
 * @Version ： $version$
 */
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassMapper classMapper;
    @Override
    public List<Class> classList() {
        List<Class> list = classMapper.classList();

        List<Class> list1 = classMapper.classList1();
        return list;
    }
}

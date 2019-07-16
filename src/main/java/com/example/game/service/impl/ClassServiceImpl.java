package com.example.game.service.impl;

import com.example.game.dao.ClassMapper;
import com.example.game.po.Class;
import com.example.game.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
    public List<Map<String,Object>> classList() {
        List<Class> list = classMapper.classList();

        List<Class> list1 = classMapper.classList1();

        List<Map<String,Object>> list2 = classMapper.classList2();
        for (Map<String,Object> map : list2)
            for (Map.Entry <String, Object> entry : map.entrySet()) {
                System.out.println("key:"+entry.getKey()+" and value:"+entry.getValue());
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println("时间:"+timestamp);

        return list2;
    }
}

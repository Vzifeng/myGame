package com.example.game.po;

import lombok.Data;

import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 16:34 2019/6/21/0021
 * @Version ： $version$
 */
@Data
public class Class {
    private Integer cId;

    private String cName;

    private List<Student> studentList;
}

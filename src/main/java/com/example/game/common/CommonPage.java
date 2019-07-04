package com.example.game.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 17:57 2019/6/17
 * @Version ： $version$
 */
@Getter
@Setter
public class CommonPage {
    private Integer curPage = 1;

    private Integer pageSize = 10;

    public static void main(String[] args) {
        List<String> lsi = new ArrayList <>();
        lsi.add("张涵");
        lsi.add("张山");
        lsi.add("几千");
        lsi.add("我就");
        lsi.add("黎明");
        lsi.add("李明");
        lsi.add("阿萨瓦");
        for (String str:lsi
             ) {
            System.out.print(str+"-");
        }
    }

}

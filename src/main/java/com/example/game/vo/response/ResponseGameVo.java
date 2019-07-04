package com.example.game.vo.response;

import com.example.game.po.GameMaterial;
import lombok.Data;

import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:02 2019/6/21/0021
 * @Version ： $version$
 */
@Data
public class ResponseGameVo {
    private Integer id;

    private String gameName;

    private String gameCompany;

    private String gamePublishTime;

    private Long people;

    private List<GameMaterial> gameMaterialList;
}

package com.example.game.vo;

import com.example.game.common.CommonPage;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 9:19 2019/6/21/0021
 * @Version ： $version$
 */
@Data
public class GameVo extends CommonPage {
    private Integer id;
    @NotNull(message = "游戏名称不能为空")
    private String gameName;
    @NotNull(message = "游戏公司不能为空")
    private String gameCompany;

    private String gamePublishTime;
}

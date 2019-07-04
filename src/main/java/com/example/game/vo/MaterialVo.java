package com.example.game.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 11:02 2019/7/1/0001
 * @Version ： $version$
 */
@Data
public class MaterialVo {
    private Integer id;
    @NotBlank(message = "材料名不能为空")
    private String gameMaterial;
    @NotNull(message = "材料数量不能为空")
    @Min(0)
    private Integer materialNum;
    @NotNull(message = "游戏ID不能为空")
    private Integer gameId;

    private Integer curPage = 1;

    private Integer pageSize = 10;

}

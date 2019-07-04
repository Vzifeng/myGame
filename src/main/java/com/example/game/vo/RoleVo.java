package com.example.game.vo;

import com.example.game.common.CommonPage;
import lombok.Data;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 9:08 2019/6/18/0018
 * @Version ： $version$
 */
@Data
public class RoleVo extends CommonPage {
    private Integer id;

    private String roleName;

    private String createTime;

    private Integer isUse;
}

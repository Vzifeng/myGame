package com.example.game.vo.response;

import com.example.game.po.Game;
import lombok.Data;

import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:23 2019/6/21/0021
 * @Version ： $version$
 */
@Data
public class ResponseUserVo {
    private Integer id;

    private String userName;

    private Integer userSex;

    private String userPhone;

    private String userAddr;

    private String userRegistrationTime;

    private String roleName;

    private List<ResponseGameVo> gameList;
}

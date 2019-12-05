package com.example.game.vo;

import com.example.game.vo.response.ResponseGameVo;
import lombok.Data;

import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:24 2019/6/17
 * @Version ： $version$
 */
@Data
public class UserVo {
    private Integer id;

    private String userName;

    private Integer userSex;

    private String userPhone;

    private String userAddr;

    private String userRegistrationTime;

    private String roleName;

    private String gameName;

    private String companyAndDpartment;

    private List<ResponseGameVo> responseGameVoList;

}

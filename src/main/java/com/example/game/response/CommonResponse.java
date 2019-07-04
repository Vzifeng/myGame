package com.example.game.response;

import lombok.Data;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:06 2019/2/19
 * @Version ： $version$
 */
@Data
public class CommonResponse {
    private String status;
    private Object data;

    //通用方法
    public static CommonResponse create(Object result){
        return CommonResponse.create(result,"success");
    }

    public static CommonResponse create(Object result,String str){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(str);
        commonResponse.setData(result);
        return commonResponse;
    }

}

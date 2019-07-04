package com.example.game.validator;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 9:57 2018/12/17
 * @Version ： $version$
 */
public class ValidationResult {
    //校验结果是否有错
    private boolean hasErrors = false;

    //存放错误信息的map
    private Map<String,String> errorMsgMap = new HashMap <>();

    public boolean isHasErrors(){
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map <String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map <String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    //通过格式化字符串信息获取错误信息的方法
    public String getErrorMsg(){
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }
}

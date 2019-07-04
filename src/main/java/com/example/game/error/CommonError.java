package com.example.game.error;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:19 2019/2/19
 * @Version ： $version$
 */
public interface CommonError {
    public int getErrorCode();

    public String getErrorMsg();

    public CommonError setErrorMsg(String errorMsg);
}

package com.example.game.error;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:27 2019/2/19
 * @Version ： $version$
 */
public enum BusinessErrorEnum implements CommonError {
    //通用错误码
    PARAMETER_VALIDATION_ERROR(100001,"参数不合法"),
    UNKNOWN(100002,"未知错误"),
    IS_NOT_PHONE_NUM(100003,"不是正确的手机号"),

    USER_NOT_EXIST(200001,"用户不存在"),
    USER_OR_PASSWORD_ERROR(200002,"手机号或者密码错误"),


    GAME_NOT_EXIST(300001,"游戏不存在"),
    COULD_NOT_DELETE_GAME(300002,"该游戏无法删除"),

    ;

    private int errorCode;
    private String errorMsg;

    BusinessErrorEnum(Integer code,String msg){
        this.errorCode = code;
        this.errorMsg = msg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}

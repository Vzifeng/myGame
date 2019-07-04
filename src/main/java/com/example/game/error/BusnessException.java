package com.example.game.error;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:21 2019/2/19
 * @Version ： $version$
 */
public class BusnessException extends Exception implements CommonError {
    private CommonError commonError;

    public BusnessException(){}

    public BusnessException(CommonError commonError){
        super();
        this.commonError = commonError ;
    }

    //接受自定义msg
    public BusnessException(CommonError commonError,String msg){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(msg);
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}

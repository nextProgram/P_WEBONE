package com.example.webone.demo.base;

/**
 * @author lhx
 * @date 2018/12/12
 */
public class ReturnVO<T> {
    private String retCode;
    private String retMsg;
    private T retData;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }
}

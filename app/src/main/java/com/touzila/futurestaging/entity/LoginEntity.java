package com.touzila.futurestaging.entity;

/**
 * Created by liu on 2018/1/2.
 */

public class LoginEntity extends BaseEntity {

    /**
     * code : login
     * status : FAILURE
     * desc : 交易码验证失败
     * token :
     * tips :
     * resultData : null
     */

    private String code;
    private String status;
    private String desc;
    private String token;
    private String tips;
    private Object resultData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", desc='" + desc + '\'' +
                ", token='" + token + '\'' +
                ", tips='" + tips + '\'' +
                ", resultData=" + resultData +
                '}';
    }
}

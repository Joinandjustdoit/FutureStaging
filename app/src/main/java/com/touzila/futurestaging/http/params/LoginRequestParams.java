package com.touzila.futurestaging.http.params;

/**
 * Created by liu on 2017/12/20.
 * 登录请求参数
 */

public class LoginRequestParams extends BaseRequestParams {

    /**
     * 设备号
     */
    public String agentNum;

    /**
     * 登录密码
     */
    public String password;

    /**
     * 请求来源
     * Android
     */
    public String sources;

    /**
     * 系统
     */
    public String system;

    /**
     * 登录手机号码
     */
    public String telephone;

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

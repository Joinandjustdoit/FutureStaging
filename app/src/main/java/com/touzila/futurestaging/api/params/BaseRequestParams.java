package com.touzila.futurestaging.api.params;


import java.io.Serializable;

/**
 * Created by liu on 2017/12/20.
 * 请求参数的基类
 */

public class BaseRequestParams implements Serializable {

    public long serialVersionUID = 1L;

    /**
     * 当前时间戳
     */
    public String id;

    /**
     * rpc版本
     * 1.0
     */
    public String jsonrpc;

    /**
     * 请求服务方法名
     * userAccountService.login
     */
    public String method;

    /**
     * 当前请求时间戳
     */
    public long timestamp;

    /**
     * 请求方法名
     */
    public String code;

    public String appRequest;

    /**
     * 请求渠道
     * Android
     */
    public String channel;

    public String timeOut;

    public String token;

    public String formToken;

    public String type;

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = "userAccountService.login";
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppRequest() {
        return appRequest;
    }

    public void setAppRequest(String appRequest) {
        this.appRequest = appRequest;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormToken() {
        return formToken;
    }

    public void setFormToken(String formToken) {
        this.formToken = formToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.aoke.apartmentsystem.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoxinglin
 */
@Data
public class TomcatInfo implements Serializable {

    private static final long serialVersionUID = 5817092425802069572L;
    /**
     * tomcat 已创建 session 数
     */
    private Double tomcatSessionsCreated;
    /**
     * tomcat 已过期 session 数
     */
    private Double tomcatSessionsExpired;
    /**
     * tomcat 当前活跃 session 数
     */
    private Double tomcatSessionsActiveCurrent;
    /**
     * tomcat 活跃 session 数峰值
     */
    private Double tomcatSessionsActiveMax;
    /**
     * 超过session 最大配置后，拒绝的 session 个数
     */
    private Double tomcatSessionsRejected;
    /**
     * 发送的字节数
     */
    private Double tomcatGlobalSent;
    /**
     * request 请求最长耗时
     */
    private Double tomcatGlobalRequestMax;
    /**
     * tomcat 全局异常数量
     */
    private Double tomcatGlobalError;
    /**
     * tomcat 当前线程数（包括守护线程）
     */
    private Double tomcatThreadsCurrent;
    /**
     * tomcat 配置的线程最大数
     */
    private Double tomcatThreadsConfigMax;
    /**
     * tomcat 当前繁忙线程数
     */
    private Double tomcatThreadsBusy;

    public Double getTomcatSessionsCreated() {
        return tomcatSessionsCreated;
    }

    public void setTomcatSessionsCreated(Double tomcatSessionsCreated) {
        this.tomcatSessionsCreated = tomcatSessionsCreated;
    }

    public Double getTomcatSessionsExpired() {
        return tomcatSessionsExpired;
    }

    public void setTomcatSessionsExpired(Double tomcatSessionsExpired) {
        this.tomcatSessionsExpired = tomcatSessionsExpired;
    }

    public Double getTomcatSessionsActiveCurrent() {
        return tomcatSessionsActiveCurrent;
    }

    public void setTomcatSessionsActiveCurrent(Double tomcatSessionsActiveCurrent) {
        this.tomcatSessionsActiveCurrent = tomcatSessionsActiveCurrent;
    }

    public Double getTomcatSessionsActiveMax() {
        return tomcatSessionsActiveMax;
    }

    public void setTomcatSessionsActiveMax(Double tomcatSessionsActiveMax) {
        this.tomcatSessionsActiveMax = tomcatSessionsActiveMax;
    }

    public Double getTomcatSessionsRejected() {
        return tomcatSessionsRejected;
    }

    public void setTomcatSessionsRejected(Double tomcatSessionsRejected) {
        this.tomcatSessionsRejected = tomcatSessionsRejected;
    }

    public Double getTomcatGlobalSent() {
        return tomcatGlobalSent;
    }

    public void setTomcatGlobalSent(Double tomcatGlobalSent) {
        this.tomcatGlobalSent = tomcatGlobalSent;
    }

    public Double getTomcatGlobalRequestMax() {
        return tomcatGlobalRequestMax;
    }

    public void setTomcatGlobalRequestMax(Double tomcatGlobalRequestMax) {
        this.tomcatGlobalRequestMax = tomcatGlobalRequestMax;
    }

    public Double getTomcatGlobalError() {
        return tomcatGlobalError;
    }

    public void setTomcatGlobalError(Double tomcatGlobalError) {
        this.tomcatGlobalError = tomcatGlobalError;
    }

    public Double getTomcatThreadsCurrent() {
        return tomcatThreadsCurrent;
    }

    public void setTomcatThreadsCurrent(Double tomcatThreadsCurrent) {
        this.tomcatThreadsCurrent = tomcatThreadsCurrent;
    }

    public Double getTomcatThreadsConfigMax() {
        return tomcatThreadsConfigMax;
    }

    public void setTomcatThreadsConfigMax(Double tomcatThreadsConfigMax) {
        this.tomcatThreadsConfigMax = tomcatThreadsConfigMax;
    }

    public Double getTomcatThreadsBusy() {
        return tomcatThreadsBusy;
    }

    public void setTomcatThreadsBusy(Double tomcatThreadsBusy) {
        this.tomcatThreadsBusy = tomcatThreadsBusy;
    }
}

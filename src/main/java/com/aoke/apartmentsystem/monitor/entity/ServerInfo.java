package com.aoke.apartmentsystem.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoxinglin
 */
@Data
public class ServerInfo implements Serializable {

    private static final long serialVersionUID = 5915203206170057447L;
    /**
     * 应用已运行时长
     */
    private Double processUptime;
    /**
     * 应用 CPU占用率
     */
    private Double processCpuUsage;
    /**
     * 应用启动时间点
     */
    private String processStartTime;
    /**
     * 系统 CPU核心数
     */
    private Double systemCpuCount;
    /**
     * 系统 CPU 使用率
     */
    private Double systemCpuUsage;
    /**
     * 当前活跃 JDBC连接数
     */
    private Double jdbcConnectionsActive;
    /**
     * JDBC最小连接数
     */
    private Double jdbcConnectionsMin;
    /**
     * JDBC最大连接数
     */
    private Double jdbcConnectionsMax;

    public Double getProcessUptime() {
        return processUptime;
    }

    public void setProcessUptime(Double processUptime) {
        this.processUptime = processUptime;
    }

    public Double getProcessCpuUsage() {
        return processCpuUsage;
    }

    public void setProcessCpuUsage(Double processCpuUsage) {
        this.processCpuUsage = processCpuUsage;
    }

    public String getProcessStartTime() {
        return processStartTime;
    }

    public void setProcessStartTime(String processStartTime) {
        this.processStartTime = processStartTime;
    }

    public Double getSystemCpuCount() {
        return systemCpuCount;
    }

    public void setSystemCpuCount(Double systemCpuCount) {
        this.systemCpuCount = systemCpuCount;
    }

    public Double getSystemCpuUsage() {
        return systemCpuUsage;
    }

    public void setSystemCpuUsage(Double systemCpuUsage) {
        this.systemCpuUsage = systemCpuUsage;
    }

    public Double getJdbcConnectionsActive() {
        return jdbcConnectionsActive;
    }

    public void setJdbcConnectionsActive(Double jdbcConnectionsActive) {
        this.jdbcConnectionsActive = jdbcConnectionsActive;
    }

    public Double getJdbcConnectionsMin() {
        return jdbcConnectionsMin;
    }

    public void setJdbcConnectionsMin(Double jdbcConnectionsMin) {
        this.jdbcConnectionsMin = jdbcConnectionsMin;
    }

    public Double getJdbcConnectionsMax() {
        return jdbcConnectionsMax;
    }

    public void setJdbcConnectionsMax(Double jdbcConnectionsMax) {
        this.jdbcConnectionsMax = jdbcConnectionsMax;
    }
}

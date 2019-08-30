package com.aoke.apartmentsystem.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoxinglin
 */
@Data
public class JvmInfo implements Serializable {

    private static final long serialVersionUID = -5178501845351050670L;
    /**
     * JVM 最大内存
     */
    private Double jvmMemoryMax;
    /**
     * JVM 可用内存
     */
    private Double jvmMemoryCommitted;
    /**
     * JVM 已用内存
     */
    private Double jvmMemoryUsed;
    /**
     * JVM 缓冲区已用内存
     */
    private Double jvmBufferMemoryUsed;
    /**
     * 当前缓冲区数量
     */
    private Double jvmBufferCount;
    /**
     * JVM 守护线程数量
     */
    private Double jvmThreadsdaemon;
    /**
     * JVM 当前活跃线程数量
     */
    private Double jvmThreadsLive;
    /**
     * JVM 峰值线程数量
     */
    private Double jvmThreadsPeak;
    /**
     * JVM 已加载 Class 数量
     */
    private Double jvmClassesLoaded;
    /**
     * JVM 未加载 Class 数量
     */
    private Double jvmClassesUnloaded;
    /**
     * GC 时, 年轻代分配的内存空间
     */
    private Double jvmGcMemoryAllocated;
    /**
     * GC 时, 老年代分配的内存空间
     */
    private Double jvmGcMemoryPromoted;
    /**
     * GC 时, 老年代的最大内存空间
     */
    private Double jvmGcMaxDataSize;
    /**
     * FullGC 时, 老年代的内存空间
     */
    private Double jvmGcLiveDataSize;

    public Double getJvmMemoryMax() {
        return jvmMemoryMax;
    }

    public void setJvmMemoryMax(Double jvmMemoryMax) {
        this.jvmMemoryMax = jvmMemoryMax;
    }

    public Double getJvmMemoryCommitted() {
        return jvmMemoryCommitted;
    }

    public void setJvmMemoryCommitted(Double jvmMemoryCommitted) {
        this.jvmMemoryCommitted = jvmMemoryCommitted;
    }

    public Double getJvmMemoryUsed() {
        return jvmMemoryUsed;
    }

    public void setJvmMemoryUsed(Double jvmMemoryUsed) {
        this.jvmMemoryUsed = jvmMemoryUsed;
    }

    public Double getJvmBufferMemoryUsed() {
        return jvmBufferMemoryUsed;
    }

    public void setJvmBufferMemoryUsed(Double jvmBufferMemoryUsed) {
        this.jvmBufferMemoryUsed = jvmBufferMemoryUsed;
    }

    public Double getJvmBufferCount() {
        return jvmBufferCount;
    }

    public void setJvmBufferCount(Double jvmBufferCount) {
        this.jvmBufferCount = jvmBufferCount;
    }

    public Double getJvmThreadsdaemon() {
        return jvmThreadsdaemon;
    }

    public void setJvmThreadsdaemon(Double jvmThreadsdaemon) {
        this.jvmThreadsdaemon = jvmThreadsdaemon;
    }

    public Double getJvmThreadsLive() {
        return jvmThreadsLive;
    }

    public void setJvmThreadsLive(Double jvmThreadsLive) {
        this.jvmThreadsLive = jvmThreadsLive;
    }

    public Double getJvmThreadsPeak() {
        return jvmThreadsPeak;
    }

    public void setJvmThreadsPeak(Double jvmThreadsPeak) {
        this.jvmThreadsPeak = jvmThreadsPeak;
    }

    public Double getJvmClassesLoaded() {
        return jvmClassesLoaded;
    }

    public void setJvmClassesLoaded(Double jvmClassesLoaded) {
        this.jvmClassesLoaded = jvmClassesLoaded;
    }

    public Double getJvmClassesUnloaded() {
        return jvmClassesUnloaded;
    }

    public void setJvmClassesUnloaded(Double jvmClassesUnloaded) {
        this.jvmClassesUnloaded = jvmClassesUnloaded;
    }

    public Double getJvmGcMemoryAllocated() {
        return jvmGcMemoryAllocated;
    }

    public void setJvmGcMemoryAllocated(Double jvmGcMemoryAllocated) {
        this.jvmGcMemoryAllocated = jvmGcMemoryAllocated;
    }

    public Double getJvmGcMemoryPromoted() {
        return jvmGcMemoryPromoted;
    }

    public void setJvmGcMemoryPromoted(Double jvmGcMemoryPromoted) {
        this.jvmGcMemoryPromoted = jvmGcMemoryPromoted;
    }

    public Double getJvmGcMaxDataSize() {
        return jvmGcMaxDataSize;
    }

    public void setJvmGcMaxDataSize(Double jvmGcMaxDataSize) {
        this.jvmGcMaxDataSize = jvmGcMaxDataSize;
    }

    public Double getJvmGcLiveDataSize() {
        return jvmGcLiveDataSize;
    }

    public void setJvmGcLiveDataSize(Double jvmGcLiveDataSize) {
        this.jvmGcLiveDataSize = jvmGcLiveDataSize;
    }
}

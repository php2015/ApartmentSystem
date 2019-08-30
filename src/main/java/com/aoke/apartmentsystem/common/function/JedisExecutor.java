package com.aoke.apartmentsystem.common.function;

import com.aoke.apartmentsystem.common.exception.RedisConnectException;

/**
 * @author xiaoxinglin
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}

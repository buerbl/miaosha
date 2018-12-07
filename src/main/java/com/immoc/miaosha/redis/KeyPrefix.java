package com.immoc.miaosha.redis;

/**
 * Created by buer on 2018/12/5.
 */
public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}

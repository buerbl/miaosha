package com.immoc.miaosha.redis;

/**
 * Created by buer on 2018/12/5.
 */
public class OrderKey extends BasePrefix{
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

}

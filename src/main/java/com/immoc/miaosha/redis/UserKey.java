package com.immoc.miaosha.redis;

/**
 * Created by buer on 2018/12/5.
 */
public class UserKey extends BasePrefix{
    private UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}

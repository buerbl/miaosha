package com.immoc.miaosha.dao;

import com.immoc.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by buer on 2018/12/5.
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);
    @Insert("insert into user(id, name)values(#{id}, #{name})")
    public int insert(User user);
}

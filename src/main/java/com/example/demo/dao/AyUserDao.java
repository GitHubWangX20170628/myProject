package com.example.demo.dao;

import com.example.demo.model.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Decription:用户DAO
 * @author wangX
 * @date 2018/7/20 10:03
 *
 */
@Mapper
public interface AyUserDao {
    /**
     * 描述：通过用户和密码查询用户
     * @param name
     * @param password
     * @return
     */
    AyUser findByNameAndPassword(@Param("name") String name, @Param("password") String password);
    AyUser findByUserName(@Param("name") String name);
}

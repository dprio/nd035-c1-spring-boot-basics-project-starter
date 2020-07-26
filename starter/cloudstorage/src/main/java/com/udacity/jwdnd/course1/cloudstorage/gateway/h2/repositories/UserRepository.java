package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories;


import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {

    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    User findUserByUserName(String userName);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) " +
            "VALUES (#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

}

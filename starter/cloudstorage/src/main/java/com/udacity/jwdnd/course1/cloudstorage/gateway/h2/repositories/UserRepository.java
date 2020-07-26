package com.udacity.jwdnd.course1.cloudstorage.gateway.h2;


import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {

    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    public User findUserBYUserName(String userName);

}

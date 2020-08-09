package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CredencialRepository {

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> findCredentialsByUserId(int userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid)" +
            "VALUES (#{url}, #{userName}, #{key}, #{password}, #{userId)")
    int insertCredential(Credential credential);

}

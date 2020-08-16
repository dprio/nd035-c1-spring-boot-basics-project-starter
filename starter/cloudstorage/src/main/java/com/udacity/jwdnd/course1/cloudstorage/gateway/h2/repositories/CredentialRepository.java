package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CredentialRepository {

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> findCredentialsByUserId(int userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid)" +
            "VALUES (#{url}, #{userName}, #{key}, #{password}, #{userId})")
    int insertCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{userName}, key = #{key}, password = #{password}" +
            "WHERE  credentialid = #{credentialId} AND userid = #{userId} ")
    void updateCredential(Credential credential);

}

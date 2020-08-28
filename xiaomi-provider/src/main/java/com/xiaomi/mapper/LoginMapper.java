package com.xiaomi.mapper;


import entity.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select(" select * from mi_user where account = #{value}")
    UserBean findUserInfoByAccount(String account);

    @Select(" select * from mi_user where phone = #{value}")
    UserBean queryUserInfoByAccount(String phone);

   /* @Select(" select mu.phone from mi_user mu where account = #{value} ")
    UserBean finUserPhone(String account);

    @Select(" select * from mi_user where user_id = #{value}")
    UserBean findUserInfoById(Integer userId);*/
}

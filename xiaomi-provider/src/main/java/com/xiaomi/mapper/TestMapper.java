package com.xiaomi.mapper;

import entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface TestMapper {

    @Select(" select * from mi_test ")
    List<Test> test();
}

package com.wzy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @ClassName UserMapper
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/14 20:51
 * @Version 1.0
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user values(#{id}, #{name})")
    boolean insert(Map map);

    @Select("select count(1) from user where id = #{id}")
    int selectById(String id);

}

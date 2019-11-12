package com.xt.springboot.mapper;

import com.xt.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * 指定这是一个操作数据库的 mapper
 */
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    Department getById(Integer id);

    @Delete("delete from department where id = #{id}")
    int deleteById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values (#{departmentName})")
    int insert(Department department);

    @Update("update department set department_name=#{departmentName} where id = #{id}")
    int update(Department department);
}

package com.xt.springboot.mapper;


import com.xt.springboot.bean.Employee;

/**
 * @Mapper 或者 @MapperScan 将接口扫描装配到容器中
 */
public interface EmployeeMapper {

    Employee getById(Integer id);

    void insert(Employee employee);
}

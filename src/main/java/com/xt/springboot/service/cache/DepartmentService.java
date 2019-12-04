package com.xt.springboot.service.cache;

import com.xt.springboot.bean.Department;
import com.xt.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "dept")
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Cacheable
    public Department findById(Integer id) {
        System.out.println("查询部门：" + id);
        Department dept = departmentMapper.getById(id);
        return dept;
    }
}

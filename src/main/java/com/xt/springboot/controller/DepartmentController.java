package com.xt.springboot.controller;

import com.xt.springboot.bean.Department;
import com.xt.springboot.bean.Employee;
import com.xt.springboot.mapper.DepartmentMapper;
import com.xt.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable Integer id){
        Department dept = departmentMapper.getById(id);
        return dept;
    }

    @GetMapping("/dept")
    public Department insertDepartment(Department department) {
        departmentMapper.insert(department);
        return department;
    }

    @GetMapping("/emp2/{id}")
    public Employee getEmp(@PathVariable Integer id) {
        Employee emp = employeeMapper.getById(id);
        return emp;
    }
}

package com.xt.springboot.controller.cache;

import com.xt.springboot.bean.Department;
import com.xt.springboot.service.cache.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/department")
public class DeptController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public Department findOne(@PathVariable Integer id) {
        Department dept = departmentService.findById(id);
        return dept;
    }
}

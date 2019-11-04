package com.xt.springboot.controller;

import com.xt.springboot.dao.DepartmentDao;
import com.xt.springboot.dao.EmployeeDao;
import com.xt.springboot.entities.Department;
import com.xt.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * 员工管理
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有的员工信息，并返回列表页面
     * @param model
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        // 放在请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    /**
     * 来到添加页面
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

}

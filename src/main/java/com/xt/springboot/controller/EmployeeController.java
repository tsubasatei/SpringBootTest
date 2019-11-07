package com.xt.springboot.controller;

import com.xt.springboot.dao.DepartmentDao;
import com.xt.springboot.dao.EmployeeDao;
import com.xt.springboot.entities.Department;
import com.xt.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 员工添加
     * SpringMvc 自动将请求参数和入参对象的属性进行意义绑定：
     *      要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String add(Employee employee) {
        System.out.println("保存的员工信息为：" + employee);
        employeeDao.save(employee);

        /**
         * 来到员工列表页面
         * redirect: 表示重定向到一个地址 /代表当前项目路径
         * forward：表示转发到一个地址
         *
         * ThymeleafViewResolver
         */
        return "redirect:/emps";
    }

    /**
     * 来到修改页面，查出当前员工，在页面回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp", employee);
        model.addAttribute("depts", departments);
        // 回到修改页面(add 是一个修改二合一的页面)
        return "emp/add";
    }

    /**
     * 修改员工信息，跳到列表页面
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String update(Employee employee) {
        System.out.println("修改的员工信息：" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 根据 id 删除员工信息
     * 有问题 DELETE 请求不支持
     *
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}

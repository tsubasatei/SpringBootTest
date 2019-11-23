package com.xt.springboot.controller.cache;

import com.xt.springboot.bean.Employee;
import com.xt.springboot.service.cache.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "empController")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public Employee findOne(@PathVariable Integer id) {
        return employeeService.getEmp(id);
    }

    @GetMapping("/employee")
    public Employee update(Employee employee){
        System.out.println("update员工信息：" + employee);
        return employeeService.updateEmp(employee);
    }

    @GetMapping("/employee/del")
    public String deleteEmp(@RequestParam Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }
}

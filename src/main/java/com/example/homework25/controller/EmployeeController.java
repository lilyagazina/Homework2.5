package com.example.homework25.controller;

import com.example.homework25.Employee;
import com.example.homework25.exeption.EmployeeNotFoundException;
import com.example.homework25.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

   // @GetMapping
    //public String employee() {
     //   return "<b>employee</b>";
   // }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName) {
        return employeeService.addEmployee(firstName,lastName);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName,lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName,
                               @RequestParam String lastName) {
        return employeeService.findEmployee(firstName,lastName);

    }
}



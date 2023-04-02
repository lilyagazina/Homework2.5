package com.example.homework25.service;

import com.example.homework25.Employee;
import com.example.homework25.exeption.EmployeeAlreadyAddedException;
import com.example.homework25.exeption.EmployeeNotFoundException;
import com.example.homework25.exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class EmployeeService {
    private static final int SIZE = 10;
    private final Employee[] employees = new Employee[SIZE];

    @GetMapping
    public Employee addEmployee(String firstName, String lastName) {
        // Добавить сотрудника.
        int id = -1;
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null && id == -1) {
                id = i;
            }
            if (employees[i] != null && employees[i].equals(employee)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        if (id == -1) {
            throw new EmployeeStorageIsFullException();
        }
        employees[id] = employee;
        return employees[id];
    }


    @GetMapping
    public Employee removeEmployee(String firstName, String lastName) {
        // Удалить сотрудника.
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getFirstName().equals(firstName) &&
                    employees[i].getLastName().equals(lastName)) {
                employees[i] = null;
                break;
            }
        }
        throw new EmployeeNotFoundException();
    }

    @GetMapping
    public Employee findEmployee (String firstName, String lastName) {
        //Найти сотрудника.
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getFirstName().equals(firstName) &&
                    employees[i].getLastName().equals(lastName)) {
                return employees[i];
            }
        }
        throw new EmployeeNotFoundException();

    }

}
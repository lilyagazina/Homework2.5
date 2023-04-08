package com.example.homework25.service;

import com.example.homework25.model.Employee;
import com.example.homework25.exeption.EmployeeAlreadyAddedException;
import com.example.homework25.exeption.EmployeeNotFoundException;
import com.example.homework25.exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private static final int SIZE = 5;
    private final List<Employee> employees = new ArrayList<>(SIZE);
    @PostConstruct
    public void init (){
        employees.add(new Employee("Аверина ","Анастасия"));
        employees.add(new Employee("Иванов","Иван"));
        employees.add(new Employee("Гуров","Дмитрий"));
        employees.add(new Employee("Смирнов ","Михаил"));
        employees.add(new Employee("Кучерова ","Виктория"));
    }
    @GetMapping
    public Employee addEmployee(String firstName, String lastName) {
        // Добавить сотрудника.
        int id = -1;
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) == null && id == -1) {
                id = i;
            }
            if (employees.get(i) != null && employees.get(i).equals(employee)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        if (id == -1) {
            throw new EmployeeStorageIsFullException();
        }
        employees.set(id, employee);
        return employees.get(id);
    }


    @GetMapping
    public Employee removeEmployee(String firstName, String lastName) {
        // Удалить сотрудника.
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) != null && employees.get(i).getFirstName().equals(firstName) &&
                    employees.get(i).getLastName().equals(lastName)) {
                employees.set(i, null);
                break;
            }
        }
        throw new EmployeeNotFoundException();
    }

    @GetMapping
    public Employee findEmployee (String firstName, String lastName) {
        //Найти сотрудника.
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) != null && employees.get(i).getFirstName().equals(firstName) &&
                    employees.get(i).getLastName().equals(lastName)) {
                return employees.get(i);
            }
        }
        throw new EmployeeNotFoundException();
    }
    public List<Employee> list(){
        return Collections.unmodifiableList(employees);
    }

}
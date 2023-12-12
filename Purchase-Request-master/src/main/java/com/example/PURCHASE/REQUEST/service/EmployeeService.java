package com.example.PURCHASE.REQUEST.service;

import com.example.PURCHASE.REQUEST.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);

}

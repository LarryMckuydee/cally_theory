package app.services;

import java.util.List;
import java.util.stream.Collectors;

import app.models.CustomerService;
import app.models.Employee;
import app.models.ProductManager;
import app.models.TechnicalLead;

public class EmployeeCollection {
    private List<Employee> employees;

    public EmployeeCollection(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getAvailableEmployees() {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false)
        .collect(Collectors.toList());
    }

    public List<Employee> getAvailableCustomerServices() {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false && employee instanceof CustomerService)
        .collect(Collectors.toList());
    }

    public List<Employee> getAvailableTechnicalLeads() {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false && employee instanceof TechnicalLead)
        .collect(Collectors.toList());
    }

    public List<Employee> getAvailableProductManagers() {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false && employee instanceof ProductManager)
        .collect(Collectors.toList());
    }

    public List<Employee> getAvailableEmployeesByLevel(int level) {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false && employee.getLevel() == level)
        .collect(Collectors.toList());
    }
    
}
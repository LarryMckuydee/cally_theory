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

    
    /** 
     * Return all not isEngaging Employee
     * 
     * @return List<Employee>
     */
    public List<Employee> getAvailableEmployees() {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false)
        .collect(Collectors.toList());
    }

    
    /** 
     * Return all not isEngaging and CustomerService
     * 
     * @return List<Employee>
     */
    public List<Employee> getAvailableCustomerServices() {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false && employee instanceof CustomerService)
        .collect(Collectors.toList());
    }

    
    /** 
     * Return all not isEngaging TechnicalLead
     * 
     * @return List<Employee>
     */
    public List<Employee> getAvailableTechnicalLeads() {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false && employee instanceof TechnicalLead)
        .collect(Collectors.toList());
    }

    
    /** 
     * Return all not isEngaging ProductManager
     * 
     * @return List<Employee>
     */
    public List<Employee> getAvailableProductManagers() {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false && employee instanceof ProductManager)
        .collect(Collectors.toList());
    }

    
    /** 
     * Return all not isEngaging Employee based on given level
     * 
     * @param level
     * @return List<Employee>
     */
    public List<Employee> getAvailableEmployeesByLevel(int level) {
        return employees.stream().map(employee -> employee)
        .filter(employee -> employee.getIsEngaging() == false && employee.getLevel() == level)
        .collect(Collectors.toList());
    }
    
}
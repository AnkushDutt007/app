package com.app2.service;

import com.app2.dto.EmployeeRequest;
import com.app2.model.Employee;
import com.app2.model.Role;
import com.app2.repository.EmployeeRepository;
import com.app2.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service class for managing employees.
 */
@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;


    /**
     * Creates a new employee.
     *
     * @param employeeRequest the employee request to create
     * @return the created employee
     */
    public Employee createEmployee(EmployeeRequest employeeRequest) {
        logger.info("Creating employee: {}", employeeRequest);

        // Log all roles to verify they exist
        List<Role> roles = roleRepository.findAll();
        logger.info("Available roles: {}", roles);

        // Ensure the role exists
        Role role = roleRepository.findById(employeeRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Employee employee = new Employee();
        String[] nameParts = employeeRequest.getName().split(" ", 2);
        employee.setFirstname(nameParts[0]);
        employee.setSurname(nameParts.length > 1 ? nameParts[1] : "");
        employee.setRole(role);

        return employeeRepository.save(employee);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee
     * @return the employee, if found
     */
    public Optional<Employee> getEmployeeById(Long id) {
        logger.info("Retrieving employee with ID: {}", id);
        return employeeRepository.findById(id);
    }

    /**
     * Updates an existing employee.
     *
     * @param id the ID of the employee to update
     * @param employeeDetails the new employee details
     * @return the updated employee
     */
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        logger.info("Updating employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setFirstname(employeeDetails.getFirstname());
        employee.setSurname(employeeDetails.getSurname());
        employee.setRole(employeeDetails.getRole());
        return employeeRepository.save(employee);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     */
    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with ID: {}", id);
        employeeRepository.deleteById(id);
    }
}
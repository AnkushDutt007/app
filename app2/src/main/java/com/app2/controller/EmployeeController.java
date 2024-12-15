package com.app2.controller;

import com.app2.dto.EmployeeRequest;
import com.app2.model.Employee;
import com.app2.model.Role;
import com.app2.repository.RoleRepository;
import com.app2.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing employees.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;


    /**
            * Creates a new employee.
     *
             * @param employeeRequest the employee request to create
     * @return the created employee
     */
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        logger.info("Received request to create employee: {}", employeeRequest);
        return employeeService.createEmployee(employeeRequest);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee
     * @return the employee, if found
     */
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        logger.info("Received request to retrieve employee with ID: {}", id);
        return employeeService.getEmployeeById(id);
    }

    /**
     * Updates an existing employee.
     *
     * @param id the ID of the employee to update
     * @param employeeDetails the new employee details
     * @return the updated employee
     */
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        logger.info("Received request to update employee with ID: {}", id);
        return employeeService.updateEmployee(id, employeeDetails);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @return a message indicating the result
     */
    @DeleteMapping("/{id}")
    public Map<String, String> deleteEmployee(@PathVariable Long id) {
        logger.info("Received request to delete employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        return Collections.singletonMap("message", "Employee deleted successfully");
    }
}

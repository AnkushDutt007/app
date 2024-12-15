package com.app1.controller;

import com.app1.dto.App1EmployeeDTO;
import com.app1.dto.App2EmployeeDTO;
import com.app1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Creates a new employee.
     *
     * @param employeeDTO the employee data transfer object
     * @param role the role of the user making the request
     * @return the created employee data transfer object
     */
    @PostMapping
    public ResponseEntity<App2EmployeeDTO> createEmployee(@RequestBody App1EmployeeDTO employeeDTO, @RequestHeader("Role") String role) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO, role));
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee
     * @return the employee data transfer object
     */
    @GetMapping("/{id}")
    public ResponseEntity<App2EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    /**
     * Updates an existing employee.
     *
     * @param id the ID of the employee to update
     * @param employeeDTO the employee data transfer object
     * @param role the role of the user making the request
     * @return the updated employee data transfer object
     */
    @PutMapping("/{id}")
    public ResponseEntity<App2EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody App1EmployeeDTO employeeDTO, @RequestHeader("Role") String role) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO, role));
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @param role the role of the user making the request
     * @return a response entity with a success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id, @RequestHeader("Role") String role) {
        employeeService.deleteEmployee(id, role);
        return ResponseEntity.ok("{\"message\": \"Employee deleted successfully\"}");
    }
}

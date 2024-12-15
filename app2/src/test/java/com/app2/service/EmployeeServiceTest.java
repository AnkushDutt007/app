package com.app2.service;

import com.app2.dto.EmployeeRequest;
import com.app2.model.Employee;
import com.app2.model.Role;
import com.app2.repository.EmployeeRepository;
import com.app2.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("John Doe");
        employeeRequest.setRoleId(1L);

        Role role = new Role();
        role.setId(1L);
        role.setName("USER");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.createEmployee(employeeRequest);

        assertNotNull(result);
        assertEquals("John", result.getFirstname());
        assertEquals("Doe", result.getSurname());
        assertEquals(role, result.getRole());
    }

    @Test
    void testGetEmployeeById() {
        Long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstname("John");
        employee.setSurname("Doe");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("John", result.get().getFirstname());
        assertEquals("Doe", result.get().getSurname());
    }

    @Test
    void testUpdateEmployee() {
        Long id = 1L;
        Employee existingEmployee = new Employee();
        existingEmployee.setId(id);
        existingEmployee.setFirstname("John");
        existingEmployee.setSurname("Doe");

        Employee updatedDetails = new Employee();
        updatedDetails.setFirstname("Jane");
        updatedDetails.setSurname("Smith");
        Role role = new Role();
        role.setId(2L);
        updatedDetails.setRole(role);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.updateEmployee(id, updatedDetails);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstname());
        assertEquals("Smith", result.getSurname());
        assertEquals(role, result.getRole());
    }

    @Test
    void testDeleteEmployee() {
        Long id = 1L;
        doNothing().when(employeeRepository).deleteById(id);
        employeeService.deleteEmployee(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }
}

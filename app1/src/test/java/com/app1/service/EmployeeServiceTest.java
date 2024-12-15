package com.app1.service;

import com.app1.dto.App1EmployeeDTO;
import com.app1.dto.App2EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestPropertySource(properties = {
        "app2.base-url=http://localhost:8081/api/employees",
        "app2.username=user",
        "app2.password=password"
})
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService = new EmployeeService();
    @Mock
    private RestTemplate restTemplate ;

    @Test
    void testGetEmployeeById() {
        Long id = 1L;
        App2EmployeeDTO employeeDTO = new App2EmployeeDTO();
        employeeDTO.setName("John");
        employeeDTO.setRoleId(1L);

        when(restTemplate.getForObject(anyString(), eq(App2EmployeeDTO.class))).thenReturn(employeeDTO);

        App2EmployeeDTO result = employeeService.getEmployeeById(id);

        assertNotNull(result);
        assertEquals(1L, result.getRoleId());
    }

    @Test
    void deleteEmployee_shouldCallDelete_whenEmployeeIsPresent() {
        Long id = 1L;
        employeeService.deleteEmployee(id, "ADMIN");
        Mockito.verify(restTemplate,times(1)).delete(anyString());
    }

}

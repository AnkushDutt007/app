package com.app1.service;

import com.app1.dto.App1EmployeeDTO;
import com.app1.dto.App2EmployeeDTO;
import com.app1.constants.RoleConstants;
import com.app1.mapper.EmployeeMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Value("${app2.base-url}")
    private String app2BaseUrl;

    @Value("${app2.username}")
    private String app2Username;

    @Value("${app2.password}")
    private String app2Password;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(app2Username, app2Password));
    }

    /**
     * Validates the role from the request header.
     *
     * @param role the role to validate
     * @throws IllegalArgumentException if the role is invalid
     */
    private void validateRole(String role) {
        if (role == null || role.length() < 3 || role.length() > 50) {
            throw new IllegalArgumentException("Invalid role length");
        }
        if (!role.equals(RoleConstants.ADMIN) && !role.equals(RoleConstants.USER) && !role.equals(RoleConstants.MANAGER)) {
            throw new IllegalArgumentException("Invalid role");
        }
    }
    @Retryable(value = {Exception.class}, maxAttempts = 3)
    public App2EmployeeDTO createEmployee(App1EmployeeDTO app1EmployeeDTO, String role) {
        logger.debug("Creating employee with role: {}", role);
        validateRole(role);
        App2EmployeeDTO app2EmployeeDTO = EmployeeMapper.INSTANCE.app1ToApp2(app1EmployeeDTO);
        String url = app2BaseUrl;
        App2EmployeeDTO createdEmployee = restTemplate.postForObject(url, app2EmployeeDTO, App2EmployeeDTO.class);
        logger.info("Employee created with ID: {}", createdEmployee.getRoleId());
        return createdEmployee;
    }
    @Retryable(value = {Exception.class}, maxAttempts = 3)
    public App2EmployeeDTO getEmployeeById(Long id) {
        logger.debug("Fetching employee with ID: {}", id);
        String url = app2BaseUrl + "/" + id;
        App2EmployeeDTO employee = restTemplate.getForObject(url, App2EmployeeDTO.class);
        logger.info("Fetched employee: {}", employee);
        return employee;
    }
    @Retryable(value = {Exception.class}, maxAttempts = 3)
    public App2EmployeeDTO updateEmployee(Long id, App1EmployeeDTO app1EmployeeDTO, String role) {
        logger.debug("Updating employee with ID: {} and role: {}", id, role);
        validateRole(role);
        App2EmployeeDTO app2EmployeeDTO = EmployeeMapper.INSTANCE.app1ToApp2(app1EmployeeDTO);
        String url = app2BaseUrl + "/" + id;
        restTemplate.put(url, app2EmployeeDTO);
        logger.info("Employee updated with ID: {}", id);
        return app2EmployeeDTO;
    }
    @Retryable(value = {Exception.class}, maxAttempts = 3)
    public void deleteEmployee(Long id, String role) {
        logger.debug("Deleting employee with ID: {} and role: {}", id, role);
        validateRole(role);
        String url = app2BaseUrl + "/" + id;
        restTemplate.delete(url);
        logger.info("Employee deleted with ID: {}", id);
    }
}

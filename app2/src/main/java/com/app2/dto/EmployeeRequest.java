package com.app2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for Employee requests.
 */
public class EmployeeRequest {
    private String name;
    @JsonProperty("role_id")
    private Long roleId;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}

package com.app1.mapper;

import com.app1.dto.App1EmployeeDTO;
import com.app1.dto.App2EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "role", target = "roleId", qualifiedByName = "roleToRoleId")
    App2EmployeeDTO app1ToApp2(App1EmployeeDTO app1EmployeeDTO);

    @Named("roleToRoleId")
    default Long roleToRoleId(String role) {
        switch (role) {
            case "ADMIN":
                return 1L;
            case "USER":
                return 2L;
            case "MANAGER":
                return 3L;
            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }
}

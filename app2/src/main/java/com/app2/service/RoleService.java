package com.app2.service;

import com.app2.repository.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void deleteRoleAndReassignProjects(Long roleId, Long defaultEmployeeId) {
        // Delete employees with the given role
        entityManager.createNativeQuery("DELETE FROM employee WHERE roleid = ?")
                .setParameter(1, roleId)
                .executeUpdate();

        // Reassign projects to the default employee
        entityManager.createNativeQuery("UPDATE project SET employee_id = ? WHERE employee_id IN (SELECT id FROM employee WHERE roleid = ?)")
                .setParameter(1, defaultEmployeeId)
                .setParameter(2, roleId)
                .executeUpdate();

        // Delete the role
        entityManager.createNativeQuery("DELETE FROM role WHERE id = ?")
                .setParameter(1, roleId)
                .executeUpdate();
    }
}

package com.example.demo.service;

import com.example.demo.entity.Roles;
import com.example.demo.enums.RoleName;
import com.example.demo.exceptions.RoleNotFound;
import com.example.demo.repository.RolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    private static final Logger logger = LoggerFactory.getLogger(RolesService.class);
    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Roles findRoleByNameOrThrow(RoleName role) {
        return rolesRepository.findByName(role)
                .orElseThrow(() -> {
                    logger.warn("ROLE {} IS NOT FOUND", role);
                    return new RoleNotFound("Role {} not found", role.name());
                });
    }
}

package com.ercan.services.impl;

import com.ercan.models.Role;
import com.ercan.repositories.RoleRepository;
import com.ercan.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public Role save(Role role) {
        if (!roleRepository.existsRolesByRoleName(role.getRoleName())) {
            return roleRepository.save(role);
        }
        return roleRepository.getRoleByRoleName(role.getRoleName());
    }

}

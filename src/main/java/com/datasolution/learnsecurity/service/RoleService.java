package com.datasolution.learnsecurity.service;

import com.datasolution.learnsecurity.entity.Role;
import com.datasolution.learnsecurity.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    public Role getRoleById(String roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    public Role regRole(Role role) {
        return roleRepository.save(role);
    }
}

package com.datasolution.learnsecurity.controller;

import com.datasolution.learnsecurity.entity.Role;
import com.datasolution.learnsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/list")
    public List<Role> getRoleList() {
        return roleService.getRoleList();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{roleId}")
    public Role getRoleById(@PathVariable String roleId) {
        return roleService.getRoleById(roleId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reg")
    public Role regRole(@RequestParam Role role) {
        return roleService.regRole(role);
    }
}

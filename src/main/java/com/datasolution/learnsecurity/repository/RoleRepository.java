package com.datasolution.learnsecurity.repository;

import com.datasolution.learnsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleId(String roleId);
    boolean existsByRoleId(String roleId);
}

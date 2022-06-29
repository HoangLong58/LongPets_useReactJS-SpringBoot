package com.longpets.longpetsecommerce.data.repository;

import com.longpets.longpetsecommerce.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}

package com.example.PURCHASE.REQUEST.repository;

import com.example.PURCHASE.REQUEST.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

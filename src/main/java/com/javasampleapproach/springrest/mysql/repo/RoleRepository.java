package com.javasampleapproach.springrest.mysql.repo;

import com.javasampleapproach.springrest.mysql.model.Role;
import com.javasampleapproach.springrest.mysql.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
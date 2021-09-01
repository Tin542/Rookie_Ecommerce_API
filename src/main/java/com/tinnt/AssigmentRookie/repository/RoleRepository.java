package com.tinnt.AssigmentRookie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinnt.AssigmentRookie.constans.ERole;
import com.tinnt.AssigmentRookie.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole roleName);
}

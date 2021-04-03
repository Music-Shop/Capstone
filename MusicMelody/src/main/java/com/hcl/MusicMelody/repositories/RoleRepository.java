package com.hcl.MusicMelody.repositories;

import org.springframework.stereotype.Repository;

import com.hcl.MusicMelody.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}

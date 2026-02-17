package com.wedding.kota_riho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wedding.kota_riho.entity.GuestRoleEntity;

public interface GuestRoleRepository extends JpaRepository<GuestRoleEntity, Long> {
	
	Optional<GuestRoleEntity> findByLastNameAndFirstNameAndGuestSideAndRelation(
		    String lastName,
		    String firstName,
		    String guestSide,
		    String relation
		);

	
}

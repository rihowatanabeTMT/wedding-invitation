package com.wedding.kota_riho.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedding.kota_riho.entity.GuestRoleEntity;
import com.wedding.kota_riho.form.RsvpForm;
import com.wedding.kota_riho.repository.GuestRoleRepository;

@Service
public class GuestRoleService {
	
	@Autowired
	private GuestRoleRepository guestRoleRepository;

	public String detectRole(RsvpForm form) {
		Optional<GuestRoleEntity> entity =
			    guestRoleRepository.findByLastNameAndFirstNameAndGuestSideAndRelation(
			        form.getLastName(),
			        form.getFirstName(),
			        form.getGuestSide(),
			        form.getRelation()
			    );

				
		return entity.map(GuestRoleEntity::getRole).orElse("none");

	}
}

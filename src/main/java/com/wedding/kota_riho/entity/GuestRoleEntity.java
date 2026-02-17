package com.wedding.kota_riho.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Table(name = "guest_roles")
@Entity
public class GuestRoleEntity implements Serializable {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private String lastName;	
	 private String firstName;
	 private String guestSide;
	 private String relation;
	 private String role;

}

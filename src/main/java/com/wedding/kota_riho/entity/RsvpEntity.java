package com.wedding.kota_riho.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "rsvp")
public class RsvpEntity {
	
	public RsvpEntity() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String furigana;
	private String attendance;
	private String phone;
	private String email;
	private String post;
	private String address;
	private String hotel;
	private String bus;
	private String allergy;
	private String allergyText;
	private String message;
	
		@OneToMany(mappedBy = "rsvpEntity", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<PlusoneEntity> plusones = new ArrayList<>();

}

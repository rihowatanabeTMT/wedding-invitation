package com.wedding.kota_riho.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "guests")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String firstName;

    private String lastNameKana;
    private String firstNameKana;

    private LocalDate birthday;
    private String allergy;
    private String side;

    private Long rsvpId;
    
    private String fullRelation;
    // getter/setter
}
package com.wedding.kota_riho.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "plus_one")
public class PlusoneEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plusOneName;
    private String plusOneFurigana;
    private String plusOneAllergy;

    @ManyToOne
    @JoinColumn(name = "rsvp_id")
    private RsvpEntity rsvpEntity;
}
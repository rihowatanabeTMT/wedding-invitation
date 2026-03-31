package com.wedding.kota_riho.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "seats")
@Data
public class SeatEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableId;
    private Integer positionNo;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;

    // getter/setter

}
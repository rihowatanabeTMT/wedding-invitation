package com.wedding.kota_riho.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class RsvpEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 名前
    private String lastName;
    private String firstName;
    private String lastNameKana;
    private String firstNameKana;
    private String lastNameEn;
    private String firstNameEn;

    // 新郎新婦どちら側
    private String guestSide;

    // 関係性
    private String relation;

    // 出欠
    private String attendance;

    // 連絡先
    private String phone;
    private String email;

    // 住所
    private String post;
    private String prefecture;
    private String address;
    private String building;

    // 宿泊・バス
    private String hotel;
    private String bus;

    // アレルギー
    private String allergy;
    private String allergyText;

    // 同伴者
    @OneToMany(mappedBy = "rsvpEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlusoneEntity> plusones = new ArrayList<>();

    // メッセージ
    @Column(columnDefinition = "TEXT")
    private String message;
    
    //役割(受付・乾杯)
    private String role;
}

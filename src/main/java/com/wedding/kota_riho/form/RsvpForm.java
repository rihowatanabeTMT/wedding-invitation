package com.wedding.kota_riho.form;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RsvpForm implements Serializable {
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

	private List<PlusoneForm> plusones;
}

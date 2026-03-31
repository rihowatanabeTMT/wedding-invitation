package com.wedding.kota_riho.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wedding.kota_riho.form.GuestSeatDto;
import com.wedding.kota_riho.service.GuestService;
import com.wedding.kota_riho.service.SeatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InfoController {
	
	
	private final SeatService seatService;
	private final GuestService guestService;
	
	@GetMapping("/info")
	public String info() {
		return "info/info";
	}
	
	@GetMapping("/info2")
	public String info2() {
		return "info/info2";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "info/guide/menu";
	}
	
	@GetMapping("/seat")
	public String seat(Model model) {

	    List<GuestSeatDto> groomGuests = guestService.getGroomGuests();
	    List<GuestSeatDto> brideGuests = guestService.getBrideGuests();

	    model.addAttribute("groomGuests", groomGuests);
	    model.addAttribute("brideGuests", brideGuests);

	    return "info/guide/seat";
	}

}

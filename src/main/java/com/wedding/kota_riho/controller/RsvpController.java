package com.wedding.kota_riho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wedding.kota_riho.form.RsvpForm;
import com.wedding.kota_riho.service.RsvpService;


@Controller
public class RsvpController {
	/**
	 * pg_ctl -D C:\pgsql\data -l logfile start
	 */
	
	private final RsvpService rsvpService;

    public RsvpController(RsvpService rsvpService) {
        this.rsvpService = rsvpService;
    }

    
	@GetMapping("/rsvp")
	public String getTop(Model model) {
		model.addAttribute("rsvpForm",new RsvpForm());
		return "rsvp/rsvp";
	}
	
	
	@PostMapping("/rsvp/confirm")
	public String confirm(@ModelAttribute RsvpForm rsvpForm, Model model) {
		model.addAttribute("rsvpForm", rsvpForm);
		return "rsvp/rsvpConfirm";
	}
	
	@PostMapping("rsvp/submit")
	 public String submit(@ModelAttribute RsvpForm rsvpForm) {
        rsvpService.saveRsvp(rsvpForm);
        return "redirect:/rsvp/complete";
    }

	// 完了画面
    @GetMapping("/rsvp/complete")
    public String complete() {
        return "rsvp/rsvpComplete";
    }

}

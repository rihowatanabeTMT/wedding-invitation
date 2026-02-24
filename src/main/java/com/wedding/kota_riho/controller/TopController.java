package com.wedding.kota_riho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TopController {
	
	@GetMapping("/top")
	public String top(@RequestParam(required = false) String from) {
	    if ("groom".equals(from)) {
	        return "top/topGroom"; // TopGroom.html
	    } else if ("bride".equals(from)) {
	        return "top/topBride"; // TopBride.html
	    } else {
	        return "top/top"; // 通常版
	    }
	}


	
}

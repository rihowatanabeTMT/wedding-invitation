package com.wedding.kota_riho.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.wedding.kota_riho.entity.PlusoneEntity;
import com.wedding.kota_riho.entity.RsvpEntity;
import com.wedding.kota_riho.form.PlusoneForm;
import com.wedding.kota_riho.form.RsvpForm;
import com.wedding.kota_riho.repository.RsvpRepository;

@Service
public class RsvpService {

	private RsvpRepository repo;
	
	public RsvpService(RsvpRepository rsvpRepository) {
	        this.repo = rsvpRepository;
	    }
	
	@Transactional
	public void saveRsvp(RsvpForm form) {
		 // --- RsvpEntity を作成 ---
        RsvpEntity rsvp = new RsvpEntity();
        rsvp.setName(form.getName());
        rsvp.setFurigana(form.getFurigana());
        rsvp.setAttendance(form.getAttendance());
        rsvp.setPhone(form.getPhone());
        rsvp.setEmail(form.getEmail());
        rsvp.setPost(form.getPost());
        rsvp.setAddress(form.getAddress());
        rsvp.setHotel(form.getHotel());
        rsvp.setBus(form.getBus());
        rsvp.setAllergy(form.getAllergy());
        rsvp.setAllergyText(form.getAllergyText());
        rsvp.setMessage(form.getMessage());

        // --- 同伴者（Plusone）を Entity に変換 ---
        if (form.getPlusones() != null) {
            for (PlusoneForm p : form.getPlusones()) {
                PlusoneEntity plus = new PlusoneEntity();
                plus.setPlusOneName(p.getPlusOneName());
                plus.setPlusOneFurigana(p.getPlusOneFurigana());
                plus.setPlusOneAllergy(p.getPlusOneAllergy());

                plus.setRsvpEntity(rsvp);          // 親をセット
                rsvp.getPlusones().add(plus); // 親のリストに追加
            }
        }
        
        repo.save(rsvp);

	}

}

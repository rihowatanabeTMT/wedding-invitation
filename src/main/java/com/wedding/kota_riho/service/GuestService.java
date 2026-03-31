package com.wedding.kota_riho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wedding.kota_riho.entity.GuestEntity;
import com.wedding.kota_riho.form.GuestSeatDto;
import com.wedding.kota_riho.repository.GuestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestService {

	  private final GuestRepository guestRepository;

	    /** 新郎ゲスト（席情報付き） */
	    public List<GuestSeatDto> getGroomGuests() {
	        return guestRepository.findGuestsWithTable("groom");
	    }

	    /** 新婦ゲスト（席情報付き） */
	    public List<GuestSeatDto> getBrideGuests() {
	        return guestRepository.findGuestsWithTable("bride");
	    }


    public GuestEntity findById(Long id) {
        return guestRepository.findById(id);
    }

    public void save(GuestEntity guest) {
        guestRepository.save(guest);
    }
}

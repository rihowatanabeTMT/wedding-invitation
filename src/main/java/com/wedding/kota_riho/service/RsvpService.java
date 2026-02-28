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

    private final RsvpRepository repo;

    public RsvpService(RsvpRepository rsvpRepository) {
        this.repo = rsvpRepository;
    }

    @Transactional
    public void saveRsvp(RsvpForm form) {

        // --- RsvpEntity を作成 ---
        RsvpEntity rsvp = new RsvpEntity();

        // 名前
        rsvp.setLastName(form.getLastName());
        rsvp.setFirstName(form.getFirstName());
        rsvp.setLastNameKana(form.getLastNameKana());
        rsvp.setFirstNameKana(form.getFirstNameKana());
        rsvp.setLastNameEn(form.getLastNameEn());
        rsvp.setFirstNameEn(form.getFirstNameEn());

        // 新郎新婦どちら側
        rsvp.setGuestSide(form.getGuestSide());

        // 関係性
        rsvp.setRelation(form.getRelation());

        // 出欠
        rsvp.setAttendance(form.getAttendance());

        // 連絡先
        rsvp.setPhone(form.getPhone());
        rsvp.setEmail(form.getEmail());

        // 住所
        rsvp.setPost(form.getPost());
        rsvp.setPrefecture(form.getPrefecture());
        rsvp.setAddress(form.getAddress());
        rsvp.setBuilding(form.getBuilding());

        // 宿泊・バス
        rsvp.setHotel(form.getHotel());
        rsvp.setBus(form.getBus());

        // アレルギー
        rsvp.setAllergy(form.getAllergy());
        rsvp.setAllergyText(form.getAllergyText());

        // メッセージ
        rsvp.setMessage(form.getMessage());

        // --- 同伴者（Plusone）を Entity に変換 ---
        if (form.getPlusones() != null) {
            for (PlusoneForm p : form.getPlusones()) {

                PlusoneEntity plus = new PlusoneEntity();
                plus.setPlusOneName(p.getPlusOneName());
                plus.setPlusOneFurigana(p.getPlusOneFurigana());
                plus.setPlusOneAllergy(p.getPlusOneAllergy());

                plus.setRsvpEntity(rsvp);      // 親をセット
                rsvp.getPlusones().add(plus);  // 親のリストに追加
            }
        }
        
        rsvp.setRole(form.getRole());

        // --- 保存 ---
        repo.save(rsvp);
    }
}
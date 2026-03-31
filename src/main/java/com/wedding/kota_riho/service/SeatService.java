package com.wedding.kota_riho.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wedding.kota_riho.entity.SeatEntity;
import com.wedding.kota_riho.repository.SeatRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    public Map<String, List<SeatEntity>> getSeatsGroupedByTable() {

        List<SeatEntity> seats = seatRepository.findAllWithGuest();

        return seats.stream()
                .collect(Collectors.groupingBy(SeatEntity::getTableId));
    }

    public SeatEntity findById(Long id) {
        return seatRepository.findById(id);
    }

    public void save(SeatEntity seat) {
        seatRepository.save(seat);
    }

    public List<SeatEntity> findAll() {
        return seatRepository.findAllWithGuest();
    }
}


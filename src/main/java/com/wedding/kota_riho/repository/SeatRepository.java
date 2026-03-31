package com.wedding.kota_riho.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.wedding.kota_riho.entity.SeatEntity;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class SeatRepository {

    @PersistenceContext
    private final EntityManager em;

    /** 全席＋ゲストJOIN */
    public List<SeatEntity> findAllWithGuest() {
        return em.createQuery(
                "SELECT s FROM SeatEntity s LEFT JOIN FETCH s.guest g ORDER BY s.tableId, s.positionNo",
                SeatEntity.class
        ).getResultList();
    }

    public SeatEntity findById(Long id) {
        return em.find(SeatEntity.class, id);
    }

    public void save(SeatEntity seat) {
        if (seat.getId() == null) {
            em.persist(seat);
        } else {
            em.merge(seat);
        }
    }
}

package com.wedding.kota_riho.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.wedding.kota_riho.entity.GuestEntity;
import com.wedding.kota_riho.form.GuestSeatDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GuestRepository {

    @PersistenceContext
    private final EntityManager em;

    /** ID検索 */
    public GuestEntity findById(Long id) {
        return em.find(GuestEntity.class, id);
    }

    public List<GuestSeatDto> findGuestsWithTable(String side) {
        return em.createQuery("""
                SELECT new com.wedding.kota_riho.form.GuestSeatDto(
                    g.id,
                    g.lastName,
                    g.firstName,
                    s.tableId,
                    s.positionNo,
                    g.fullRelation
                )
                FROM SeatEntity s
                JOIN s.guest g
                WHERE g.side = :side
                ORDER BY s.tableId, s.positionNo
                """, GuestSeatDto.class)
            .setParameter("side", side)
            .getResultList();
    }

    /** 保存 */
    public void save(GuestEntity guest) {
        if (guest.getId() == null) {
            em.persist(guest);
        } else {
            em.merge(guest);
        }
    }
}

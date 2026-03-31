package com.wedding.kota_riho.form;

public record GuestSeatDto(
        Long id,
        String lastName,
        String firstName,
        String table,
        Integer positionNo,
        String fullRelation
) {}


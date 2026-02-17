package com.wedding.kota_riho.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class PlusoneForm {

    @NotBlank
    private String plusOneName;

    @NotBlank
    private String plusOneFurigana;

    private String plusOneAllergy;
}


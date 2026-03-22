package com.wedding.kota_riho.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.Data;
@Data
public class PlusoneForm {

    @NotBlank
    private String plusOneName;

    @NotBlank
    private String plusOneFurigana;

    private String plusOneAllergy;

    @NotNull(message = "誕生日を入力してください")
    @Past(message = "誕生日は過去の日付を入力してください")
    private LocalDate plusOneBirth;
}


package com.wedding.kota_riho.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllergyDetailValidator.class)
public @interface AllergyDetailRequired {
    String message() default "アレルギー詳細を入力してください";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


package com.wedding.kota_riho.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.wedding.kota_riho.form.RsvpForm;

public class AllergyDetailValidator implements ConstraintValidator<AllergyDetailRequired, RsvpForm> {

	@Override
	public boolean isValid(RsvpForm rsvpForm, ConstraintValidatorContext context) {
		
		if("yes".equals(rsvpForm.getAllergy())){
			return rsvpForm.getAllergyText() != null && !rsvpForm.getAllergyText().isBlank();
        }

        return true; 
	}

}

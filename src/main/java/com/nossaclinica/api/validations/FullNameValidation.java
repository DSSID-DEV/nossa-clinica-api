package com.nossaclinica.api.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nossaclinica.api.validations.anotations.FullName;

public class FullNameValidation implements ConstraintValidator<FullName, String>{
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String[] values = value.split(" ");
		return values.length > 2;
	}

}

package com.nossaclinica.api.validations.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nossaclinica.api.validations.FullNameValidation;

@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, 
	ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FullNameValidation.class)
public @interface FullName {
	
	String message() default "Por favor informe o seu nome completo!";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	

}

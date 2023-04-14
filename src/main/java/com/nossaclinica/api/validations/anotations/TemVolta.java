package com.nossaclinica.api.validations.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE,  
	ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = TemDireitoAVolta.class)
public @interface TemVolta {
	String message() default "O cliente não tem direito a volta referente a ultima consulta!";
	
	Class<?>[] groups() default {};
	
//	Class<? extends Payload>[] payload() default{}; 
}

package com.spice.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.spice.validator.CircleValidator;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CircleValidator.class)
@Documented
public @interface ValidateCircles {
	String[] acceptedValues();

    String message() default "{1002}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

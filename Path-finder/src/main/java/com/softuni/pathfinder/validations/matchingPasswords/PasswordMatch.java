package com.softuni.pathfinder.validations.matchingPasswords;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordMatcher.class)
public @interface PasswordMatch {
	String password();

	String confirmPassword();

	String message()

	default "Passwords miss match";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

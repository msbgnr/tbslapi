package com.biggergames.tbslapi.validation.constraints;

import com.biggergames.tbslapi.validation.validator.MatchScoreValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = MatchScoreValidator.class)
@Documented
public @interface MatchScore {

    String message() default "Body ifadesini kontrol ediniz";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
package com.tlc.crm.crud.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom annotation for department.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentValidator.class)
@Documented
public @interface Department {

    String message() default "invalid_Department";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

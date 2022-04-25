package com.tlc.crm.crud.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validating the department name.
 */
public class DepartmentValidator implements ConstraintValidator<Department, String> {

    /**
     * Checks the department name is valid or not.
     *
     * @param department
     * @param constraintValidatorContext
     */
    @Override
    public boolean isValid(final String department, final ConstraintValidatorContext constraintValidatorContext) {
        return department.matches("(?i)CSE|IT|ECE|MECH|EEE");
    }
}

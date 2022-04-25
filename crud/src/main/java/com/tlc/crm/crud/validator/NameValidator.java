package com.tlc.crm.crud.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validating the name.
 */
public class NameValidator implements ConstraintValidator<Name, String> {

    /**
     * Validating the name is valid or not.
     *
     * @param name
     * @param constraintValidatorContext
     */
    @Override
    public boolean isValid(final String name, final ConstraintValidatorContext constraintValidatorContext) {
        return name.matches("[a-zA-Z]{1,}");
    }
}

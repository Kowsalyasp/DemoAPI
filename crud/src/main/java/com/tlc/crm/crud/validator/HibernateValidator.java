package com.tlc.crm.crud.validator;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.crud.model.Student;
import com.tlc.crm.crud.status.StudentErrorCodes;
import com.tlc.validator.ModelValidator;
import com.tlc.validator.ValidatorAccess;

/**
 * Hibernate validation for student data.
 *
 * @author KowsalyaSP
 */
public class HibernateValidator {

    private static final ModelValidator VALIDATOR = ValidatorAccess.get();

    /**
     * Validates the Student data is valid or not.
     *
     * @param student
     * @param classType
     */
    public static void validate(final Student student, final Class... classType) {

        if (!VALIDATOR.isValid(student, classType)) {
            throw ErrorCode.get(StudentErrorCodes.VALIDATION_FAILED);
        }
    }
}

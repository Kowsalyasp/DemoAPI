package com.tlc.crm.crud.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * Error Code for Student Data by implementing the Error code provider and group.
 *
 * @author KowsalyaSP
 */
public enum StudentErrorCodes implements ErrorCodeProvider {

    STUDENT_ID_NOT_FOUND(0x01),
    VALIDATION_FAILED(0x05);

    private final int code;

    StudentErrorCodes(final int code) {
        this.code = StudentErrorCodeGroup.STUDENT_ERROR_CODE_GROUP.getConvertedCode(code);
    }

    /**
     * Gets the error code.
     *
     * @return code
     */
    @Override
    public int getCode() {
        return code;
    }

    /**
     * Creating an error code group.
     */
    private static class StudentErrorCodeGroup implements ErrorCodeGroup {
        private static final StudentErrorCodeGroup STUDENT_ERROR_CODE_GROUP = new StudentErrorCodeGroup();

        /**
         * Gets prefix for the error code.
         */
        @Override
        public int getPrefix() {
            return 0x10_0_0000;
        }
    }
}

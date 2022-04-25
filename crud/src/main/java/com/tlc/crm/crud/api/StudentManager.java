package com.tlc.crm.crud.api;

import com.tlc.commons.code.ErrorCode;

import com.tlc.crm.common.config.AuditEntry;
import com.tlc.crm.common.config.ConfigManager;

import com.tlc.crm.crud.model.Student;
import com.tlc.crm.crud.status.StudentErrorCodes;

import com.tlc.sql.SQLAccess;
import com.tlc.sql.api.DataContainer;
import com.tlc.sql.api.Row;
import com.tlc.sql.api.dml.*;
import com.tlc.sql.api.ds.OrgDataStore;

import java.util.*;

/**
 * Implementing the Student management for crud operation.
 *
 * @author KowsalyaSP
 * @version 1.0
 */
public class StudentManager implements ConfigManager<Student> {

    private static final Table TABLE = Table.get("StudentData");
    private static final OrgDataStore ORG_DATA_STORE = SQLAccess.get().getOrgDataStore(1L);

    /**
     * Creating an instance for the StudentManager.
     */
    private static class Instance {
        private static final StudentManager INSTANCE = new StudentManager();
    }

    private StudentManager() {
    }

    /**
     * Get instance.
     */
    public static StudentManager getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * Inserting a data to tha database.
     *
     * @param student
     */
    @Override
    public void create(final Student student) {
        final Row row = new Row(TABLE);
        final DataContainer dataContainer = DataContainer.create();

        row.set("NAME", student.getName());
        row.set("DEPARTMENT", student.getDepartment());
        dataContainer.addNewRow(row);
        ORG_DATA_STORE.commitChanges(dataContainer);
        student.setId(row.getPKValue());
    }

    /**
     * Inserting a collection of data to the database.
     *
     * @param students
     */
    public void create(final Collection<Student> students) {
        final DataContainer dataContainer = DataContainer.create();

        for (final Student student : students) {
            final Row row = new Row(TABLE);

            row.set("NAME", student.getName());
            row.set("DEPARTMENT", student.getDepartment());
            dataContainer.addNewRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
            student.setId(row.getPKValue());
        }
    }

    /**
     * Updating the student data.
     *
     * @param student
     */
    @Override
    public void update(final Student student) {
        final Row row = new Row(TABLE, student.id());
        final DataContainer dataContainer = DataContainer.create();

        if (exists(student)) {
            row.set("NAME", student.getName());
            row.set("DEPARTMENT", student.getDepartment());
            dataContainer.updateRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        } else {
            throw ErrorCode.get(StudentErrorCodes.STUDENT_ID_NOT_FOUND);
        }
    }

    /**
     * Deleting the data from model using the id.
     *
     * @param student
     */
    @Override
    public void delete(final Student student) {

        if (exists(student)) {
            delete(List.of(student));
        } else {
            throw ErrorCode.get(StudentErrorCodes.STUDENT_ID_NOT_FOUND);
        }
    }

    /**
     * Checks if it is exists or not, and it returns in boolean format.
     *
     * @param student
     */
    @Override
    public boolean exists(final Student student) {
        return ORG_DATA_STORE.get(TABLE, student.getId()) != null ? true : false;
    }

    /**
     * Deleting the student data.
     *
     * @param students
     */
    @Override
    public void delete(final Collection<Student> students) {
        final Collection<Long> id = new HashSet<>();

        students.forEach(studentId -> id.add(studentId.id()));
        ORG_DATA_STORE.delete(TABLE, id);
    }

    /**
     * Set the id and it returns to delete method.
     *
     * @param id
     */
    @Override
    public Student partialGet(final Long id) {
        final Student student = new Student();

        student.setId(id);
        return student;
    }

    /**
     * Get the student id.
     *
     * @param id
     */
    @Override
    public Student get(final Long id) {
        final Student student = new Student();
        final Row row = ORG_DATA_STORE.get(TABLE, id);

        student.setId(row.get("ID"));
        student.setName(row.get("NAME"));
        student.setDepartment(row.get("DEPARTMENT"));

        return student;
    }

    /**
     * Get the student values.
     *
     * @param id
     */
    @Override
    public Collection<Student> get(final Collection<Long> id) {
        return null;
    }

    @Override
    public AuditEntry auditEntry(final Student student) {
        return null;
    }
}

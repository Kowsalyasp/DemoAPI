package com.tlc.crm.crud.model;

import com.tlc.crm.crud.validator.Department;
import com.tlc.crm.crud.validator.Name;
import com.tlc.validator.TlcModel;
import com.tlc.validator.type.Group.Update;
import com.tlc.validator.type.Group.Create;

/**
 * Get and set the student data.
 *
 * @author KowsalyaSP
 * @version 1.0
 */
public class Student implements TlcModel {

    private Long id;

    @Name(message = "Name", groups = {Create.class, Update.class})
    private String name;

    @Department(message = "Department", groups = {Create.class, Update.class})
    private String department;

    public Student() {
        super();
    }

    public Student(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Object identity() {
        return null;
    }

    public String toString() {
        return String.format("ID%s \n NAME%s \n DEPARTMENT%s", id, name, department);
    }
}

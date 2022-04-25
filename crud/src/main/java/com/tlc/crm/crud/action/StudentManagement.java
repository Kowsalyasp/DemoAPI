package com.tlc.crm.crud.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;

import com.tlc.crm.common.action.secure.CrmConfigAction;
import com.tlc.crm.common.config.ConfigManager;

import com.tlc.crm.crud.model.Student;
import com.tlc.crm.crud.api.StudentManager;
import com.tlc.crm.crud.validator.HibernateValidator;

import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;

import com.tlc.web.WebAction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Api implementation for construct the student data.
 *
 * @author KowsalyaSP
 * @version 1.0
 */
@WebAction(path = "/crud/mgmt")
public class StudentManagement extends CrmConfigAction<Student> {

    /**
     * creating an instance for config manager.
     */
    @Override
    public ConfigManager<Student> getConfigManager() {
        return StudentManager.getInstance();
    }

    /**
     * Convert in to student object by passing the json object.
     *
     * @param jsonObject
     */
    @Override
    public Student construct(final JsonObject jsonObject) {
        final Long id = jsonObject.optLong("id", 0);
        final String name = jsonObject.optString("name", null);
        final String department = jsonObject.optString("department", null);
        final String type = jsonObject.getString("type");
        final Student student = new Student(id, name, department);

        if (type.equals("create") || type.equals("update")) {
            HibernateValidator.validate(student, getGroups(type));
        }
        return student;
    }

    /**
     * Get collection object by passing the json object.
     *
     * @param jsonObject
     */
    public Collection<Student> constructArray(final JsonObject jsonObject) {
        final Collection<Student> studentList = new ArrayList<>();
        final JsonArray dataArray = jsonObject.getJsonArray("values");

        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject Object = dataArray.getJsonObject(i);
            final Long id = Object.optLong("id", 0);
            final String name = Object.optString("name", null);
            final String department = Object.optString("department", null);
            final String type = jsonObject.getString("type");
            final Student student = new Student(id, name, department);

            if (type.equals("create") || type.equals("update")) {

                try {
                    HibernateValidator.validate(student, getGroups(type));
                    studentList.add(student);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return studentList;
    }

    /**
     * Get groups.
     *
     * @param type
     */
    private Class getGroups(final String type) {

        if (type.equals("create")) {
            return Create.class;
        }
        return Update.class;
    }

    /**
     * Converting student data to json object.
     *
     * @param model
     */
    @Override
    public JsonObject construct(final Student model) {
        final JsonObject jsonObj = Json.object();

        jsonObj.put(("id"), model.id());
        jsonObj.put(("name"), model.getName());
        jsonObj.put(("department"), model.getDepartment());

        return jsonObj;
    }
}

package com.tlc.crm;

import com.tlc.crm.crud.model.Student;
import com.tlc.crm.crud.api.StudentManager;
import com.tlc.sql.api.dml.Table;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void start() {
        //System.out.println(Table.get("StudentData"));
        int choice;
        do {
            System.out.println(" \n 1.Add Student \n 2.Search Student \n 3.Remove Student \n 4.Update student \n 5.ShowAll Students \n 6. Exit");
            choice = SCANNER.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    // CrudManager.getInstance().get();
                    break;
                case 3:
                    //StudentManager.getInstance().delete(new Student());
                    break;
                case 4:
                    //StudentManager.getInstance().update(new Student());
                    break;
            }
        } while (choice < 5);
    }

    public static void addStudent() {
        Long id = SCANNER.nextLong();// don't want for this
        System.out.println("Enter Name");
        String name = SCANNER.next();
        System.out.println("Enter Department");
        String department = SCANNER.next();
       Student studentData = new Student(id, name, department);// don't want for this we can use two consructor.
       // StudentManager.getInstance().create(studentData);
    }
}


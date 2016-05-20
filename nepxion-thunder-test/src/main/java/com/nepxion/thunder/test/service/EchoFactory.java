package com.nepxion.thunder.test.service;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import com.nepxion.thunder.test.entity.Student;
import com.nepxion.thunder.test.entity.Teacher;

public class EchoFactory {
    public static String create250Byte() {
        return "12345678901234567890";
    }

    public static Teacher create2496Byte() {
        Teacher teacher = new Teacher();
        List<Student> slist = new ArrayList<Student>();
        for (int j = 0; j < 20; j++) {
            slist.add(new Student());
        }
        teacher.setStudentList(slist);

        return teacher;
    }

    public static List<Teacher> create53518Byte() {
        List<Teacher> tlist = new ArrayList<Teacher>();
        for (int i = 0; i < 10; i++) {
            Teacher teacher = new Teacher();
            List<Student> slist = new ArrayList<Student>();
            for (int j = 0; j < 50; j++) {
                slist.add(new Student());
            }
            teacher.setStudentList(slist);
            tlist.add(teacher);
        }

        return tlist;
    }

    public static List<Teacher> create210748Byte() {
        List<Teacher> tlist = new ArrayList<Teacher>();
        for (int i = 0; i < 20; i++) {
            Teacher teacher = new Teacher();
            List<Student> slist = new ArrayList<Student>();
            for (int j = 0; j < 100; j++) {
                slist.add(new Student());
            }
            teacher.setStudentList(slist);
            tlist.add(teacher);
        }

        return tlist;
    }

    public static List<Teacher> create1041538Byte() {
        List<Teacher> tlist = new ArrayList<Teacher>();
        for (int i = 0; i < 10; i++) {
            Teacher teacher = new Teacher();
            List<Student> slist = new ArrayList<Student>();
            for (int j = 0; j < 1000; j++) {
                slist.add(new Student());
            }
            teacher.setStudentList(slist);
            tlist.add(teacher);
        }

        return tlist;
    }
}
package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    Map<String, Student> studentMap = new HashMap();
    Map<String, Teacher> teacherMap = new HashMap<>();
    Map<Student, Teacher> teacherStudentMap = new HashMap<>();

    public void addStudent(Student student) {
      studentMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        teacherStudentMap.put(studentMap.get(student), teacherMap.get(teacher));
    }


    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return  teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> students = new ArrayList<>();
        for(Student s : teacherStudentMap.keySet()){
            if(teacherStudentMap.get(s).getName().equals(teacher)){
                students.add(s.getName());
            }
        }
        return students;
    }

    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        for(String s : studentMap.keySet()){
            students.add(s);
        }
        return students;
    }

    public void deleteTeacherByName(String teacher) {
        teacherMap.remove(teacher);
        Iterator<Map.Entry<Student, Teacher>> itr = teacherStudentMap.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<Student, Teacher> entry = itr.next();
            if(entry.getValue().getName().equals(teacher)){
                studentMap.remove(entry.getKey().getName());
                itr.remove();
            }
        }
    }

    public void deleteAllTeachers() {
        Iterator<Map.Entry<Student, Teacher>> itr = teacherStudentMap.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<Student, Teacher> entry = itr.next();
            studentMap.remove(entry.getKey().getName());
            teacherMap.remove(entry.getValue().getName());
            itr.remove();
        }
    }
}

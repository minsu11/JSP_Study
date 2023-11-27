package com.example.studentmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRepository implements StudentRepository {
    private Map<String, Student> studentMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public void deleteById(String id) {
        // null 체크
        studentMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    @Override
    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        for (Student student : studentMap.values()) {
            list.add(student);
        }
        return list;
    }


    @Override
    public boolean existById(String id) {
        return studentMap.containsKey(id);
    }
}

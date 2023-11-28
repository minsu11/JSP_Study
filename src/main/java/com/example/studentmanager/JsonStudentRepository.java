package com.example.studentmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;
    // json file 저장 경로
    private static final String JSON_FILE_PATH = "/Users/minsu/Desktop/StudentManager/src/main/java/com/example/studentmanager/json/student.json";

    public JsonStudentRepository() {
        this.objectMapper = new ObjectMapper();
        //LocalDateTime json 직렬화/역직렬화 가능하도록 설정
        objectMapper.registerModules(new JavaTimeModule());
        // json 파일 존재 시 json file 삭제
        File file = new File(JSON_FILE_PATH);
        if (file.exists() && !file.isDirectory()) {
            log.error("파일 삭제");
            file.delete();
        }
    }

    //    private synchronized List<Student> readJsonFile() {
//        // todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
//        File file = new File(JSON_FILE_PATH);
//        List<Student> students = new ArrayList<>();
//        if (file.exists() && !file.isDirectory()) {
//            return students;
//        }
//        // json read & 역직렬화( json string -> object)
//        try (
//                FileInputStream fileInputStream = new FileInputStream(file);
//                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        ) {
//            students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {
//            });
//            return students;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private synchronized List<Student> readJsonFile() {
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴

        //json read & 역직렬화 ( json string -> Object )
        File file = new File(JSON_FILE_PATH);

        if (!file.exists()) {
            List<Student> list = new LinkedList<>();
            log.error("파일 존재");
            return list;
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            log.error("쓰기 전");
            List<Student> students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {
            });
            log.error("쓰기 완료");
            return students;
        } catch (JsonProcessingException e) {
            log.error("readJsonFile jsonProcessing");
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("readJsonFile IOException");
            throw new RuntimeException(e);
        }

    }

    private synchronized void writeJsonFile(List<Student> studentList) {
        File file = new File(JSON_FILE_PATH);
        log.error("write 처음 실행");
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter, studentList);
        } catch (IOException e) {
            log.error("write ");
            throw new RuntimeException(e);
        }

    }


    @Override
    public void save(Student student) {
        List<Student> students = readJsonFile();
        students.add(student);
        log.error("add 하고난 뒤:{}", students.toString());
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();
        int idx = 0;
        for (Student st : students) {
            if (st.getId().equals(student.getId())) {
                st.setName(student.getName());
                st.setGender(student.getGender());
                st.setAge(student.getAge());
            }
        }
        writeJsonFile(students);
    }

    @Override
    public void deleteById(String id) {
        log.error("deleteById 호출 시작");
        log.error("id 갑이 넘어왔는지 확인:{}", id);
        List<Student> students = readJsonFile();
        log.error("students 생성");
        // null 체크, 값이 있는지, id null 체크
        int idx = 0;
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                log.error("delete 삭제하기 전");
                it.remove();
            }
        }

        writeJsonFile(students);
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        // null 체크, 값이 있는지, id null 체크
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> students = readJsonFile();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}

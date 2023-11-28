package com.example.studentmanager;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        String id = request.getParameter("id");
        if (Objects.isNull(id)) {
            throw new RuntimeException("parameter null");
        }
        String name = request.getParameter("name");
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        int age = Integer.parseInt(request.getParameter("age"));
        LocalDateTime localDateTime = LocalDateTime.now();
        Student student = new Student(id, name, gender, age, localDateTime);
        studentRepository.update(student);
        return "redirect/student/view.do?id=" + student.getId();
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//        if (Objects.isNull(id)) {
//            throw new RuntimeException("null");
//        }
//        String name = req.getParameter("name");
//        Gender gender = Gender.valueOf(req.getParameter("gender"));
//        int age = Integer.parseInt(req.getParameter("age"));
//
//        Student student = new Student(id, name, gender, age);
//
//        studentRepository.update(student);
////        resp.sendRedirect("/student/view?id=" + student.getId());
//        req.setAttribute("view", "redirect/student/view.do?id=" + student.getId());
//
//    }
}

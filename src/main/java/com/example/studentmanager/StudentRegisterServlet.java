package com.example.studentmanager;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentRegisterServlet implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        if (Objects.isNull(id)) {
            throw new RuntimeException("Parameter ID Null");
        }
        String name = request.getParameter("name");
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        int age = Integer.parseInt(request.getParameter("age"));
        LocalDateTime localDateTime = LocalDateTime.now();
        Student student = new Student(id, name, gender, age, localDateTime);
        studentRepository.save(student);

        return "redirect/student/view.do?id=" + student.getId();
    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("utf-8");
//        String id = req.getParameter("id");
//        log.error("register doPsot:{}", id);
//        if (Objects.isNull(id)) {
//            throw new RuntimeException("register id 값 null");
//        }
//        String name = req.getParameter("name");
//        Gender gender = Gender.valueOf(req.getParameter("gender"));
//        int age = Integer.parseInt(req.getParameter("age"));
//        Student student = new Student(id, name, gender, age);
//        studentRepository.save(student);
//        RequestDispatcher rd = req.getRequestDispatcher("student");
//        req.setAttribute("view", "redirect/student/view.do?id=" + student.getId());
//
//    }


}

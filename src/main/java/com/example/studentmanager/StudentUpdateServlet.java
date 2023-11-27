package com.example.studentmanager;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentUpdateServlet",
        urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new RuntimeException("Student not found");
        }
        // 학생 조회
        req.setAttribute("student", student);
//
//        RequestDispatcher rd = req.getRequestDispatcher("/student/register");
//        rd.forward(req, resp);
        req.setAttribute("view", "/student/register.jsp");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (Objects.isNull(id)) {
            throw new RuntimeException("null");
        }
        String name = req.getParameter("name");
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        int age = Integer.parseInt(req.getParameter("age"));

        Student student = new Student(id, name, gender, age);

        studentRepository.update(student);
//        resp.sendRedirect("/student/view?id=" + student.getId());
        req.setAttribute("view", "redirect/student/view.do?id=" + student.getId());

    }
}

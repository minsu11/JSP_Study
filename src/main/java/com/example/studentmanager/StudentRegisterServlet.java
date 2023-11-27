package com.example.studentmanager;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.error("init before ");
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student/register.jsp");
//        requestDispatcher.forward(req, resp);
        log.error("register init ");
        req.setAttribute("view", "/student/register.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        log.error("register doPsot:{}", id);
        if (Objects.isNull(id)) {
            throw new RuntimeException("register id 값 null");
        }
        String name = req.getParameter("name");
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        int age = Integer.parseInt(req.getParameter("age"));
        Student student = new Student(id, name, gender, age);
        studentRepository.save(student);
        RequestDispatcher rd = req.getRequestDispatcher("student");
        req.setAttribute("view", "redirect/student/view.do?id=" + student.getId());

    }


}

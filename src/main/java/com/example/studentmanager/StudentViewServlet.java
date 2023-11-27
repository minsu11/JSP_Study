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
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (Objects.isNull(id)) {
            throw new RuntimeException("parameter[id]: null");
        }

        String name = studentRepository.getStudentById(id).getName();
        Gender gender = studentRepository.getStudentById(id).getGender();

        int age = studentRepository.getStudentById(id).getAge();

        resp.setCharacterEncoding("utf-8");
        Student student = new Student(id, name, gender, age);

        req.setAttribute("student", student);
//        RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view", "/student/view.jsp");
    }


}

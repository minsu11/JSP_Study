package com.example.studentmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentListServlet", urlPatterns = "/student/studentManager")

public class StudentListServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.error("List init 하기전 ");
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);

//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/studentManager.jsp");
//
//        req.setAttribute("studentList", studentList);
//
//        requestDispatcher.forward(req, resp);

        req.setAttribute("view", "/student/studentManager.jsp");

    }

}

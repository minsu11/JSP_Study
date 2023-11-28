package com.example.studentmanager;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        String id = request.getParameter("id");
        if (Objects.isNull(id)) {
            throw new RuntimeException("Parameter [id]:Null");
        }
        Student student = studentRepository.getStudentById(id);
        request.setAttribute("student", student);

        return "/student/register.jsp";
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//
//        Student student = studentRepository.getStudentById(id);
//        if (Objects.isNull(student)) {
//            throw new RuntimeException("Student not found");
//        }
//        // 학생 조회
//        req.setAttribute("student", student);
////
////        RequestDispatcher rd = req.getRequestDispatcher("/student/register");
////        rd.forward(req, resp);
//        req.setAttribute("view", "/student/register.jsp");
//
//
//    }


}

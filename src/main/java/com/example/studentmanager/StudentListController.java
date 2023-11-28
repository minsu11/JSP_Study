package com.example.studentmanager;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentListController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        List<Student> studentList = studentRepository.getStudents();

        request.setAttribute("studentList", studentList);
        return "/student/studentManager.jsp";
    }
    //    private StudentRepository studentRepository;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        log.error("List init 하기전 ");
//        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Student> studentList = studentRepository.getStudents();
//        req.setAttribute("studentList", studentList);
//
////        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/studentManager.jsp");
////
////        req.setAttribute("studentList", studentList);
////
////        requestDispatcher.forward(req, resp);
//
//        req.setAttribute("view", "/student/studentManager.jsp");
//
//    }

}

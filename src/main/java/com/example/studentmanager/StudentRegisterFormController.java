package com.example.studentmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentRegisterFormController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        log.error("StudentRegisterFormController execute");

        return "/student/register.jsp";
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student/register.jsp");
////        requestDispatcher.forward(req, resp);
//        log.error("register init ");
//        req.setAttribute("view", "/student/register.jsp");
//
//    }

}

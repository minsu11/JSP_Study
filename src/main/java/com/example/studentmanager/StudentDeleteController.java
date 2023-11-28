package com.example.studentmanager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteController implements Command {

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("utf-8");
//        log.error(req.getParameter("id"));
//        String id = req.getParameter("id");
//        log.error("id:{}", id);
//
//        studentRepository.deleteById(id);
////        resp.sendRedirect("/studentManager");
//        req.setAttribute("view", "redirect/student/studentManager.do");
//
//    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        log.error("getParameter 호출하기 전");
        String id = request.getParameter("id");
        log.error("id:{}", id);
        studentRepository.deleteById(id);

        return "redirect/student/studentManager.do";
    }
}

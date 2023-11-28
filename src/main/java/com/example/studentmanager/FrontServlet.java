package com.example.studentmanager;

import static javax.servlet.RequestDispatcher.*;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PRFIX = "redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        log.error("넘어오는지 확인");
        try {
            // 실제 요청 처리할 servlet을 결정
            log.error("servletpath resolveServlet11:{}", req.getServletPath());
            Command command = resovleServlet(req.getServletPath(), req.getMethod());
            log.error("command resovleServlet"); // 여기까지
            String view = command.execute(req, resp);
            log.error("address:{}", view);
            RequestDispatcher rd = req.getRequestDispatcher(view);

            // 실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해줌
            log.error("error:{}", view.startsWith(REDIRECT_PRFIX));
            log.error("error:{}", view);
            if (view.startsWith(REDIRECT_PRFIX)) {
                log.error("redirect-url: {}", view.substring(REDIRECT_PRFIX.length()));
                // todo redirect로 시작하면 redirect로 처리

                resp.sendRedirect(view.substring(REDIRECT_PRFIX.length()));
            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위하

                rd = req.getRequestDispatcher(view);
                rd.include(req, resp);

            }
        } catch (Exception exception) {
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }

    }

    private Command resovleServlet(String servletPath, String method) {
        Command command = null;
        log.error("command");
        if ("/student/studentManager.do".equals(servletPath) &&
                "GET".equalsIgnoreCase(method)) {
            log.error("StudentList");
            command = new StudentListController();
        } else if ("/student/register.do".equals(servletPath) &&
                "GET".equalsIgnoreCase(method)) {
            log.error("student register get");
            command = new StudentRegisterFormController();
        } else if ("/student/register.do".equals(servletPath) &&
                "POST".equalsIgnoreCase(method)) {
            log.error("student register post");
            command = new StudentRegisterServlet();
        } else if ("/student/view.do".equals(servletPath) &&
                "GET".equalsIgnoreCase(method)) {
            log.error("view get");
            command = new StudentViewController();
        } else if ("/student/update.do".equals(servletPath) &&
                "GET".equalsIgnoreCase(method)) {
            log.error("update get");
            command = new StudentUpdateFormController();
        } else if ("/student/update.do".equals(servletPath) &&
                "POST".equalsIgnoreCase(method)) {
            log.error("updater post");
            command = new StudentUpdateController();
        } else if ("/student/delete.do".equals(servletPath) &&
                "POST".equalsIgnoreCase(method)) {
            log.error("delete ");
            command = new StudentDeleteController();
        }
        // todo 실행 할 servlet 결정하기
        return command;

    }


}

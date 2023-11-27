package com.example.studentmanager;

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
            log.error("servletpath resolveServlet:{}", req.getServletPath());
            String servletPath = resovleServlet(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
            rd.include(req, resp);

            // 실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해줌
            String view = (String) req.getAttribute("view");
            log.error("error:{}", view.startsWith(REDIRECT_PRFIX));
            log.error("error:{}", view);
            if (view.startsWith(REDIRECT_PRFIX)) {
                log.error("redirect-url: {}", view.substring(REDIRECT_PRFIX.length() + 1));
                // todo redirect로 시작하면 redirect로 처리

                resp.sendRedirect(view.substring(REDIRECT_PRFIX.length()));
            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위하

                rd = req.getRequestDispatcher(view);
                rd.include(req, resp);

            }
        } catch (Exception exception) {
            log.error("", exception);
            req.setAttribute("exception", exception);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }

    }

    private String resovleServlet(String servletPath) {
        String processingServlet = null;
        if ("/student/studentManager.do".equals(servletPath)) {
            processingServlet = "/student/studentManager";
        } else if ("/student/register.do".equals(servletPath)) {
            processingServlet = "/student/register";
        } else if ("/student/view.do".equals(servletPath)) {
            processingServlet = "/student/view";
        } else if ("/student/update.do".equals(servletPath)) {
            processingServlet = "/student/update";
        } else if ("/student/delete.do".equals(servletPath)) {
            processingServlet = "/student/delete";
        }
        // todo 실행 할 servlet 결정하기
        log.error("resolveServlet:{}", processingServlet);
        return processingServlet;

    }


}

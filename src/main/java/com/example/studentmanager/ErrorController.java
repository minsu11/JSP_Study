package com.example.studentmanager;

import static javax.servlet.RequestDispatcher.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("status_code", request.getAttribute(ERROR_STATUS_CODE));
        request.setAttribute("exception_type", request.getAttribute(ERROR_EXCEPTION_TYPE));
        request.setAttribute("message", request.getAttribute(ERROR_MESSAGE));
        request.setAttribute("exception", request.getAttribute(ERROR_EXCEPTION));
        request.setAttribute("request_url", request.getAttribute(ERROR_REQUEST_URI));

        return "/error.jsp";
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
//        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
//        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
//        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
//        req.setAttribute("request_url", req.getAttribute(ERROR_REQUEST_URI));
//
//        RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
//        rd.forward(req, resp);
//    }
}

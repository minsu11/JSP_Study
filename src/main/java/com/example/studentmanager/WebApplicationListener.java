package com.example.studentmanager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();
        Gender g;
        for (int i = 1; i <= 10; i++) {
            int age = (int) (Math.random() * 20) + 1;
            g = Gender.F;
            if (i % 2 == 0) {
                g = Gender.M;
            }
            Student student = new Student("Student" + i, "아카데미" + i, g, age);
            log.error(student.getId());
            studentRepository.save(student);
        }
        context.setAttribute("studentRepository", studentRepository);
    }
}
package com.example.studentmanager;

import java.time.LocalDateTime;
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
        StudentRepository studentRepository = new JsonStudentRepository();
        Gender g;
        for (int i = 1; i <= 10; i++) {
            int age = (int) (Math.random() * 20) + 1;
            g = Gender.F;
            if (i % 2 == 0) {
                g = Gender.M;
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            Student student = new Student("Student" + i, "아카데미" + i, g, age, localDateTime);
            log.error(student.getId());
            studentRepository.save(student);
            log.error("save 하고 난뒤");
        }
        context.setAttribute("studentRepository", studentRepository);
    }
}
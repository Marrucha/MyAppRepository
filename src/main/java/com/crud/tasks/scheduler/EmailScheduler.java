package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: New trello Card";
    @Autowired
    SimpleEmailService simpleEmailService;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    AdminConfig adminConfig;

    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail(){

        String countTask;
        long size = taskRepository.count();
            if(size == 1) {
                countTask = "task";
            } else {
                countTask = "tasks";
            }

        simpleEmailService.send(new Mail(adminConfig.getAdminMail(),null, SUBJECT, "Currently in database you have  " + size  + " " + countTask));


    }

}

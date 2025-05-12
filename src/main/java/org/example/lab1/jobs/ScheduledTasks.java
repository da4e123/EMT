package org.example.lab1.jobs;

import org.example.lab1.service.application.AuthorApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final AuthorApplicationService authorApplicationService;


    public ScheduledTasks(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }


    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView(){
        this.authorApplicationService.refreshMaterializedView();
    }
}

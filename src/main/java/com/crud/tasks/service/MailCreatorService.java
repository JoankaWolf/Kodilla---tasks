package com.crud.tasks.service;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");


        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://joankawolf.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Best regards");
        context.setVariable("company_details", adminConfig.getInfoAppName());
        context.setVariable("preview_message", "New task has been created!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }


    public String buildDailyTrelloCardEmail(String message) {

        List<String> functionTask = new ArrayList<>();
        functionTask.add("manage your tasks");
        functionTask.add("sending tasks to Trello");


        Context contextDaily = new Context();
        contextDaily.setVariable("message", message);
        contextDaily.setVariable("tasks_url", "https://joankawolf.github.io/");
        contextDaily.setVariable("button", "Visit Task Application");
        contextDaily.setVariable("goodbye_message", "Best regards");
        contextDaily.setVariable("preview_message", "Your Daily Tasks Info!");
        contextDaily.setVariable("show_button", true);
        contextDaily.setVariable("is_friend", false);
        contextDaily.setVariable("admin_config", adminConfig);
        contextDaily.setVariable("application_function_Task", functionTask);
        return templateEngine.process("mail/daily-trello-card-mail", contextDaily);
    }

//    public String buildTrelloCardEmail(String message) {
//        Context context = new Context();
//        context.setVariable("message", message);
//        return templateEngine.process("mail/created-trello-card-mail", context);
//    }

}
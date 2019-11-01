package com.geekbrains.controller;

import com.geekbrains.domain.Task;
import com.geekbrains.domain.User;
import com.geekbrains.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddTaskController {

    private static final String MAPPING = "/add";
    private static final String VIEW = "add_task";
    private static final String REDIRECTION = "redirect:/main";

    @Autowired
    private TaskService taskService;

    @GetMapping(MAPPING)
    public String addContact() {

        return VIEW;
    }

    @PostMapping(MAPPING)
    public String save(String title, String description, @AuthenticationPrincipal User user) {
        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setAuthor(user);
        taskService.save(task);

        return REDIRECTION;
    }

}

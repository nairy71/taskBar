package com.geekbrains.controller;


import com.geekbrains.domain.Project;
import com.geekbrains.domain.Task;
import com.geekbrains.domain.User;
import com.geekbrains.service.ProjectService;
import com.geekbrains.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
public class ProjectController {

    private static final String MAIN_VIEW = "main";
    private static final String PROJECT_VIEW = "project";
    private static final String REDIRECTION = "redirect:/project";
    private static final String TASK_ATTRIBUTE = "tasks";
    private static final String PROJECT_ATTRIBUTE = "projects";
    private static final String PROJECT_MAPPING = "/project";
    private static final String DELETE_MAPPING = "/delete";

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/addProject")
    public String addNewProject(String name, String description, @AuthenticationPrincipal User user) {

        projectService.createNewProject(name, description, user);

        return REDIRECTION;
    }

    @GetMapping(PROJECT_MAPPING)
    public String main(@AuthenticationPrincipal User user, @RequestParam Long id, Model model) {

        return getView(user, id, model);
    }

    private String getView(User user, Long id, Model model) {

        String result;
        List<Project> projects;

        if (id == null) {
            projects = projectService.getAllAvailableForUser(user);
            model.addAttribute(PROJECT_ATTRIBUTE, projects);

            result = MAIN_VIEW;
        } else {
            Project project = projectService.getById(id);
            model.addAttribute(TASK_ATTRIBUTE, project.getTasks());

            result = PROJECT_VIEW;
        }

        return result;
    }

}

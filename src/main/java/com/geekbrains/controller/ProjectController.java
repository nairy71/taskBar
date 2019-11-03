package com.geekbrains.controller;


import com.geekbrains.domain.Project;
import com.geekbrains.domain.User;
import com.geekbrains.service.ProjectService;
import com.geekbrains.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ProjectController {

    private static final String PROJECT_VIEW = "main";
    private static final String PROJECTS_VIEW = "project";
    private static final String REDIRECTION = "redirect:/project";
    private static final String TASK_ATTRIBUTE = "tasks";
    private static final String PROJECT_ATTRIBUTE = "projects";
    private static final String PROJECT_MAPPING = "/project";
    private static final String DELETE_MAPPING = "/deleteProject";
    private static final String ADD_MAPPING = "/addProject";
    private static final String SPECIFIC_PROJECT_MAPPING = "/project/{id}";

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @PostMapping(ADD_MAPPING)
    public String addNewProject(String name, String description,
                                @AuthenticationPrincipal User user) {

        projectService.createNewProject(name, description, user);

        return REDIRECTION;
    }

    @GetMapping(PROJECT_MAPPING)
    public String main(@AuthenticationPrincipal User user, Model model) {

        List<Project> projects;

        projects = projectService.getAllAvailableForUser(user);

        model.addAttribute(PROJECT_ATTRIBUTE, projects);

        return PROJECTS_VIEW;
    }

    @GetMapping(SPECIFIC_PROJECT_MAPPING)
    public String specificProject(@PathVariable Long id, Model model) {

        model.addAttribute(TASK_ATTRIBUTE, taskService.getAllByProjectId(id));

        return PROJECT_VIEW;
    }

    @PostMapping(DELETE_MAPPING)
    public String deleteProject(@RequestParam Long id) {
        projectService.delete(id);

        return PROJECTS_VIEW;
    }

}

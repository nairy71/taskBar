package com.geekbrains.service;

import com.geekbrains.domain.Project;
import com.geekbrains.domain.User;

import java.util.List;

public interface ProjectService extends Service<Project, Long> {

    List<Project> getAllBySupervisorUsername(String username);

    List<Project> getAllAvailableForUser(User user);

    void createNewProject(String name, String description, User user);

}

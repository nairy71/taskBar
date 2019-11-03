package com.geekbrains.service.impl;

import com.geekbrains.domain.Project;
import com.geekbrains.domain.User;
import com.geekbrains.repo.ProjectRepository;
import com.geekbrains.service.ProjectService;
import com.geekbrains.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<Project, Long> implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public List<Project> getAllAvailableForUser(User user) {

        User userFromDb = userService.getById(user.getId());
        List<Project> projects = userFromDb.getProjects();

        return userFromDb.getProjects();
    }

    @Override
    PagingAndSortingRepository<Project, Long> getRepository() {
        return repository;
    }

    @Override
    public List<Project> getAllBySupervisorUsername(String username) {
        return repository.findAllBySupervisorUsername(username);
    }

    @Override
    public void createNewProject(String name, String description, User user) {

        Project project = new Project();
        ArrayList<User> list = new ArrayList<>();

        list.add(user);
        project.setName(name);
        project.setDescription(description);
        project.setSupervisor(user);
        project.setMembers(list);

        repository.save(project);
    }

}

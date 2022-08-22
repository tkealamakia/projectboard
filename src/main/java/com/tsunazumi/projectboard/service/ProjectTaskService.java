package com.tsunazumi.projectboard.service;

import com.tsunazumi.projectboard.domain.ProjectTask;
import com.tsunazumi.projectboard.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectTaskService {
  @Autowired
  private ProjectTaskRepository projectTaskRepository;

  public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask) {
    if (projectTask.getStatus() == null || projectTask.getStatus().equals("")) {
      projectTask.setStatus("TO_DO");
    }
    return projectTaskRepository.save(projectTask);
  }

  public Iterable<ProjectTask> findAll() {
    return projectTaskRepository.findAll();
  }

  public Optional<ProjectTask> findById(Long id) {
    return projectTaskRepository.findById(id);
  }
}

package com.tsunazumi.projectboard.data;

import com.tsunazumi.projectboard.domain.ProjectTask;
import com.tsunazumi.projectboard.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  @Autowired
  ProjectTaskRepository projectTaskRepository;

  @Override
  public void run(String... args) throws Exception {
    loadData();
  }

  private void loadData() {
    if (projectTaskRepository.count() == 0) {
      ProjectTask task1 = new ProjectTask("summary1", "ac1", "status1" );
      ProjectTask task2 = new ProjectTask("summary2", "ac2", "status2" );
      ProjectTask task3 = new ProjectTask("summary3", "ac3", "status3" );
      projectTaskRepository.save(task1);
      projectTaskRepository.save(task2);
      projectTaskRepository.save(task3);
    }

  }
}

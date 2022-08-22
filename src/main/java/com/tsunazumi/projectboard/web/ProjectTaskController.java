package com.tsunazumi.projectboard.web;

import com.tsunazumi.projectboard.domain.ProjectTask;
import com.tsunazumi.projectboard.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ProjectTaskController {
  @Autowired
  private ProjectTaskService projectTaskService;

  @PostMapping("")
  public ResponseEntity<?> addProjectTaskToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result) {
    if (result.hasErrors()) {
      Map<String, String> errorMap = new HashMap<>();
      for (FieldError error : result.getFieldErrors()) {
        errorMap.put(error.getField(), error.getDefaultMessage());
      }

      return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
    }
    ProjectTask newTask = projectTaskService.saveOrUpdateProjectTask(projectTask);
    return new ResponseEntity<ProjectTask>(newTask, HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public Iterable<ProjectTask> getAllProjectTasks() {
    return projectTaskService.findAll();
  }

  @GetMapping("/{pt_id}")
  public ResponseEntity<?> getProjectTaskById(@PathVariable Long pt_id) {
    Optional<ProjectTask> projectTask = projectTaskService.findById(pt_id);
    return new ResponseEntity<>(projectTask.get(), HttpStatus.OK);
  }
}

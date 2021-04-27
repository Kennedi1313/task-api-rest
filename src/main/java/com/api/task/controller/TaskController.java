package com.api.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.task.model.Task;
import com.api.task.repository.TaskRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	

	@GetMapping
	public List<Task> getTasks(@RequestParam String name) {
		System.out.println("entrou no get");
		return taskRepository.findTaskByGroupName(name);  
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public void setTasks(@RequestBody List<Task> tasks) {
		System.out.println("entrou no set");
		for (Task task : taskRepository.findTaskByGroupName(tasks.get(0).getGroupName())) {
			taskRepository.delete(task);
		}
		for (Task task : tasks) {
			taskRepository.save(task);
		}
		
	}
}

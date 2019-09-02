package br.com.todolist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todolist.entity.Task;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;
import br.com.todolist.service.TaskService;

@RestController
@RequestMapping(value = "/todos/{todoId}/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public List<Task> readAll(@PathVariable int todoId) throws IdNotFoundException{
		return this.taskService.readAll(todoId);
	}
	
	@PostMapping
	public Task create(@PathVariable int todoId, @RequestBody Task task) throws IdNotFoundException, InvalidEntityException {
		return this.taskService.create(todoId, task);
	}
	
	@PutMapping(value = "/{taskId}")
	public Task update(@PathVariable int todoId, @PathVariable int taskId, @RequestBody Task task) throws IdNotFoundException {
		return this.taskService.update(todoId, taskId, task);
	}
	
	@DeleteMapping(value = "/{taskId}")
	public boolean delete(@PathVariable int taskId) throws IdNotFoundException {
		return this.taskService.delete(taskId);
	}
	
	@GetMapping(value = "/{taskId}")
	public Optional<Task> findById(@PathVariable int taskId) throws IdNotFoundException{
		return this.taskService.findById(taskId);
	}
	
	@GetMapping(value = "/titles/{titleTask}")
	public Task findByTitle(@PathVariable String titleTask) throws TitleNotFoundException {
		return this.taskService.findByTitle(titleTask);
	}
	
	@GetMapping(value = "/done")
	public List<Task> findByTasksDone(){
		return this.taskService.findByTasksDone();
	}
	
	@GetMapping(value = "/done/{taskId}")
	public boolean isDone(@PathVariable int taskId) throws IdNotFoundException{
		return this.taskService.isDone(taskId);
	}
}

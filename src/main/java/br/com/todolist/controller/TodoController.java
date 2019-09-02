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

import br.com.todolist.entity.Todo;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;
import br.com.todolist.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping
	public List<Todo> readAll(){
		return this.todoService.readAll();
	}
	
	@PostMapping
	public Todo create(@RequestBody Todo todo) throws InvalidEntityException {
		return this.todoService.create(todo);
	}
	
	@PutMapping(value = "/{todoId}")
	public Todo update(@PathVariable int todoId ,@RequestBody Todo todo) throws IdNotFoundException {
		return this.todoService.update(todoId, todo);
	}
	
	@DeleteMapping(value = "/{todoId}")
	public boolean delete(@PathVariable int todoId) throws IdNotFoundException {
		return this.todoService.delete(todoId);
	}
	
	@GetMapping(value = "/{todoId}")
	public Optional<Todo> findById(@PathVariable int todoId) throws IdNotFoundException {
		return this.todoService.findById(todoId);
	}

	@GetMapping(value = "/titles/{title}")
	public Todo findByTitle(@PathVariable String title) throws TitleNotFoundException {
		return this.todoService.findByTitle(title);
	}

	@GetMapping(value = "/done")
	public List<Todo> findByDone() {
		return this.todoService.findByDone();
	}
	
	@GetMapping(value = "/done/{todoId}")
	public boolean isDone(@PathVariable int todoId) throws IdNotFoundException {
		return this.todoService.isDone(todoId);
	}
	
	
}

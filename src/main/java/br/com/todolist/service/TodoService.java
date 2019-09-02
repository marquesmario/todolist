package br.com.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.todolist.entity.Todo;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;
import br.com.todolist.repository.TodoRepository;

@Service
public class TodoService implements ITodoService{

	@Autowired
	private TodoRepository todoRepository;
	private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
	

	@Override
	public List<Todo> readAll() {
		logger.info("Reading TodoList");
		return this.todoRepository.findAll();
	}

	@Override
	public Todo create(Todo todo) throws InvalidEntityException {
		logger.info("Creating a Todo");
		if(!this.todoRepository.findAll().contains(todo)) {
			logger.info("Created Successfull");
			return this.todoRepository.save(todo);
		}
		else {
			logger.error("Error Creating a Todo");
			throw new InvalidEntityException();
		}
	}

	@Override
	public Todo update(int todoId, Todo todo) throws IdNotFoundException {
		logger.info("Updating a Todo");
		if(this.todoRepository.existsById(todoId)) {
			logger.info("Updated Successfull");
			todo.setId(todoId);
			return this.todoRepository.save(todo);
		}else{
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}

	@Override
	public boolean delete(int todoId) throws IdNotFoundException {
		logger.info("Deleting a Todo");
		if(this.todoRepository.existsById(todoId)) {
			logger.info("Delete Successfull");
			this.todoRepository.deleteById(todoId);
			return true;
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}

	@Override
	public Optional<Todo> findById(int todoId) throws IdNotFoundException{
		logger.info("Finding by ID a Todo");
		if(this.todoRepository.existsById(todoId)) {
			logger.info("Finding by ID Successfull");
			return this.todoRepository.findById(todoId);
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}

	@Override
	public Todo findByTitle(String title) throws TitleNotFoundException{
		logger.info("Finding by Title a Todo");
		if(this.todoRepository.existsByTitle(title)) {
			logger.info("Finding by Title Successfull");
			return this.todoRepository.findByTitle(title);
		}else {
			logger.error("Title Not Found");
			throw new TitleNotFoundException();
		}
	}

	@Override
	public List<Todo> findByDone() {
		logger.info("Finding by Done Objects in a Todo List");
		return this.todoRepository.findAll().stream().filter(todo -> todo.isDone() == true).collect(Collectors.toList());
	}
	
	public boolean isDone(int todoId) throws IdNotFoundException{
		logger.info("Completing a Todo Object");
		if(this.todoRepository.existsById(todoId) && this.todoRepository.findById(todoId).get().getTask().stream().allMatch(task -> task.isDone() == true)){
			this.todoRepository.findById(todoId).get().setDone(true);
			this.todoRepository.save(this.todoRepository.findById(todoId).get());
			return true;
		}else if(!this.todoRepository.existsById(todoId)){
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}else {
			logger.info("Todo Object Can't be Completed");
			return false;
		}
	}
	
}

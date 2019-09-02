package br.com.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.todolist.entity.Task;
import br.com.todolist.entity.Todo;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;
import br.com.todolist.repository.TaskRepository;

@Service
public class TaskService implements ITaskService{

	@Autowired
	private TaskRepository taskRepository;
	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
	
	
	@Override
	public List<Task> readAll(int todoId) throws IdNotFoundException {
		if(this.taskRepository.existsByTodoId(todoId)) {
			logger.info("Reading Task List");
			return this.taskRepository.findByTodoId(todoId);
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}

	@Override
	public Task create(int todoId, Task task) throws IdNotFoundException, InvalidEntityException {
		logger.info("Creating a Task");
		if(this.taskRepository.existsByTodoId(todoId) && !this.taskRepository.findAll().contains(task)) {
			task.setTodo( new Todo(todoId,"","", false));
			logger.info("Created Successfull");
			return this.taskRepository.save(task);
		}else if(!this.taskRepository.existsByTodoId(todoId)){
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}else {
			logger.error("Error Creating a Todo Object");
			throw new InvalidEntityException();
		}
	}

	@Override
	public Task update(int todoId, int taskId, Task task) throws IdNotFoundException {
		logger.info("Updating a Task");
		if(this.taskRepository.existsByTodoId(todoId) && this.taskRepository.existsById(taskId)) {
			task.setTodo(new Todo(todoId,"","", false));
			task.setId(taskId);
			logger.info("Updated Successfull");
			return this.taskRepository.save(task);
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}

	@Override
	public boolean delete(int taskId) throws IdNotFoundException{
		logger.info("Deleting a Task");
		if(this.taskRepository.existsById(taskId)) {
			this.taskRepository.deleteById(taskId);
			logger.info("Delete Successfull");
			return true;
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
		
	}

	@Override
	public Optional<Task> findById(int taskId) throws IdNotFoundException{
		logger.info("Finding by ID a Task");
		if(this.taskRepository.existsById(taskId)) {
			logger.info("Finding by ID Successfull");
			return this.taskRepository.findById(taskId);
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}		
	}

	@Override
	public Task findByTitle(String titleTask)  throws TitleNotFoundException{
		logger.info("Finding by Title a Task");
		if(this.taskRepository.existsByTitle(titleTask)) {
			logger.info("Finding by Title Successfull");
			return this.taskRepository.findByTitle(titleTask);
		}else {
			logger.error("Title Not Found");
			throw new TitleNotFoundException();
		}
	}

	@Override
	public List<Task> findByTasksDone() {
		logger.info("Finding by Done Tasks in a Task List");
		return this.taskRepository.findAll().stream().filter(task -> task.isDone() == true).collect(Collectors.toList());
	}
	
	public boolean isDone(int taskId) throws IdNotFoundException{
		logger.info("Completing a Task");
		if(this.taskRepository.existsById(taskId) && this.taskRepository.findById(taskId).get().getItems().stream().allMatch(item -> item.isDone() == true)){
			this.taskRepository.findById(taskId).get().setDone(true);
			this.taskRepository.save(this.taskRepository.findById(taskId).get());
			logger.info("Completed Successfull");
			return true;
		}else if(!this.taskRepository.existsById(taskId)){
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}else {
			logger.info("Task Can't be Completed");
			return false;
		}
	}
	
	
}

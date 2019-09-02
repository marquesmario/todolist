package br.com.todolist.service;

import java.util.List;
import java.util.Optional;

import br.com.todolist.entity.Task;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;

public interface ITaskService {

	public List<Task> readAll(int todoId) throws IdNotFoundException;
	public Task create(int todoId,Task task) throws IdNotFoundException, InvalidEntityException;
	public Task update(int todoId, int taskId, Task task) throws IdNotFoundException;
	public boolean delete(int id) throws IdNotFoundException;
	public Optional<Task> findById(int id) throws IdNotFoundException;
	public Task findByTitle(String title) throws TitleNotFoundException;
	public List<Task> findByTasksDone();
	boolean isDone(int taskId) throws IdNotFoundException;
}

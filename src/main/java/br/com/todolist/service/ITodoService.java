package br.com.todolist.service;

import java.util.List;
import java.util.Optional;

import br.com.todolist.entity.Todo;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;

public interface ITodoService {

	List<Todo> readAll();
	Todo create(Todo todo) throws InvalidEntityException;
	Todo update(int todoId, Todo todo) throws IdNotFoundException;
	boolean delete(int id) throws IdNotFoundException;
	Optional<Todo> findById(int todoId) throws IdNotFoundException;
	Todo findByTitle(String title) throws TitleNotFoundException;
	List<Todo> findByDone();
	boolean isDone(int todoId) throws IdNotFoundException;
}

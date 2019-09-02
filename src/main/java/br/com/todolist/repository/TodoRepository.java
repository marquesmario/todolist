package br.com.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.todolist.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{

	Todo findByTitle(String titleTodo);
	boolean existsByTitle(String titleTodo);
}

package br.com.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.todolist.entity.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer>{

	public Task findByTitle(String titleTask);
	public List<Task> findByTodoId(int taskId);
	public boolean existsByTodoId(int taskId);
	public boolean existsByTitle(String titleTask);
}

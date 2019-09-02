package br.com.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.todolist.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

	public Item findByTitle(String titleItem);
	public List<Item> findByTaskId(int taskId);
	public boolean existsByTaskId(int taskId);
	public boolean existsByTitle(String titleItem);
}

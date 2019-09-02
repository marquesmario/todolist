package br.com.todolist.service;

import java.util.List;
import java.util.Optional;

import br.com.todolist.entity.Item;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;

public interface IItemService {

	public List<Item> readAll(int taskId) throws IdNotFoundException;
	public Item create(int todoId, int taskId, Item item) throws IdNotFoundException, InvalidEntityException;
	public Item update(int todoId, int taskId, int itemId, Item item) throws IdNotFoundException;
	public boolean delete(int itemId) throws IdNotFoundException;
	public Optional<Item> findById(int itemId) throws IdNotFoundException;
	public Item findByTitle(String titleItem) throws TitleNotFoundException;
	public List<Item> findByItemsDone();
	boolean doneItem(int itemId) throws IdNotFoundException;
	
	
	
}

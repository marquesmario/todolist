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

import br.com.todolist.entity.Item;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;
import br.com.todolist.service.ItemService;

@RestController
@RequestMapping(value = "/todos/{todoId}/tasks/{taskId}/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public List<Item> readAll(@PathVariable int taskId) throws IdNotFoundException{
		return this.itemService.readAll(taskId);
	}
	
	@PostMapping
	public Item create(@PathVariable int todoId, @PathVariable int taskId, @RequestBody Item item) throws IdNotFoundException, InvalidEntityException {
		return this.itemService.create(todoId, taskId, item);
	}
	
	@PutMapping(value = "/{itemId}")
	public Item update(@PathVariable int todoId, @PathVariable int taskId, @PathVariable int itemId, @RequestBody Item item) throws IdNotFoundException {
		return this.itemService.update(todoId, taskId, itemId, item);
	}
	
	@DeleteMapping(value = "/{itemId}")
	public boolean delete(@PathVariable int itemId) throws IdNotFoundException {
		return this.itemService.delete(itemId);
	}
	
	@GetMapping(value = "/{itemId}")
	public Optional<Item> findById(@PathVariable int itemId) throws IdNotFoundException{
		return this.itemService.findById(itemId);
	}
	
	@GetMapping(value = "/titles/{title}")
	public Item findByTitle(@PathVariable String title) throws TitleNotFoundException {
		return this.itemService.findByTitle(title);
	}
	
	@GetMapping(value = "/done")
	public List<Item> findByItemsDone(){
		return this.itemService.findByItemsDone();
	}
	
	@GetMapping(value = "/done/{ItemId}")
	public boolean doneItem(@PathVariable int itemId) throws IdNotFoundException {
		return this.itemService.doneItem(itemId);
	}
}

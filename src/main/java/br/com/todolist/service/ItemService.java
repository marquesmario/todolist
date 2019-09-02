package br.com.todolist.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.todolist.entity.Item;
import br.com.todolist.entity.Task;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;
import br.com.todolist.repository.ItemRepository;

@Service
public class ItemService implements IItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
	
	@Override
	public List<Item> readAll(int taskId) throws IdNotFoundException{
		if (this.itemRepository.existsByTaskId(taskId)) {
			logger.info("Reading Item List");
			return itemRepository.findByTaskId(taskId);
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}
	
	@Override	
	public Item create(int todoId, int taskId, Item item) throws IdNotFoundException,InvalidEntityException {
		logger.info("Creating a Item");
		if (this.itemRepository.existsByTaskId(taskId) && !this.itemRepository.findAll().contains(item)) {
			item.setTask(new Task(taskId, "","",false,todoId));
			logger.info("Created Successfull");
			return this.itemRepository.save(item);
		}else if(!this.itemRepository.existsByTaskId(taskId)) {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}else {
			logger.error("Error Creating a Todo Object");
			throw new InvalidEntityException();
		}
	}
	
	@Override
	public Item update(int todoId, int taskId, int itemId, Item item) throws IdNotFoundException {
		logger.info("Updating a Item");
		if(this.itemRepository.existsByTaskId(taskId) && this.itemRepository.existsById(itemId)) {
			item.setTask(new Task(taskId, "","",false,todoId));
			item.setId(itemId);
			logger.info("Updated Successfull");
			return this.itemRepository.save(item);
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}
	
	@Override
	public boolean delete(int itemId) throws IdNotFoundException{
		logger.info("Deleting a Item");
		if(this.itemRepository.existsById(itemId)) {
			this.itemRepository.deleteById(itemId);
			logger.info("Delete Successfull");
			return true;
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}
	
	@Override
	public Optional<Item> findById(int itemId) throws IdNotFoundException{
		logger.info("Finding by ID a Item");
		if(this.itemRepository.existsById(itemId)) {
			logger.info("Finding by ID Successfull");
			return this.itemRepository.findById(itemId);
		}else {
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}
	
	@Override
	public List<Item> findByItemsDone(){
		logger.info("Finding by Done Items in a Item List");
		return this.itemRepository.findAll().stream().filter(item -> item.isDone() == true).collect(Collectors.toList());
	}
	
	@Override
	public Item findByTitle(String titleItem) throws TitleNotFoundException{
		logger.info("Finding by Title a Item");
		if(this.itemRepository.existsByTitle(titleItem)) {
			logger.info("Finding by Title Successfull");
			return this.itemRepository.findByTitle(titleItem);
		}else {
			logger.error("Title Not Found");
			throw new TitleNotFoundException();
		}
	}
	
	public boolean doneItem(int itemId) throws IdNotFoundException{
		logger.info("Completing a Item");
		if(this.itemRepository.existsById(itemId) && !this.itemRepository.findById(itemId).get().isDone()){
			this.itemRepository.findById(itemId).get().setEndDate(LocalDate.now());
			this.itemRepository.findById(itemId).get().setDone(true);
			logger.info("Completed Successfull");
			return true;
		}else if(this.itemRepository.findById(itemId).get().isDone()){
			logger.info("Item Already Completed");	
			return false;
		}else{
			logger.error("Id Not Found");
			throw new IdNotFoundException();
		}
	}
}

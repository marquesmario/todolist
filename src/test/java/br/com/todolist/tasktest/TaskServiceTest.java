package br.com.todolist.tasktest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.todolist.entity.Task;
import br.com.todolist.entity.Todo;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;
import br.com.todolist.service.TaskService;
import br.com.todolist.service.TodoService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private TodoService todoService;
	
	@Test
	public void createTaskSuccesfull() throws IdNotFoundException, InvalidEntityException {
		Todo todo = new Todo(1, "title", "descr", false);
		this.todoService.create(todo);
		Task novoTask = new Task(1, "title", "descr", false, 1);
		Task sameTask = this.taskService.create(1,novoTask);
		assertEquals(novoTask, sameTask);
	}
	@Test
	public void updateCategorySuccessful() throws InvalidEntityException, IdNotFoundException {
		Todo todo = new Todo(1, "title", "descr", false);
		this.todoService.create(todo);
		Task novoTask = new Task(1, "title", "descr", false,1);
		Task updatedTask = new Task(1, "title1", "desc3", false,1);
		this.taskService.create(1, novoTask);
		assertEquals(updatedTask, this.taskService.update(1, 1, updatedTask));
	}
	
	@Test(expected = IdNotFoundException.class)
	public void whenUpdateIdDoesntExist_thenCallIdNotFoundException() throws IdNotFoundException, InvalidEntityException {
		Task novoTask = new Task(1, "title", "descr", false, 1);
		this.taskService.update(100, 100, novoTask);
	}
	
	@Test
    public void deleteTaskSuccessful() throws InvalidEntityException, IdNotFoundException {
		Todo todo = new Todo(1, "title", "descr", false);
		this.todoService.create(todo);
		Task novoTask = new Task(1, "title", "descr", false,1);
		this.taskService.create(1, novoTask);
        assertTrue(this.taskService.delete(1));
    }
	
	@Test(expected = IdNotFoundException.class)
    public void whenDeleteIdDoesntExist_thenCallIdNotFoundException() throws IdNotFoundException {
		this.taskService.delete(13);
	}
	
	@Test
    public void findByIdSuccessful() throws InvalidEntityException, IdNotFoundException{
		Todo todo = new Todo(1, "title", "descr", false);		
		this.todoService.create(todo);
		Task novoTask = new Task(1, "title", "descr", false,1);
		this.taskService.create(1,novoTask);
        assertEquals(this.taskService.findById(1), novoTask);
    }
	
	@Test(expected = IdNotFoundException.class)
	public void whenFindByIdDoesntExist_thenCallIdNotFoundException() throws IdNotFoundException {
		this.taskService.findById(13);
	}
	
	@Test
    public void findByTitleSuccessful() throws InvalidEntityException, TitleNotFoundException, IdNotFoundException {
		Todo todo = new Todo(1, "title", "descr", false);
		this.todoService.create(todo);
		Task novoTask = new Task(1, "title", "descr", false,1);
		this.taskService.create(1, novoTask);
		assertEquals(this.taskService.findByTitle("title"), novoTask);
	}
	
	@Test(expected = TitleNotFoundException.class)
	public void whenFindByTitleDoesntExist_thenCallTitleNotFoundException() throws TitleNotFoundException {
		this.taskService.findByTitle("Aleatorio");
	}
	
	@Test
	public void isDoneSuccessfull() throws IdNotFoundException, InvalidEntityException{
		Todo todo = new Todo(1, "title", "descr", false);
		this.todoService.create(todo);
		Task novoTask = new Task(1, "title", "descr", false,1);
		this.taskService.create(1,novoTask);
		assertTrue(this.taskService.isDone(1));	
	}
	
	@Test(expected = IdNotFoundException.class)
	public void whenIsDoneIdDoesntExist_thenCallIdNotFoundException() throws IdNotFoundException{
		this.taskService.isDone(12);
	}

}

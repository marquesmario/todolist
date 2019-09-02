package br.com.todolist.todotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.todolist.entity.Todo;
import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;
import br.com.todolist.service.TodoService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TodoServiceTest {

	@Autowired
	private TodoService todoService;
	
	@Test
	public void createTodoSuccesfull() throws IdNotFoundException, InvalidEntityException {
		Todo novoTodo = new Todo(1, "title", "descr", false);
		Todo sameTodo = this.todoService.create(novoTodo);
		assertEquals(novoTodo, sameTodo);
	}
	@Test
	public void updateCategorySuccessful() throws InvalidEntityException, IdNotFoundException {
		Todo novoTodo = new Todo(1, "title", "descr", false);
		Todo updatedTodo = new Todo(1, "title1", "desc3", false);
		this.todoService.create(novoTodo);
		assertEquals(updatedTodo, this.todoService.update(1, updatedTodo));
	}
	
	@Test(expected = IdNotFoundException.class)
	public void whenUpdateIdDoesntExist_thenCallIdNotFoundException() throws IdNotFoundException {
		Todo novoTodo = new Todo(1, "title", "descr", false);
		this.todoService.update(100, novoTodo);
	}
	
	@Test
    public void deleteTodoSuccessful() throws InvalidEntityException, IdNotFoundException {
		Todo novoTodo = new Todo(1, "title", "descr", false);
		this.todoService.create(novoTodo);
        assertTrue(this.todoService.delete(1));
    }
	
	@Test(expected = IdNotFoundException.class)
    public void whenDeleteIdDoesntExist_thenCallIdNotFoundException() throws IdNotFoundException {
		this.todoService.delete(13);
	}
	
	@Test
    public void findByIdSuccessful() throws InvalidEntityException, IdNotFoundException{
		Todo novoTodo = new Todo(1, "title", "descr", false);
		this.todoService.create(novoTodo);
        assertEquals(this.todoService.findById(1), novoTodo);
    }
	
	@Test(expected = IdNotFoundException.class)
	public void whenFindByIdDoesntExist_thenCallIdNotFoundException() throws IdNotFoundException {
		this.todoService.findById(13);
	}
	
	@Test
    public void findByTitleSuccessful() throws InvalidEntityException, TitleNotFoundException {
		Todo novoTodo = new Todo(1, "title", "descr", false);
		this.todoService.create(novoTodo);
		assertEquals(this.todoService.findByTitle("title"), novoTodo);
	}
	
	@Test(expected = TitleNotFoundException.class)
	public void whenFindByTitleDoesntExist_thenCallTitleNotFoundException() throws TitleNotFoundException {
		this.todoService.findByTitle("Aleatorio");
	}
	
	@Test
	public void isDoneSuccessfull() throws IdNotFoundException, InvalidEntityException{
		Todo novoTodo = new Todo(1, "title", "descr", false);
		this.todoService.create(novoTodo);
		assertTrue(this.todoService.isDone(1));	
	}
	
	@Test(expected = IdNotFoundException.class)
	public void whenIsDoneIdDoesntExist_thenCallIdNotFoundException() throws IdNotFoundException{
		this.todoService.isDone(12);
	}
	
}

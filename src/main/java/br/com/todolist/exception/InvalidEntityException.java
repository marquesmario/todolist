package br.com.todolist.exception;

public class InvalidEntityException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1751517292783947425L;

	public InvalidEntityException() {
		super("Entity Invalid To Be Saved or Updated, Please Try Another Valid Entity");
	}
}

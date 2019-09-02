package br.com.todolist.exception;

public class TitleNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7060430419391006551L;

	public TitleNotFoundException() {
		super("Title Not Found, Please Try Another Valid Title");
	}
}

package br.com.todolist.exception;


public class IdNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4871736420647300748L;

	public IdNotFoundException() {
		super("ID Not Found, Please Try Another Valid ID");
	}

}

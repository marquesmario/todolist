package br.com.todolist.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "todo")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "done")
	private boolean done;
	@JsonManagedReference
	@OneToMany(mappedBy = "todo")
	private List<Task> task;
	
	public Todo(int id, String title, String description, boolean done) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.done = done;
	}
	
	public Todo(String title, String description, boolean done) {
		this.title = title;
		this.description = description;
		this.done = done;
	}
	
}

package br.com.todolist.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "done", nullable = false)
	private boolean done;
	@OneToMany(mappedBy = "task")
	@JsonManagedReference
	private List<Item> items;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "todo_id")
	private Todo todo;
	
	public Task(int id, String title, String description, boolean done, int todoId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.done = done;
		this.todo = new Todo(todoId,"","", false);
	}

}

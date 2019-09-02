package br.com.todolist.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "done", nullable = false)
	private boolean done;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "task_id")
	private Task task;
	
	public Item(int id, String title, String description, LocalDate endDate, boolean done, int taskId, int todoId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.startDate = LocalDate.now();
		this.endDate = endDate;
		this.done = done;
		this.task = new Task(taskId,"","",false,todoId);
	}
	
}

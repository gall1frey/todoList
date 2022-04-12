package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("todoEntry")
public class TodoEntry {
	@Id
	private String task_id;
	private String name;
	private boolean done;
	
	public TodoEntry() {
	}

	public TodoEntry(String name, boolean done) {
		this.name = name;
		this.done = done;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}

	public void toggleDone() {
		this.done = !this.done;
	}
}

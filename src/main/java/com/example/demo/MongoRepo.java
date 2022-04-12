package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoRepo extends MongoRepository <TodoEntry,String> {
	public List<TodoEntry> findAll();
	
	@Query("{name: ?0}")
		List<TodoEntry> getTask(String taskName);
}

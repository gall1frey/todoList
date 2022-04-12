package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoEntryController {
	@Autowired
	public MongoRepo repo;

	@GetMapping("/")
	public String homepage(Model model){
		return "homepage";
	}

	@GetMapping("/all")
	public String viewTodos(Model model) {
		List<TodoEntry> todos = repo.findAll();
		model.addAttribute("alltodos",todos);
		return "all";
	}

	@GetMapping("/edit")
	public String editTodosGet(Model model) {
		return "edit";
	}

	@GetMapping("/add")
	public String addTodo(Model model) {
		model.addAttribute("todo0",new TodoEntry());
		return "add";
	}

	@PostMapping("/add")
	public String submitTodo(@ModelAttribute("todo0") TodoEntry todo0) {
		repo.save(todo0);
		return "redirect:all";
	}

	@PostMapping("/complete")
	public String completeTask(@RequestParam String name) {
		//repo.save(todo0);
		List<TodoEntry> todos = repo.getTask(name);
		TodoEntry a = todos.get(0);
		a.toggleDone();
		repo.save(a);
		return "redirect:all";
	}

	@PostMapping("/delete")
	public String deleteTodo(@RequestParam String name) {
		List<TodoEntry> todos = repo.getTask(name);
		TodoEntry a = todos.get(0);
		repo.delete(a);
		return "redirect:all";
	}

	@PostMapping("/update")
	public String updateTodo(@RequestParam String name, String newName) {
		List<TodoEntry> todos = repo.getTask(name);
		TodoEntry a = todos.get(0);
		a.setName(newName);
		repo.save(a);
		return "redirect:all";
	}
}

package com.jd.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jd.springboot.web.model.Todo;
import com.jd.springboot.web.service.TodoRespository;


@Controller
public class TodoController {
	
	//@Autowired
	//TodoService todoService;
	
	@Autowired
	TodoRespository repository;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		String name = getLoggedInUserName(model);
		//model.put("todos", todoService.retrieveTodos(name));
		model.put("todos", repository.findByUser(name));
		return "list-todos";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model){
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), " ",
				new Date(), false));
		return "todo";
	}

	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model,  @Valid Todo todo , BindingResult result){
		if(result.hasErrors()){
			return "todo";	
		}
		todo.setUser(getLoggedInUserName(model));
		repository.save(todo);
		//todoService.addTodo(getLoggedInUserName(model), todo.getDesc(), new Date(), false);
		
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id){
	//	todoService.deleteTodo(id);
		repository.deleteById(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		//Todo todo = todoService.retrieveTodo(id);
		Todo todo = repository.findById(id).get();
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		
		todo.setUser(getLoggedInUserName(model));
		
		//todoService.updateTodo(todo);
		repository.save(todo);
		return "redirect:/list-todos";
	}


	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails)
		{
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
	
}

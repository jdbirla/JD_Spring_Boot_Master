package com.jd.springboot.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jd.springboot.web.model.Todo;

public interface TodoRespository extends JpaRepository<Todo, Integer> {
	
	List<Todo> findByUser(String user);

}

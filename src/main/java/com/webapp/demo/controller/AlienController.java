package com.webapp.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.demo.dao.AlienRepo;
import com.webapp.demo.model.Alien;

import jakarta.ws.rs.Path;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;

	@RequestMapping("/")
	public String home() {
		
		return "home.jsp";
	}
	

	@DeleteMapping("/alien/{aid}")
	public Alien deleteAlien(@PathVariable int aid) {
		
		Alien a = repo.getOne(aid);
		repo.delete(a);
		return a; 
	}
	
	@PostMapping("/alien")
	public Alien addAlien(Alien alien) {
		
		repo.save(alien);
		return alien; 
	}
	
	@PutMapping(path="/alien", consumes= {"application/json"})
	public Alien saveOrUpdateAlien(@RequestBody Alien alien) {
		
		repo.save(alien);
		return alien; 
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlienById(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showData.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		return mv; 
	}
	
	@RequestMapping("/aliens")
	@ResponseBody
	public List<Alien> getAliens() {
		
		return repo.findAll();
	}
	
	@RequestMapping("/aliens/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		
		return repo.findById(aid);
	}
	
}

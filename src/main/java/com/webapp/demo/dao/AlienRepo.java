package com.webapp.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.demo.model.Alien;

public interface AlienRepo extends JpaRepository<Alien, Integer>
{

	
}

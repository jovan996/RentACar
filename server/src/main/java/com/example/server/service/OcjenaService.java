package com.example.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.model.Ocjena;
import com.example.server.repository.OcjenaRepository;

@Service
public class OcjenaService {
	
	@Autowired
	private OcjenaRepository repo;
	
	public List<Ocjena> getAllOcjene() {
		return repo.findAll();
	}
	
	public List<Ocjena> saveAll(List<Ocjena> lista){
		return repo.saveAll(lista);
	}
}

package com.example.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.model.Komentar;
import com.example.server.repository.KomentarRepository;

@Service
public class KomentarService {
	
	@Autowired
	private KomentarRepository repo;
	
	public List<Komentar> getAllKomentari() {
		
		return repo.findAll();
	}

	public List<Komentar> saveAll(List<Komentar> lista){
		return repo.saveAll(lista);
	}
}

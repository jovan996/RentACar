package com.example.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.model.Komentar;
import com.example.server.model.Ocjena;
import com.example.server.service.KomentarService;
import com.example.server.service.OcjenaService;


@RestController
@RequestMapping(value = "")
public class SyncController {
	
	@Autowired
	private OcjenaService ocjenaService;
	
	@Autowired
	private KomentarService komentarService;
	
	@RequestMapping(value = "/getOcjene", method = RequestMethod.POST)
	public List<Ocjena> getOcjene() {

		return ocjenaService.getAllOcjene();
	}
	
	@RequestMapping(value = "/getKomentari", method = RequestMethod.POST)
	public List<Komentar> getKomentari() {

		return komentarService.getAllKomentari();
	}
	
	@RequestMapping(value = "/syncKomentari", method = RequestMethod.POST)
	public ResponseEntity<List<Komentar>> syncKomentari(@RequestBody List<Komentar> komentari) {
		
		List<Komentar> pom=komentarService.saveAll(komentari);
		System.out.println("fddfdfdf");
		
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/syncOcjene", method = RequestMethod.POST)
	public ResponseEntity<List<Ocjena>> syncOcjene(@RequestBody List<Ocjena> komentari) {
		
		List<Ocjena> pom=ocjenaService.saveAll(komentari);
		System.out.println("fddfdfdf");
		
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	


}

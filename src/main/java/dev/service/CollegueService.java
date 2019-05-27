package dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Collegue;
import dev.repository.CollegueRepo;

@Service
public class CollegueService {
	@Autowired
	private CollegueRepo collegueRepo;

	// mise à jour de la base de données
	public void setCollegueRepo(CollegueRepo collegueRepo) {
		this.collegueRepo = collegueRepo;
	}

	public Collegue findCollegueByEmail(String email){
		Collegue collegue = this.collegueRepo.findByEmail(email).get();
		return collegue;
	}
}

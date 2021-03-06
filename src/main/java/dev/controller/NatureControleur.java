/**
 *
 */
package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Nature;
import dev.domainDto.MissionDto;
import dev.domainDto.NatureDTO;
import dev.service.NatureService;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@RestController
@RequestMapping("/nature")
public class NatureControleur {

	@Autowired
	NatureService natureService;

	@PostMapping
	public ResponseEntity<NatureDTO> ajout (@RequestBody NatureDTO nature) {
		Nature natureRetour = natureService.ajoutNature (nature);
		return ResponseEntity.status(200).body(new NatureDTO (natureRetour.getId (), natureRetour.getNomNature (), natureRetour.isFacturee(), natureRetour.isPrime (), natureRetour.getTauxJournalierMoyen(), natureRetour.getPourcentPrime(), natureRetour.getPlafondQuotidien(), natureRetour.isDepassementFrais(), natureRetour.getDateDebut(), natureRetour.getDateFin()));
	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<NatureDTO> modification (@PathVariable int id, @RequestBody NatureDTO nature) {
		Nature natureRetour = natureService.modificationNature(id, nature);
		return ResponseEntity.status(200).body(new NatureDTO (natureRetour.getId (), natureRetour.getNomNature (), natureRetour.isFacturee(), natureRetour.isPrime (), natureRetour.getTauxJournalierMoyen(), natureRetour.getPourcentPrime(), natureRetour.getPlafondQuotidien(), natureRetour.isDepassementFrais(), natureRetour.getDateDebut(), natureRetour.getDateFin()));
	}

	@DeleteMapping(path = "/{id}")
	public void suppression (@PathVariable Integer id) {
		natureService.suppressionNature (id);
	}

	@GetMapping
	public List <NatureDTO> recupererNatures () {
		return natureService.afficherToutesNatures();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<NatureDTO> afficherParId(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(natureService.trouverNatureDepuisId(id));
	}


}

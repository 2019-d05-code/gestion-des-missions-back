/**
 *
 */
package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Nature;
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
	public NatureDTO ajout (@RequestBody NatureDTO nature) {
		Nature natureRetour = natureService.ajoutNature (nature);
		return new NatureDTO (natureRetour.getId (), natureRetour.getNomNature (), natureRetour.isFacturee(), natureRetour.isPrime (), natureRetour.getTauxJournalierMoyen(), natureRetour.getPourcentPrime(), natureRetour.getPlafondQuotidien(), natureRetour.isDepassementFrais(), natureRetour.getDateDebut(), natureRetour.getDateFin());
	}

	@PatchMapping
	public NatureDTO modification (@RequestBody NatureDTO nature) {
		Nature natureRetour = natureService.modificationNature (nature);
		return new NatureDTO (natureRetour.getId (), natureRetour.getNomNature (), natureRetour.isFacturee(), natureRetour.isPrime (), natureRetour.getTauxJournalierMoyen(), natureRetour.getPourcentPrime(), natureRetour.getPlafondQuotidien(), natureRetour.isDepassementFrais(), natureRetour.getDateDebut(), natureRetour.getDateFin());
	}

	@DeleteMapping
	public void suppression (@RequestBody int nature) {
		natureService.suppressionNature (nature);
	}

	@GetMapping
	public List <NatureDTO> recupererNatures () {
		return natureService.afficherToutesNatures();
	}


}

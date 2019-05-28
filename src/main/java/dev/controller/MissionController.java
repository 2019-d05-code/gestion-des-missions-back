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

import dev.Utils.DtoUtils;
import dev.domain.Mission;
import dev.domainDto.MissionDto;
import dev.repository.CollegueRepo;
import dev.service.CollegueService;
import dev.service.MissionService;

@RestController
@RequestMapping("/mission")
public class MissionController {

	@Autowired
	private MissionService missionService;

	@Autowired
	public CollegueRepo collegueRepo;

	@Autowired
	CollegueService col;

	@GetMapping
	public List<MissionDto> afficherToutesLesMissions() {
		return this.missionService.recupererToutesLesMissions();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<MissionDto> afficherParId(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(missionService.trouverMissionDepuisId(id));
	}

	@PostMapping
	public ResponseEntity<Boolean> creer(@RequestBody MissionDto mission) {
		Mission miss = DtoUtils.toMissionAvecMail(mission, col);
		this.missionService.ajouterMission(miss);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<Object> modifier(@PathVariable int id, @RequestBody MissionDto nouvelleMission) {
		// La mission à modifier est indiquée dans l'URL depuis son identifiant
		this.missionService.modifierMission(id, DtoUtils.toMission(nouvelleMission));
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> supprimer(@PathVariable Integer id) {

		this.missionService.missionSupprimer(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}

package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domainDto.MissionDto;
import dev.service.MissionService;

@RestController
@RequestMapping("/mission")
public class MissionController {

	@Autowired
	private MissionService missionService;

	@GetMapping
	public List<MissionDto> searchAll() {
		return this.missionService.recupererToutesLesMissions();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<MissionDto> afficherParId(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(missionService.trouverMissionDepuisId(id));
	}

	@PostMapping
	public ResponseEntity<Boolean> creer(@RequestBody MissionDto mission) {
		this.missionService.ajouterMission(mission);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<Object> modifier(@RequestBody MissionDto nouvelleMission, @PathVariable int id) {
		// La mission à modifier est indiquée dans l'URL depuis son identifiant
		this.missionService.modifierMission(id, nouvelleMission);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}

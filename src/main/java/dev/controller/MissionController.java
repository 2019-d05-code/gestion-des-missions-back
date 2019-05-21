package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

		return this.missionService.findAllMission();
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody MissionDto mission) {

		this.missionService.ajouterMission(mission);

		return ResponseEntity.status(HttpStatus.OK).body(mission);
	}

}

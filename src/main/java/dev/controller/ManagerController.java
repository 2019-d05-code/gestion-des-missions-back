package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.ManagerMission;
import dev.domainDto.MissionDto;
import dev.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@GetMapping
	//@Secured("ROLE_MANAGER")
	public List<MissionDto> searchAll() {

		return this.managerService.findAllMission();
	}

	@PatchMapping
	//@Secured("ROLE_MANAGER")
	public ResponseEntity<Object> modifierEmail(@RequestBody ManagerMission mission) {

		MissionDto missionDto = (this.managerService.modifierStatut(mission));

		return ResponseEntity.status(HttpStatus.OK).body(missionDto);
	}

}

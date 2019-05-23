package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domainDto.MissionDto;
import dev.service.MissionService;

@RestController
@RequestMapping("/collegue")
public class CollegueController {
	@Autowired
	private MissionService missionService;

	@GetMapping(path = "/{id}")
	public List<MissionDto> afficherToutesLesMissions(@PathVariable Integer id) {
		return this.missionService.recupererMissionParCollegue(id);
	}

}

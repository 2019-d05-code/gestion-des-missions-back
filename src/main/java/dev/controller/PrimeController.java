package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domainDto.MissionDto;
import dev.service.PrimeService;

@RestController
@RequestMapping("/prime")
public class PrimeController {

	@Autowired
	private PrimeService primeService;

	@GetMapping
	public List<MissionDto> afficherToutesLesMissionsAsc() {
		return this.primeService.missionAvecPrimeAsc();
	}

}

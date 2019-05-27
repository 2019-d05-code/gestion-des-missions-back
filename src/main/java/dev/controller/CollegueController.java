package dev.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domainDto.MissionDto;
import dev.repository.CollegueRepo;
import dev.service.MissionService;

@RestController
@RequestMapping("/collegue")
@Secured("ROLE_EMPLOYE")
public class CollegueController {

	private static final Logger LOG = LoggerFactory.getLogger(MissionService.class);

	@Autowired
	private MissionService missionService;

	@Autowired
	private CollegueRepo collegueRepo;

	@GetMapping(path = "/{email}")
	public List<MissionDto> afficherToutesLesMissions(@PathVariable String email) {
		return this.missionService.recupererMissionParCollegue(email);
	}

	/*@GetMapping(path = "/{id}")
	public List<MissionDto> afficherToutesLesMissions(@PathVariable int id) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			Collegue idCollegueConnecte = this.collegueRepo.findByEmail(email)
					.orElseThrow(() -> new CollegueNonTrouveException("Collegue non trouvé"));


			if (idCollegueConnecte.getId() == id) {
				return this.missionService.recupererMissionParCollegue(id);
			}
		} catch (CollegueNonTrouveException e) {
			LOG.error("Collegue non trouvé");
		}
		return null;
	}*/
}
package dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.Exception.FraisInvalideException;
import dev.Utils.DtoUtils;
import dev.domain.LigneDeFrais;
import dev.domain.Mission;
import dev.domainDto.FraisDto;
import dev.repository.FraisRepo;
import dev.repository.MissionRepo;
import dev.service.FraisService;

@RestController
@RequestMapping("/frais")
@Secured("ROLE_EMPLOYE")
public class FraisController
{
    // - injection dependance -
    @Autowired
    private FraisService fraisService;

    @Autowired
    private FraisRepo fraisRepo;

    @Autowired
    private MissionRepo missionRepo;

    @GetMapping
    public List<FraisDto> afficherToutesLesFrais()
    {
	return fraisRepo.findAll ().stream().map(frais -> new FraisDto (frais.getId (), frais.getDate(), frais.getNature(), frais.getMontant(), frais.getMission().getId())).collect (Collectors.toList());
    }

    @GetMapping(path = "/{idMiss}")
    public List<FraisDto> afficherToutesLesFrais(@PathVariable int idMiss)
    {
	return this.fraisService.envoyerListeFrais(idMiss);
    }

    @PostMapping(path = "/{idMiss}")
    public void enregistrerFrais(@PathVariable int idMiss, @RequestBody FraisDto dto)
    {
	LigneDeFrais frais = DtoUtils.dtoVersFrais(dto);
	Mission miss = missionRepo.findById(idMiss).orElseThrow(() -> new FraisInvalideException ("La mission n'existe pas !"));
	frais.setMission(miss);
	this.fraisService.ajouterFrais(frais);
    }

    @PutMapping(path = "/{idMiss}")
    public void changerFrais(@PathVariable int idMiss, @RequestBody FraisDto dto)
    {
	LigneDeFrais frais = DtoUtils.dtoVersFrais(dto);
	frais.setId(dto.getId());
	Mission miss = missionRepo.findById(idMiss).orElseThrow(() -> new FraisInvalideException ("La mission n'existe pas !"));
	frais.setMission(miss);
	this.fraisService.modifierFrais(frais);
    }

    @DeleteMapping(path = "/{idFrais}")
    public void enleverFrais(@PathVariable int idFrais )
    {
	this.fraisService.supprimerFrais(idFrais);
    }

}

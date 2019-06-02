package dev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Exception.FraisInvalideException;
import dev.Utils.DtoUtils;
import dev.domain.LigneDeFrais;
import dev.domain.Mission;
import dev.domainDto.FraisDto;
import dev.repository.FraisRepo;
import dev.repository.MissionRepo;

@Service
public class FraisService
{

    private FraisRepo fraisRepo;

    @Autowired
    private MissionRepo missionRepo;

    @Autowired
    public FraisService(FraisRepo fraisRepo) {
	this.fraisRepo = fraisRepo;
    }

    // - ajouter -
    public void ajouterFrais(LigneDeFrais frais)
    {
	// Envoi d'une exception en cas de non-respect des règles métier
	if (montantPositif(frais.getMontant()) &&
		verificationDate(frais.getMission().getDateDebut(),frais.getMission().getDateFin(),frais.getDate()) &&
		verificationUnique(fraisRepo.findAll(), frais) )
	{
	    fraisRepo.save(frais);
	}
	else
	{
	    throw new FraisInvalideException("Les paramètres sont invalides");
	}

    }

    // - recuperer des frais
    public List<FraisDto> envoyerListeFrais(int idMiss)
    {
	Mission miss = missionRepo.findById(idMiss).get();
	List<LigneDeFrais> liste = fraisRepo.findByMission(miss);
	List<FraisDto> sortie = new ArrayList<>();
	for(LigneDeFrais f:liste)
	{
	    sortie.add(DtoUtils.fraisVersDto(f));
	}

	return sortie;
    }

    // - supprimer des frais
    public void supprimerFrais(int idFrais)
    {
	fraisRepo.deleteById(idFrais);
    }

    // - modifier des frais
    public void modifierFrais(LigneDeFrais frais)
    {
	if (montantPositif(frais.getMontant()) &&
		verificationDate(frais.getMission().getDateDebut(),frais.getMission().getDateFin(),frais.getDate()))
	{
	    fraisRepo.save(frais);
	}
	else
	{
	    throw new FraisInvalideException("Les paramètres sont invalides");
	}

    }

    // - regle metier -
    public boolean montantPositif(double montant)
    {
	return montant>0;
    }

    public boolean verificationDate(LocalDate debut, LocalDate fin, LocalDate date)
    {
	return ( date.isAfter(debut) && date.isBefore(fin) );
    }

    public boolean verificationUnique(List<LigneDeFrais> liste, LigneDeFrais frais)
    {
	for(LigneDeFrais l:liste)
	{
	    if(l.getDate().isEqual(frais.getDate()) && l.getNature().equals(frais.getNature()) )
	    { return false; }
	}
	return true;
    }

}

package dev.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.LigneDeFrais;
import dev.repository.FraisRepo;

@Service
public class FraisService
{
	
	private FraisRepo fraisRepo;

	@Autowired
	public FraisService(FraisRepo fraisRepo) {
		this.fraisRepo = fraisRepo;
	}
	
	// - ajouter -
	public void ajouterFrais(LigneDeFrais frais) {
		// Envoi d'une exception en cas de non-respect des règles métier
		if (montantPositif(frais.getMontant()) && 
				verificationDate(frais.getMission().getDateDebut(),frais.getMission().getDateFin(),frais.getDate()) && 
				verificationUnique(fraisRepo.findAll(), frais) )
		{
			fraisRepo.save(frais);
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

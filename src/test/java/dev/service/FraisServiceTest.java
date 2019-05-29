package dev.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dev.Exception.FraisInvalideException;
import dev.domain.Collegue;
import dev.domain.LigneDeFrais;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.NatureFrais;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Statut;
import dev.domain.Transport;
import dev.repository.FraisRepo;


public class FraisServiceTest
{
	//creation des elements du test
	private FraisRepo testRepo;
	private FraisService testServ;
	private Collegue coll;
	private Mission miss;

	@Before //initialisation
	public void init()
	{
		testRepo = Mockito.mock(FraisRepo.class);
		testServ = new FraisService(testRepo); //on indique quel est le repo a utiliser
		
		coll = new Collegue();
		coll.setNom("Admin");
		coll.setPrenom("DEV");
		coll.setEmail("admin@dev.fr");
		coll.setMotDePasse("superpass");
		coll.setRoles(Arrays.asList(new RoleCollegue(coll, Role.ROLE_ADMINISTRATEUR),
				new RoleCollegue(coll, Role.ROLE_EMPLOYE), new RoleCollegue(coll, Role.ROLE_UTILISATEUR)));
		
		miss = new Mission();
		miss.setDateDebut(LocalDate.parse("2019-10-31"));
		miss.setDateFin(LocalDate.parse("2019-12-01"));
		miss.setNature(Nature.Technique);
		miss.setVilleDepart("Bordeaux");
		miss.setVilleArrivee("Strasbourg");
		miss.setStatut(Statut.EN_ATTENTE_VALIDATION);
		miss.setTransport(Transport.VoitureDeService);
		miss.setCollegue(coll);
		
	}

	// - test sur repo - 
	@Test
	public void testAjouterFrais() throws FraisInvalideException
	{
		LigneDeFrais frais = new LigneDeFrais(LocalDate.parse("2019-11-08"), NatureFrais.Restaurant, 15, miss);
		testServ.ajouterFrais(frais);

		Mockito.verify(testRepo).save(frais);
	}
	
	@Test
	public void testEnvoyerListeFrais() throws FraisInvalideException
	{
		List<LigneDeFrais> test = testRepo.findByMission(miss);

		Mockito.verify(testRepo).findByMission(miss);
	}
	@Test
	public void testSupprimerFrais() throws FraisInvalideException
	{
		testRepo.deleteById(1);

		Mockito.verify(testRepo).deleteById(1);
	}

	// - test regle metier - 
	@Test
	public void testMontantPositif()
	{
		Assert.assertTrue(testServ.montantPositif(10));
	}
	
	@Test
	public void testVerificationDate()
	{
		Assert.assertTrue(testServ.verificationDate(LocalDate.parse("2019-05-01"), LocalDate.parse("2019-05-31"), LocalDate.parse("2019-05-15") ) );
	}
	
	@Test
	public void testVerificationUnique()
	{
		LigneDeFrais frais = new LigneDeFrais(LocalDate.parse("2019-11-08"), NatureFrais.Restaurant, 15, miss);
		Assert.assertTrue(testServ.verificationUnique(testRepo.findAll(), frais));
	}

}


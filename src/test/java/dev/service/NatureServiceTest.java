/**
 *
 */
package dev.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.Exception.NatureInvalideException;
import dev.domain.Nature;
import dev.domainDto.NatureDTO;
import dev.repository.NatureRepository;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class NatureServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(NatureServiceTest.class);

	private NatureService natureService;
	private NatureRepository natureRepository;

	private NatureDTO natDTO;
	private NatureDTO modifNatDTO;

	private Nature nat;
	private Nature natReturn;

	@Before
	public void init() {
		natureService = new NatureService();
		natureRepository = Mockito.mock(NatureRepository.class);
		natureService.setNatureRepository(natureRepository);

		natDTO = new NatureDTO ("Congés", false, false, 0, 0, 0, false, LocalDate.now ());
		natReturn = new Nature ("Congés", false, false, 0, 0, 0, false, LocalDate.now ());
		natureRepository.save(natReturn);
		modifNatDTO = new NatureDTO (0, "Vacances", false, false, 0, 0, 0, false, LocalDate.now ());
	}

	// Si l'on attend qu'une exception soit lancée, on modifie cette variable
	// Permet de récupérer un message d'erreur précis.
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void ajoutNature_sauvegardeRéussie () {
		LOG.info("Ajout d'une nature réussie");


		nat = natureService.ajoutNature(natDTO);
		LOG.info(nat.toString());
		Mockito.verify(natureRepository).save(nat);
	}

	@Test
	public void ajoutNature_plafondNegatif () {
		exception.expect(NatureInvalideException.class);
		exception.expectMessage("Le plafond quotidien doit être positif !");
		natDTO.setFacturee (true);
		natDTO.setPlafondQuotidien (-200);
		nat = natureService.ajoutNature(natDTO);
	}

	@Test
	public void ajoutNature_TJMNegatif () {
		exception.expect(NatureInvalideException.class);
		exception.expectMessage("Le taux journalier moyen doit exister pour une nature facturée !");
		natDTO.setFacturee (true);
		natDTO.setTauxJournalierMoyen (-200);
		nat = natureService.ajoutNature(natDTO);
	}

	@Test
	public void ajoutNature_PourcentPrimeNegatif () {
		exception.expect(NatureInvalideException.class);
		exception.expectMessage("Le taux de la prime doit être renseigner pour une nature incluant une prime !");
		natDTO.setPrime (true);
		natDTO.setPourcentPrime (-2);
		nat = natureService.ajoutNature(natDTO);
	}

	@Test
	public void ajoutNature_DateDebutNull () {
		exception.expect(NatureInvalideException.class);
		exception.expectMessage("La date de début est obligatoire !");
		natDTO.setDateDebut(null);
		nat = natureService.ajoutNature(natDTO);
	}

	@Test
	public void ajoutNature_DateFinAvantDebut () {
		exception.expect(NatureInvalideException.class);
		exception.expectMessage("La date de fin ne doit pas être précéder la date de début !");
		natDTO.setDateFin(LocalDate.now ().minusDays(1));
		nat = natureService.ajoutNature(natDTO);
	}

	@Test
	public void ajoutNature_DateDebutEgaleDateFin () {
		natDTO.setDateFin(LocalDate.now ());
		nat = natureService.ajoutNature(natDTO);
		Mockito.verify(natureRepository).save(nat);
	}
}

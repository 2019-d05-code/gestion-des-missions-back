import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.Exception.MissionInvalidException;
import dev.Exception.MissionNonTrouveeException;
import dev.Exception.ModificationInvalideException;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Statut;
import dev.domain.Transport;
import dev.repository.MissionRepo;
import dev.service.MissionService;

public class MissionServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(MissionServiceTest.class);

	private MissionService missionService;

	private MissionRepo missionRepo;

	@Before
	public void init() {
		missionService = new MissionService();
		missionRepo = Mockito.mock(MissionRepo.class);
		missionService.setMissionRepository(missionRepo);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testIfTheDateIsToday() {
		exception.expect(MissionInvalidException.class);
		exception.expectMessage(" La mission ne peut pas démarrer le jour même ou avant. ");
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now(), LocalDate.now().plusDays(80), Nature.Conseil, "Toulouse",
				"Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);

	}

	@Test
	public void testIfTheDateIsNotToday() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().plusDays(8), Nature.Conseil,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);
	}

	@Test
	public void testIfTheDateFinIsInPast() {
		exception.expect(MissionInvalidException.class);
		exception.expectMessage(" La date de Fin n'est pas correcte. ");
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().minusDays(1), Nature.Technique,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);

	}

	@Test
	public void testIfTheDateFinIsInToday() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), Nature.Conseil,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);
	}

	@Test
	public void testIfTheTransAvion() {
		exception.expect(MissionInvalidException.class);
		exception.expectMessage(" Il faut une anticipation de 7 jours pour prendre l'avion. ");
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(5), LocalDate.now().plusDays(20), Nature.Conseil,
				"Toulouse", "Bordeaux", Transport.Avion);
		this.missionService.ajouterMission(newMission);

	}

	@Test
	public void testIfProjectPresent() {
		Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), Nature.Expertise,
				"Toulouse", "Bordeaux", Transport.Avion);
		Assert.assertTrue(newMission.getDateDebut().isAfter(LocalDate.now().plusDays(8))
				&& newMission.getDateDebut().isBefore(LocalDate.now().plusDays(20)));
		this.missionService.ajouterMission(newMission);

	}

	@Test
	public void testForBaseDesDonne() {
		Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);
		Mockito.when(missionRepo.findById(newMission.getId())).thenReturn(Optional.of(newMission));
	}

	@Test
	public void testModifierMissionNOK_IdInvalide() {
		exception.expect(MissionNonTrouveeException.class);
		exception.expectMessage("Aucune mission ne correspond à cet ID");
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		int id = 42;
		this.missionService.modifierMission(id, mission);
	}

	@Test
	public void testModifierMissionNOK_dateDebutInvalide() {
		exception.expect(MissionInvalidException.class);
		exception.expectMessage(" La mission ne peut pas démarrer le jour même ou avant. ");
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		Mission modifications = new Mission(LocalDate.now().plusDays(0), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.modifierMission(mission.getId(), modifications);
	}

	@Test
	public void testModifierMissionNOK_dateFinInvalide() {
		exception.expect(MissionInvalidException.class);
		exception.expectMessage(" La date de Fin n'est pas correcte. ");
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		Mission modifications = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(0), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.modifierMission(mission.getId(), modifications);
	}

	@Test
	public void testModifierMissionNOK_AvionInvalide() {
		exception.expect(MissionInvalidException.class);
		exception.expectMessage(" Il faut une anticipation de 7 jours pour prendre l'avion. ");
		Mission mission = new Mission(LocalDate.now().plusDays(3), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		Mission modifications = new Mission(LocalDate.now().plusDays(3), LocalDate.now().plusDays(4), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Avion);
		this.missionService.modifierMission(mission.getId(), modifications);
	}

	@Test
	public void testModifierMissionNOK_AvionValide() {
		Mission mission = new Mission(LocalDate.now().plusDays(3), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		Mission modifications = new Mission(LocalDate.now().plusDays(7), LocalDate.now().plusDays(7), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Avion);
		this.missionService.modifierMission(mission.getId(), modifications);
	}

	@Test
	public void testModifierMissionNOK_statutInvalide_EN_ATTENTE_VALIDATION() {
		exception.expect(ModificationInvalideException.class);
		exception.expectMessage(" Les missions en attentes ou validées ne peuvent plus être modifiées. ");
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		mission.setStatut(Statut.EN_ATTENTE_VALIDATION);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		Mission modifications = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(0), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.modifierMission(mission.getId(), modifications);
	}

	@Test
	public void testModifierMissionNOK_statutInvalide_VALIDEE() {
		exception.expect(ModificationInvalideException.class);
		exception.expectMessage(" Les missions en attentes ou validées ne peuvent plus être modifiées. ");
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		mission.setStatut(Statut.VALIDEE);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		Mission modifications = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(0), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.modifierMission(mission.getId(), modifications);
	}

	@Test
	public void testModifierMissionOK() {
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(mission);
		int id = mission.getId();
		Mockito.when(missionRepo.findById(id)).thenReturn(Optional.of(mission));
		Mission modifications = new Mission(LocalDate.now().plusDays(1), LocalDate.now().plusDays(79), Nature.Conseil,
				"Nantes", "Carquefou", Transport.Covoiturage);

		Assert.assertFalse(mission.equals(modifications));
		this.missionService.modifierMission(id, modifications);
		Assert.assertTrue(mission.equals(modifications));
	}

	@Test
	public void testSupprimerMission() {
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(mission);
		// vérifie si la mission est présente en BDD 
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		this.missionService.missionSupprimer(mission.getId());
		// vérifie si la mission a été retirée de la BDD
		Mockito.when(missionRepo.existsById(mission.getId())).thenReturn(true);
	}
}

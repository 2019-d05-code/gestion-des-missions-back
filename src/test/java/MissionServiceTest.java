import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.Exception.MissionInvalidException;
import dev.domain.Mission;
import dev.domain.Nature;
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

	@Test(expected = MissionInvalidException.class)
	public void testIfTheDateIsToday() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now(), LocalDate.now().plusDays(80), Nature.Conseil, "Toulouse",
				"Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);

	}

	@Test
	public void testIfTheDateIsIsToday() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().plusDays(8), Nature.Conseil,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);

		// Assert.assertTrue(result.getVilleDepart().equals("Toulouse"));

	}

	@Test(expected = MissionInvalidException.class)
	public void testIfTheDateFinIsInPast() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().minusDays(1), Nature.Technique,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);

	}

	@Test
	public void testIfTheDateFinIsIntoday() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now(), Nature.Conseil, "Toulouse",
				"Bordeaux", Transport.Train);
		this.missionService.ajouterMission(newMission);
	}

	@Test(expected = MissionInvalidException.class)
	public void testIfTheTransAvion() {
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
	
	@Test(expected = RuntimeException.class)
	public void testModifierMissionNOK_IdInvalide() {
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		int id = 42 ;
		this.missionService.modifierMission(id, mission);
	}
	
	@Test(expected = MissionInvalidException.class)
	public void testModifierMissionNOK_dateDebutInvalide() {
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		Mission modifications = new Mission (LocalDate.now().plusDays(0), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.modifierMission(mission.getId(), modifications);
		
	}
	
	@Test
	public void testModifierMissionOK_dateDebutValide() {
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
		Mission modifications = new Mission (LocalDate.now().plusDays(1), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train);
		this.missionService.modifierMission(mission.getId(), modifications);
	}

}

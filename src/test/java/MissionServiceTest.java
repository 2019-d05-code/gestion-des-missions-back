import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.Exception.MissionInvalidException;
import dev.Utils.DtoUtils;
import dev.domain.Mission;
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
		Mission newMission = new Mission(LocalDate.now(), LocalDate.now().plusDays(80), "Toulouse", "Bordeaux",
				Transport.Train);
		this.missionService.ajouterMission(DtoUtils.toMissionDto(newMission));

	}

	@Test
	public void testIfTheDateIsIsToday() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().plusDays(8), "Toulouse",
				"Bordeaux", Transport.Train);
		this.missionService.ajouterMission(DtoUtils.toMissionDto(newMission));

		// Assert.assertTrue(result.getVilleDepart().equals("Toulouse"));

	}

	@Test(expected = MissionInvalidException.class)
	public void testIfTheDateFinIsInPast() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().minusDays(1), "Toulouse",
				"Bordeaux", Transport.Train);
		this.missionService.ajouterMission(DtoUtils.toMissionDto(newMission));

	}

	@Test
	public void testIfTheDateFinIsIntoday() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now(), "Toulouse", "Bordeaux",
				Transport.Train);
		this.missionService.ajouterMission(DtoUtils.toMissionDto(newMission));
	}

	@Test(expected = MissionInvalidException.class)
	public void testIfTheTransAvion() {
		LOG.info("Etant donné, une instance de Mission");
		Mission newMission = new Mission(LocalDate.now().plusDays(5), LocalDate.now().plusDays(20), "Toulouse",
				"Bordeaux", Transport.Avion);
		this.missionService.ajouterMission(DtoUtils.toMissionDto(newMission));

	}

	@Test
	public void testIfProjectPresent() {
		Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), "Toulouse",
				"Bordeaux", Transport.Avion);
		Assert.assertTrue(newMission.getDateDebut().isAfter(LocalDate.now().plusDays(8))
				&& newMission.getDateDebut().isBefore(LocalDate.now().plusDays(20)));
		this.missionService.ajouterMission(DtoUtils.toMissionDto(newMission));

	}

}

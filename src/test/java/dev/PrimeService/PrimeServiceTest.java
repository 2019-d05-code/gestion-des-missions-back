package dev.PrimeService;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Transport;
import dev.repository.MissionRepo;
import dev.repository.PrimeRepo;
import dev.service.MissionService;
import dev.service.PrimeService;

public class PrimeServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(PrimeServiceTest.class);

	private PrimeService primeService;

	private MissionService missionService;

	private MissionRepo missionRepo;

	private PrimeRepo primeRepo;

	@Before
	public void init() {
		primeService = new PrimeService();
		primeRepo = Mockito.mock(PrimeRepo.class);
		primeService.setPrimeRepository(primeRepo);
		missionService = new MissionService();
		missionRepo = Mockito.mock(MissionRepo.class);
		missionService.setMissionRepository(missionRepo);
	}

	@Test
	public void TestPourAscList() {
		Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), Nature.Formation,
				"Toulouse", "Bordeaux", Transport.Train, 1500);
		Mission mission2 = new Mission(LocalDate.now().plusDays(90), LocalDate.now().plusDays(150), Nature.Formation,
				"Toulouse", "nantes", Transport.Covoiturage, 450);
		Mission mission3 = new Mission(LocalDate.now().plusDays(200), LocalDate.now().plusDays(300), Nature.Formation,
				"Toulouse", "pau", Transport.Avion, 900);

		List<Mission> missionList = new ArrayList<>();
		Mockito.when(missionRepo.save(mission)).thenReturn(mission);
		this.missionService.ajouterMission(mission);
		Mockito.when(missionRepo.save(mission)).thenReturn(mission2);
		this.missionService.ajouterMission(mission2);
		Mockito.when(missionRepo.save(mission)).thenReturn(mission3);
		this.missionService.ajouterMission(mission3);
//
//		Mockito.when(primeRepo.sortAllMissionAsc()).thenReturn(missionList);
//		this.primeRepo.sortAllMissionAsc();
//		Mockito.verify(primeRepo).sortAllMissionAsc();
//		assertTrue(missionList.get(0).getPrime() > missionList.get(1).getPrime());
		// System.out.println(missionList.get(0).getPrime());
		// System.out.println(missionList.get(1).getPrime());

	}
}

package dev.ManagerService;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.Utils.DtoUtils;
import dev.domain.ManagerMission;
import dev.domain.Mission;
import dev.domain.Statut;
import dev.domain.Transport;
import dev.domainDto.MissionDto;
import dev.repository.MissionRepo;
import dev.service.ManagerService;
import dev.service.MissionService;

public class ManagerServiceTest {
	private static final Logger LOG = LoggerFactory.getLogger(ManagerServiceTest.class);

	private ManagerService managerService;

	private MissionRepo missionRepo;

	private MissionService serviceMission;

	@Before
	public void init() {
		managerService = new ManagerService();
		serviceMission = new MissionService();
		missionRepo = Mockito.mock(MissionRepo.class);
		managerService.setMissionRepository(missionRepo);
		serviceMission.setMissionRepository(missionRepo);
	}

	@Test
	public void testModification() {
		Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), "Toulouse",
				"Bordeaux", Transport.Train);
		newMission.setId(1);
		Mockito.when(missionRepo.findById(newMission.getId())).thenReturn(Optional.of(newMission));
		this.serviceMission.ajouterMission(DtoUtils.toMissionDto(newMission));
		ManagerMission miss = new ManagerMission(newMission.getId(), Statut.VALIDEE);
		MissionDto resultat = this.managerService.modifierStatut(miss);
		Assert.assertTrue(resultat.getStatut().equals(Statut.VALIDEE));

	}

	@Test
	public void testForBaseDesDonne() {
		Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), "Toulouse",
				"Bordeaux", Transport.Train);
		this.serviceMission.ajouterMission(DtoUtils.toMissionDto(newMission));
		Mockito.when(missionRepo.findById(newMission.getId())).thenReturn(Optional.of(newMission));
	}

}

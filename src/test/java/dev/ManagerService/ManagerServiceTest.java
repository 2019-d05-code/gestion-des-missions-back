package dev.ManagerService;

public class ManagerServiceTest {

    /*
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
		Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Conseil,
				"Toulouse", "Bordeaux", Transport.Train);
		newMission.setId(1);
		Mockito.when(missionRepo.findById(newMission.getId())).thenReturn(Optional.of(newMission));
		this.serviceMission.ajouterMission(newMission);
		ManagerMission miss = new ManagerMission(newMission.getId(), Statut.VALIDEE);
		MissionDto resultat = this.managerService.modifierStatut(miss);
		Assert.assertTrue(resultat.getStatut().equals(Statut.VALIDEE));
	}

	@Test
	public void testForBaseDesDonne() {
		Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Technique,
				"Toulouse", "Bordeaux", Transport.Train);
		this.serviceMission.ajouterMission(newMission);
		Mockito.when(missionRepo.findById(newMission.getId())).thenReturn(Optional.of(newMission));
	}
     */

}

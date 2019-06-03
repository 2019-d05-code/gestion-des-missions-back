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
import dev.domain.NatureEnum;
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

    /*
    @Test
    public void test_ajouter_mission_nok_date_debut_invalide_aujourdhui() {
	exception.expect(MissionInvalidException.class);
	exception.expectMessage(" La mission ne peut pas démarrer le jour même ou avant. ");
	LOG.info("Etant donné, une instance de Mission");
	Mission newMission = new Mission(LocalDate.now(), LocalDate.now().plusDays(80), NatureEnum.Conseil, "Toulouse",
		"Bordeaux", Transport.Train);
	this.missionService.ajouterMission(newMission);
    }

    @Test
    public void test_ajouter_mission_ok_date_debut_valide() {
	LOG.info("Etant donné, une instance de Mission");
	Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().plusDays(8), NatureEnum.Conseil,
		"Toulouse", "Bordeaux", Transport.Train);
	this.missionService.ajouterMission(newMission);
    }

    @Test
    public void test_ajouter_mission_nok_date_fin_passee_invalide() {
	exception.expect(MissionInvalidException.class);
	exception.expectMessage(" La date de Fin n'est pas correcte. ");
	LOG.info("Etant donné, une instance de Mission");
	Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().minusDays(1), NatureEnum.Technique,
		"Toulouse", "Bordeaux", Transport.Train);
	this.missionService.ajouterMission(newMission);
    }

    @Test
    public void test_ajouter_mission_ok_date_debut_meme_jour_date_fin() {
	LOG.info("Etant donné, une instance de Mission");
	Mission newMission = new Mission(LocalDate.now().plusDays(1), LocalDate.now().plusDays(1), NatureEnum.Conseil,
		"Toulouse", "Bordeaux", Transport.Train);
	this.missionService.ajouterMission(newMission);
    }

    @Test
    public void test_ajouter_mission_nok_date_invalide_pour_transport_avion() {
	exception.expect(MissionInvalidException.class);
	exception.expectMessage(" Il faut une anticipation de 7 jours pour prendre l'avion. ");
	LOG.info("Etant donné, une instance de Mission");
	Mission newMission = new Mission(LocalDate.now().plusDays(5), LocalDate.now().plusDays(20), NatureEnum.Conseil,
		"Toulouse", "Bordeaux", Transport.Avion);
	this.missionService.ajouterMission(newMission);
    }

    @Test
    public void test_ajouter_mission_ok_toutes_regles_metier_valides() {
	Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), NatureEnum.Expertise,
		"Toulouse", "Bordeaux", Transport.Avion);
	Assert.assertTrue(newMission.getDateDebut().isAfter(LocalDate.now().plusDays(8))
		&& newMission.getDateDebut().isBefore(LocalDate.now().plusDays(20)));
	this.missionService.ajouterMission(newMission);
    }

    @Test
    public void test_ajouter_mission_ok() {
	Mission newMission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Formation,
		"Toulouse", "Bordeaux", Transport.Train, 100);
	this.missionService.ajouterMission(newMission);
	Mockito.verify(missionRepo).save(newMission);
    }

    @Test
    public void test_modifier_mission_nok_id_invalide() {
	exception.expect(MissionNonTrouveeException.class);
	exception.expectMessage("Aucune mission ne correspond à cet ID");
	Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Formation,
		"Toulouse", "Bordeaux", Transport.Train);
	int id = 42;
	this.missionService.modifierMission(id, mission);
    }

    @Test
    public void test_modifier_mission_nok_date_debut_invalide() {
	exception.expect(MissionInvalidException.class);
	exception.expectMessage(" La mission ne peut pas démarrer le jour même ou avant. ");
	// Instanciation et insertion d'une mission en BDD mockée
	Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Formation,
		"Toulouse", "Bordeaux", Transport.Train);
	this.missionService.ajouterMission(mission);

	// Récupère la mission à modifier à partir d'une recherche par ID
	Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));

	/*
     * Instanciation d'une mission correspondant aux modifications à
     * apporter à la mission existante
     */
    Mission modifications = new Mission(LocalDate.now().plusDays(0), LocalDate.now().plusDays(80), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train, 100);
    this.missionService.modifierMission(mission.getId(), modifications);
}

@Test
public void test_modifier_mission_nok_date_fin_invalide() {
    exception.expect(MissionInvalidException.class);
    exception.expectMessage(" La date de Fin n'est pas correcte. ");
    Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    this.missionService.ajouterMission(mission);
    Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
    Mission modifications = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(0), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    this.missionService.modifierMission(mission.getId(), modifications);
}

@Test
public void test_modifier_mission_nok_avion_invalide() {
    exception.expect(MissionInvalidException.class);
    exception.expectMessage(" Il faut une anticipation de 7 jours pour prendre l'avion. ");
    Mission mission = new Mission(LocalDate.now().plusDays(3), LocalDate.now().plusDays(80), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    this.missionService.ajouterMission(mission);
    Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
    Mission modifications = new Mission(LocalDate.now().plusDays(3), LocalDate.now().plusDays(4), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Avion);
    this.missionService.modifierMission(mission.getId(), modifications);
}

@Test
public void test_modifier_mission_nok_avion_valide() {
    Mission mission = new Mission(LocalDate.now().plusDays(3), LocalDate.now().plusDays(80), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    this.missionService.ajouterMission(mission);
    Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
    Mission modifications = new Mission(LocalDate.now().plusDays(7), LocalDate.now().plusDays(7), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Avion);
    this.missionService.modifierMission(mission.getId(), modifications);
}


@Test
public void test_modifier_mission_nok_statut_invalide_en_attente_validation() {
    exception.expect(ModificationInvalideException.class);
    exception.expectMessage(" Les missions en attentes ou validées ne peuvent plus être modifiées. ");
    Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    mission.setStatut(Statut.EN_ATTENTE_VALIDATION);
    this.missionService.ajouterMission(mission);
    Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
    Mission modifications = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(0), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    this.missionService.modifierMission(mission.getId(), modifications);
}*

@Test
public void test_modifier_mission_nok_statut_invalide_mission_validee() {
    exception.expect(ModificationInvalideException.class);
    exception.expectMessage(" Les missions en attentes ou validées ne peuvent plus être modifiées. ");
    Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    mission.setStatut(Statut.VALIDEE);
    this.missionService.ajouterMission(mission);
    Mockito.when(missionRepo.findById(mission.getId())).thenReturn(Optional.of(mission));
    Mission modifications = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(0), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    this.missionService.modifierMission(mission.getId(), modifications);
}

@Test
public void test_modifier_mission_ok() {
    Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train);
    this.missionService.ajouterMission(mission);
    int id = mission.getId();
    Mockito.when(missionRepo.findById(id)).thenReturn(Optional.of(mission));

    Mission modifications = new Mission(LocalDate.now().plusDays(1), LocalDate.now().plusDays(79), NatureEnum.Conseil,
	    "Nantes", "Carquefou", Transport.Covoiturage, 230);

    Assert.assertFalse(mission.equals(modifications));
    this.missionService.modifierMission(id, modifications);
    Assert.assertTrue(mission.equals(modifications));
}

@Test
public void test_supprimer_mission_ok() {
    Mission mission = new Mission(LocalDate.now().plusDays(10), LocalDate.now().plusDays(80), NatureEnum.Formation,
	    "Toulouse", "Bordeaux", Transport.Train, 365);

    this.missionService.ajouterMission(mission);
    // vérifie si la mission est présente en BDD
    Mockito.verify(missionRepo).save(mission);
    this.missionService.missionSupprimer(mission.getId());
    // vérifie si la mission a été retirée de la BDD
    Mockito.verify(missionRepo).deleteById(mission.getId());
}

*/
}

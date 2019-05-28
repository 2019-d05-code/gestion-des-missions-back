package dev;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Collegue;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Statut;
import dev.domain.Transport;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private MissionRepo missionRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, MissionRepo missionRepo) {
		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
		this.missionRepo = missionRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		this.versionRepo.save(new Version(appVersion));

		// Création de deux utilisateurs

		Collegue col1 = new Collegue();
		col1.setNom("Admin");
		col1.setPrenom("DEV");
		col1.setEmail("admin@dev.fr");
		col1.setMotDePasse(passwordEncoder.encode("superpass"));
		col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR),
				new RoleCollegue(col1, Role.ROLE_EMPLOYE), new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col1);

		Collegue col2 = new Collegue();
		col2.setNom("User");
		col2.setPrenom("DEV");
		col2.setEmail("user@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_EMPLOYE), new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col2);

		Collegue col3 = new Collegue();
		col3.setNom("Manag");
		col3.setPrenom("DEV");
		col3.setEmail("manag@dev.fr");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(
				Arrays.asList(new RoleCollegue(col3, Role.ROLE_MANAGER), new RoleCollegue(col3, Role.ROLE_EMPLOYE), new RoleCollegue(col3, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col3);

		Collegue col4 = new Collegue();
		col3.setNom("Manager");
		col3.setPrenom("DEV");
		col3.setEmail("manager@dev.fr");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(Arrays.asList(new RoleCollegue(col4, Role.ROLE_EMPLOYE), new RoleCollegue(col4, Role.ROLE_MANAGER), new RoleCollegue(col4, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col4);

		Collegue col5 = new Collegue();
		col4.setNom("Manager2");
		col4.setPrenom("DEV");
		col4.setEmail("manager2@dev.fr");
		col4.setMotDePasse(passwordEncoder.encode("superpass"));
		col4.setRoles(Arrays.asList(new RoleCollegue(col5, Role.ROLE_EMPLOYE), new RoleCollegue(col5, Role.ROLE_MANAGER), new RoleCollegue(col5, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col5);



		// creation de trois (enfin six) missions

		Mission miss1 = new Mission();
		miss1.setDateDebut(LocalDate.parse("2015-05-15"));
		miss1.setDateFin(LocalDate.parse("2015-09-25"));
		miss1.setNature(Nature.Conseil);
		miss1.setVilleDepart("Pau");
		miss1.setVilleArrivee("Bordeau");
		miss1.setStatut(Statut.REJETEE);
		miss1.setTransport(Transport.VoitureDeService);
		miss1.setCollegue(col2);
		this.missionRepo.save(miss1);

		Mission miss2 = new Mission();
		miss2.setDateDebut(LocalDate.parse("2015-12-01"));
		miss2.setDateFin(LocalDate.parse("2016-02-28"));
		miss2.setNature(Nature.Expertise);
		miss2.setVilleDepart("Nice");
		miss2.setVilleArrivee("Brest");
		miss2.setStatut(Statut.VALIDEE);
		miss2.setTransport(Transport.Train);
		miss2.setCollegue(col2);
		this.missionRepo.save(miss2);

		Mission miss3 = new Mission();
		miss3.setDateDebut(LocalDate.parse("2014-04-20"));
		miss3.setDateFin(LocalDate.parse("2016-07-10"));
		miss3.setNature(Nature.Formation);
		miss3.setVilleDepart("Paris");
		miss3.setVilleArrivee("Montpellier");
		miss3.setStatut(Statut.INITIALE);
		miss3.setTransport(Transport.Avion);
		miss3.setCollegue(col1);
		this.missionRepo.save(miss3);

		Mission miss4 = new Mission();
		miss4.setDateDebut(LocalDate.parse("2019-10-31"));
		miss4.setDateFin(LocalDate.parse("2019-12-01"));
		miss4.setNature(Nature.Technique);
		miss4.setVilleDepart("Bordeaux");
		miss4.setVilleArrivee("Strasbourg");
		miss4.setStatut(Statut.EN_ATTENTE_VALIDATION);
		miss4.setTransport(Transport.VoitureDeService);
		miss4.setCollegue(col1);
		this.missionRepo.save(miss4);

		Mission miss5 = new Mission();
		miss5.setDateDebut(LocalDate.parse("2019-10-15"));
		miss5.setDateFin(LocalDate.parse("2019-11-05"));
		miss5.setNature(Nature.Expertise);
		miss5.setVilleDepart("Albi");
		miss5.setVilleArrivee("St Etienne");
		miss5.setStatut(Statut.INITIALE);
		miss5.setTransport(Transport.Avion);
		miss5.setCollegue(col3);
		this.missionRepo.save(miss5);

		Mission miss6 = new Mission();
		miss6.setDateDebut(LocalDate.parse("2019-06-10"));
		miss6.setDateFin(LocalDate.parse("2019-07-25"));
		miss6.setVilleDepart("Frejus");
		miss6.setVilleArrivee("Cannes");
		miss6.setStatut(Statut.EN_ATTENTE_VALIDATION);
		miss6.setTransport(Transport.Covoiturage);
		miss6.setCollegue(col2);
		this.missionRepo.save(miss6);
	}

}

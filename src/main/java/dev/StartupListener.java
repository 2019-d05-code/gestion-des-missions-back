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
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Statut;
import dev.domain.Transport;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application.
 * Insertion de jeux de données.
 */
@Component
public class StartupListener {

    private String appVersion;
    private VersionRepo versionRepo;
    private PasswordEncoder passwordEncoder;
    private CollegueRepo collegueRepo;
    private MissionRepo missionRepo;

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, MissionRepo missionRepo) {
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
        col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR), new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col1);

        Collegue col2 = new Collegue();
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col2);
        
        // creation de trois missions

        Mission miss1 = new Mission();
        miss1.setDateDebut(LocalDate.parse("2015-05-15"));
        miss1.setDateFin(LocalDate.parse("2015-09-25"));
        miss1.setVilleDepart("Pau");
        miss1.setVilleArrivee("Bordeau");
        miss1.setStatut(Statut.INITIALE);
        miss1.setTransport(Transport.VoitureDeService);
        miss1.setCollegue(col2);
        this.missionRepo.save(miss1);
        
        Mission miss2 = new Mission();
        miss2.setDateDebut(LocalDate.parse("2015-12-01"));
        miss2.setDateFin(LocalDate.parse("2016-02-28"));
        miss2.setVilleDepart("Nice");
        miss2.setVilleArrivee("Brest");
        miss2.setStatut(Statut.INITIALE);
        miss2.setTransport(Transport.Train);
        miss2.setCollegue(col2);
        this.missionRepo.save(miss2);
        
        Mission miss3 = new Mission();
        miss3.setDateDebut(LocalDate.parse("2014-04-20"));
        miss3.setDateFin(LocalDate.parse("2016-07-10"));
        miss3.setVilleDepart("Paris");
        miss3.setVilleArrivee("Montpellier");
        miss3.setStatut(Statut.INITIALE);
        miss3.setTransport(Transport.Avion);
        miss3.setCollegue(col1);
        this.missionRepo.save(miss3);

        
        
    }

}

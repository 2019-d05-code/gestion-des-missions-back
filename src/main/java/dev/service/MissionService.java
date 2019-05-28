package dev.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Exception.MissionInvalidException;
import dev.Exception.MissionNonTrouveeException;
import dev.Exception.ModificationInvalideException;
import dev.Utils.DtoUtils;
import dev.domain.Collegue;
import dev.domain.Mission;
import dev.domain.Statut;
import dev.domain.Transport;
import dev.domainDto.MissionDto;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;

@Service
public class MissionService {

	private static final Logger LOG = LoggerFactory.getLogger(MissionService.class);

	@Autowired
	private MissionRepo missionRepo;

	@Autowired
	private CollegueRepo collegueRepo;

	public void setMissionRepository(MissionRepo missionRepo) {
		this.missionRepo = missionRepo;
	}


	public Boolean ajouterMission(Mission missionAjouter) {
		// Envoi d'une exception en cas de non-respect des règles métier
		if (regleMetierDateDebut(missionAjouter) && regleMetierAvion(missionAjouter)
				&& regleMetierDateFin(missionAjouter) && regleMetierMissionDisponible(missionAjouter)) {
			// Vérification de la présence en base de données avant l'insertion
			missionRepo.save(missionAjouter);
			return true;
		} else {
			return false;
		}
	}

	public void modifierMission(Integer id, Mission modifications) {
		Mission missionAModifier = missionRepo.findById(id).orElseThrow(() -> new MissionNonTrouveeException ("Aucune mission ne correspond à cet ID."));

		if (regleMetierStatut(missionAModifier)) {
			if (regleMetierDateDebut(modifications)) {
				missionAModifier.setDateDebut(modifications.getDateDebut());
			} else {
				LOG.error(" Modification impossible. ");
			}
			if (regleMetierDateFin(modifications)) {
				missionAModifier.setDateFin(modifications.getDateFin());
			} else {
				LOG.error(" Modification impossible. ");
			}
			if (regleMetierAvion(modifications)) {
				missionAModifier.setTransport(modifications.getTransport());
			} else {
				LOG.error(" Modification impossible. ");
			}
			missionAModifier.setNature(modifications.getNature());
			missionAModifier.setVilleDepart(modifications.getVilleDepart());
			missionAModifier.setVilleArrivee(modifications.getVilleArrivee());
			missionAModifier.setStatut(Statut.INITIALE);
			// Envoi d'une exception en cas de non-respect des règles métier
			if (ajouterMission(missionAModifier)) {
				LOG.info(" La mission a bien été modifiée. ");
			} else {
				LOG.error(" La mission n'a pas été modifiée. ");
			}
		} else {
			LOG.error(" Modification impossible. ");
		}
	}

	public List<MissionDto> recupererToutesLesMissions() {
		List<Mission> missionList = this.missionRepo.findAll();
		missionList.forEach(m -> System.out.println (m.getId() + " " + m.getDateDebut() + " " + m.getDateFin() ));
		return missionList.stream().map(DtoUtils::toMissionDtoAvecId).collect(Collectors.toList());
	}

	public List<MissionDto> recupererMissionParCollegue(String email) {
		Collegue collegue = collegueRepo.findByEmail(email).orElseThrow(RuntimeException::new);
		List<Mission> missionList = this.missionRepo.findByCollegue(collegue);
		return missionList.stream().map(DtoUtils::toMissionDtoAvecId).collect(Collectors.toList());
	}

	public MissionDto trouverMissionDepuisId(Integer id) {
		Optional<Mission> missionTrouve = missionRepo.findById(id);
		if (missionTrouve.isPresent()) {
			return DtoUtils.toMissionDto(missionTrouve.get());
		} else {
			LOG.error("Aucune mission trouvée avec cet ID");
			return null;
		}
	}

	public Boolean missionExistante(Mission mission) {
		List<MissionDto> missionsEnBaseDeDonnees = recupererToutesLesMissions();

		List<Mission> missionBDD = missionsEnBaseDeDonnees.stream()
				.map(missionUnique -> DtoUtils.toMission(missionUnique)).collect(Collectors.toList());

		for (Mission m : missionBDD) {
			System.out.println(m.getDateDebut() + " " + m.getDateFin() + " " + m.getId());
			if (m.equals(mission)) {
				return true;
			}
		}
		return false;
	}

	public void missionSupprimer(Integer id) {
		this.missionRepo.deleteById(id);

	}

	// Règles métier :
	/** Une mission ne peut pas débuter le jour même, ni dans le passé */
	private Boolean regleMetierDateDebut(Mission mission) {
		LocalDate dateDebut = LocalDate.now().plusDays(1);
		System.out.println(dateDebut);
		System.out.println(mission.getDateDebut());
		if (mission.getDateDebut().isBefore(dateDebut)) {
			throw new MissionInvalidException(" La mission ne peut pas démarrer le jour même ou avant. ");
		} else {
			return true;
		}
	}

	/**
	 * Si le type de transport est l'avion, une anticipation de 7 jours est
	 * exigée
	 */
	private Boolean regleMetierAvion(Mission mission) {
		LocalDate dateAvion = LocalDate.now().plusDays(7);
		if (mission.getTransport().equals(Transport.Avion)) {
			if (mission.getDateDebut().isBefore(dateAvion)) {
				throw new MissionInvalidException(" Il faut une anticipation de 7 jours pour prendre l'avion. ");
			}
		}
		return true;
	}

	/** La date de fin est supérieure ou égale à la date de début */
	private Boolean regleMetierDateFin(Mission mission) {
		LocalDate dateToCheck = mission.getDateDebut();
		if (mission.getDateFin().isBefore(dateToCheck)) {
			throw new MissionInvalidException(" La date de Fin n'est pas correcte. ");
		} else {
			return true;
		}
	}

	/**
	 * 1. Il est interdit de créer une mission qui chevauche une autre mission
	 * ou un congé (absence) 2. Il est interdit de créer une mission qui
	 * commence ou finit un jour non travaillé
	 */
	private Boolean regleMetierMissionDisponible(Mission mission) {
		List<MissionDto> missionList = this.recupererToutesLesMissions().stream()
				.filter(m -> m.getStatut().equals(Statut.VALIDEE)).collect(Collectors.toList());

		// iterator loop
		Iterator<MissionDto> iterator = missionList.iterator();
		while (iterator.hasNext()) {
			MissionDto missionTemp = iterator.next();
			if (mission.getDateDebut().isAfter(missionTemp.getDateDebut())
					&& mission.getDateDebut().isBefore(missionTemp.getDateFin())) {
				throw new MissionInvalidException(" La date de Fin n'est pas correcte. ");
			} else {
			}
		}
		return true;
	}

	/**
	 * Seules les missions au statut INITIALE ou REJETEE peuvent être modifiées
	 */
	private Boolean regleMetierStatut(Mission mission) {
		if (mission.getStatut() == Statut.INITIALE || mission.getStatut() == Statut.REJETEE) {
			return true;
		} else {
			throw new ModificationInvalideException(
					" Les missions en attentes ou validées ne peuvent plus être modifiées. ");
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public List<MissionDto> recupererMissionParCollegue(int id) {
		Collegue collegue = collegueRepo.findById(id).orElseThrow(RuntimeException::new);
		List<Mission> missionList = this.missionRepo.findByCollegue(collegue);
		return missionList.stream().map(DtoUtils::toMissionDtoAvecId).collect(Collectors.toList());
	}
}
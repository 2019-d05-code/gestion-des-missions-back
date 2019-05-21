package dev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Exception.MissionInvalidException;
import dev.Utils.DtoUtils;
import dev.domain.Mission;
import dev.domainDto.MissionDto;
import dev.repository.MissionRepo;

@Service
public class MissonService {

	@Autowired
	private MissionRepo missionRepo;

	public MissionDto ajouterMission(MissionDto missionAjouter) {
		// une mission ne peut pas débuter le jour même, ni dans le passé
		if (missionAjouter.getDateDebut().isBefore(missionAjouter.getDateDebut()))
			throw new MissionInvalidException("Illegal argument Date: la date exigee en passé ");

		// si le type de transport est l'avion, une anticipation de 7 jours est
		// exigée
		if (missionAjouter.getTransport().toString().equalsIgnoreCase("Avion")) {
			missionAjouter.setDateDebut(missionAjouter.getDateDebut().plusDays(7));
		}

		// la date de fin est supérieure ou égale à la date de début
		LocalDate dateToCheck = missionAjouter.getDateDebut().minusDays(1);
		if (missionAjouter.getDateFin().isBefore(dateToCheck))
			throw new MissionInvalidException("Illegal argument Date: la date de Fin est pas correct ");

		// il est interdit de créer une mission qui chevauche une autre mission
		// ou un congé (absence)
		// il est interdit de créer une mission qui commence ou finit un jour
		// non travaillé
		List<Mission> missionList = this.findAllMission().stream()
				.filter(mission -> mission.getStatut().toString().equalsIgnoreCase("VALIDE"))
				.collect(Collectors.toList());

		// iterator loop
		Iterator<Mission> iterator = missionList.iterator();
		while (iterator.hasNext()) {
			if (missionAjouter.getDateDebut().isAfter(iterator.next().getDateDebut())
					|| missionAjouter.getDateDebut().isBefore(iterator.next().getDateFin())) {

				throw new MissionInvalidException("Illegal argument Date: la date de Fin est pas correct ");
			}

		}
		missionRepo.save(DtoUtils.toMission(missionAjouter));
		return missionAjouter;
	}

	public List<Mission> findAllMission() {
		List<Mission> missionList = new ArrayList<>();
		missionList = this.missionRepo.findAll();
		return missionList;
	}

}

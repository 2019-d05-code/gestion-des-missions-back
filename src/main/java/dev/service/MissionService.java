package dev.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Exception.MissionInvalidException;
import dev.Utils.DtoUtils;
import dev.domain.Mission;
import dev.domain.Statut;
import dev.domain.Transport;
import dev.domainDto.MissionDto;
import dev.repository.MissionRepo;

@Service
public class MissionService {

	@Autowired
	private MissionRepo missionRepo;

	public void setMissionRepository(MissionRepo missionRepo) {
		this.missionRepo = missionRepo;
	}

	public MissionDto ajouterMission(MissionDto missionAjouter) {
		// une mission ne peut pas débuter le jour même, ni dans le passé

		LocalDate dateDebut = LocalDate.now().plusDays(1);

		if (missionAjouter.getTransport().equals(Transport.Avion)) {
			dateDebut = LocalDate.now().plusDays(7);
		}

		if (missionAjouter.getDateDebut().isBefore(dateDebut))
			throw new MissionInvalidException("Illegal argument Date: la date exigée est passée ");

		// si le type de transport est l'avion, une anticipation de 7 jours est
		// exigée

		// la date de fin est supérieure ou égale à la date de début
		LocalDate dateToCheck = LocalDate.now();
		if (missionAjouter.getDateFin().isBefore(dateToCheck))
			throw new MissionInvalidException("Illegal argument Date: la date de Fin est pas correcte ");

		// il est interdit de créer une mission qui chevauche une autre mission
		// ou un congé (absence)
		// il est interdit de créer une mission qui commence ou finit un jour
		// non travaillé
		List<MissionDto> missionList = this.findAllMission().stream()
				.filter(mission -> mission.getStatut().equals(Statut.VALIDEE)).collect(Collectors.toList());

		// iterator loop
		Iterator<MissionDto> iterator = missionList.iterator();
		while (iterator.hasNext()) {
			if (missionAjouter.getDateDebut().isAfter(iterator.next().getDateDebut())
					&& missionAjouter.getDateDebut().isBefore(iterator.next().getDateFin())) {

				throw new MissionInvalidException("Illegal argument Date: la date de Fin est pas correcte ");
			}

		}
		missionRepo.save(DtoUtils.toMission(missionAjouter));
		return missionAjouter;
	}

	public List<MissionDto> findAllMission() {
		List<Mission> missionList = this.missionRepo.findAll();
		return missionList.stream().map(DtoUtils::toMissionDto).collect(Collectors.toList());

	}

}

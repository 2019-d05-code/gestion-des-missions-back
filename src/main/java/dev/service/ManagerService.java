package dev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.Utils.DtoUtils;
import dev.domain.ManagerMission;
import dev.domain.Mission;
import dev.domain.Statut;
import dev.domainDto.MissionDto;
import dev.repository.MissionRepo;

@Service
public class ManagerService {
	@Autowired
	private MissionRepo missionRepo;

	public void setMissionRepository(MissionRepo missionRepo) {
		this.missionRepo = missionRepo;
	}

	public List<MissionDto> findAllMission() {
		List<Mission> missionList = this.missionRepo.findAll();
		return missionList.stream().filter(mission -> mission.getStatut().equals(Statut.EN_ATTENTE_VALIDATION))
				.map(DtoUtils::toMissionDtoAvecStatut).collect(Collectors.toList());

	}

	@Transactional
	public MissionDto modifierStatut(ManagerMission mission) {
		Optional<Mission> missionOptional = missionRepo.findById(mission.getId());
		Mission miss = missionOptional.get();
		if (mission.getStatut().equals(Statut.VALIDEE)) {
			miss.setStatut(Statut.VALIDEE);
		} else {
			miss.setStatut(Statut.REJETEE);
		}

		// collegueModifieEmail.setEmail(email);

		return DtoUtils.toMissionDtoAvecStatut(miss);

	}

}

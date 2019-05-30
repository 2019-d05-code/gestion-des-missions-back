package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Utils.DtoUtils;
import dev.domain.Statut;
import dev.domainDto.MissionDto;
import dev.repository.PrimeRepo;

@Service
public class PrimeService {

	@Autowired
	private PrimeRepo primeRepo;

	public void setPrimeRepository(PrimeRepo primeRepo) {
		this.primeRepo = primeRepo;
	}

	public List<MissionDto> missionAvecPrimeAsc() {

		return this.primeRepo.sortAllMissionAsc().stream().filter(mission -> mission.getStatut().equals(Statut.VALIDEE))
				.map(DtoUtils::toMissionDtoAvecPrime).collect(Collectors.toList());
	}

	public List<MissionDto> missionAvecPrimeDesc() {

		return this.primeRepo.sortAllMissionDesc().stream()
				.filter(mission -> mission.getStatut().equals(Statut.VALIDEE)).map(DtoUtils::toMissionDtoAvecPrime)
				.collect(Collectors.toList());
	}
}

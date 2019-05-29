package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Utils.DtoUtils;
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

		return this.primeRepo.sortAllMissionAsc().stream().map(DtoUtils::toMissionDtoAvecPrime)
				.collect(Collectors.toList());
	}

	public List<MissionDto> missionAvecPrimeDesc() {

		return this.primeRepo.sortAllMissionDesc().stream().map(DtoUtils::toMissionDtoAvecPrime)
				.collect(Collectors.toList());
	}
}

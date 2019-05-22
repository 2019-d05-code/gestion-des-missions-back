package dev.Utils;

import dev.domain.Mission;
import dev.domainDto.MissionDto;

public interface DtoUtils {

	public static MissionDto toMissionDto(Mission miss) {
		return new MissionDto(miss.getDateDebut(), miss.getDateFin(), miss.getVilleDepart(), miss.getVilleArrivee(),
				miss.getTransport());
	}

	public static Mission toMission(MissionDto missDto) {
		return new Mission(missDto.getDateDebut(), missDto.getDateFin(), missDto.getVilleDepart(),
				missDto.getVilleArrivee(), missDto.getTransport());
	}

	public static MissionDto toMissionDtoAvecStatut(Mission miss) {
		return new MissionDto(miss.getDateDebut(), miss.getDateFin(), miss.getVilleDepart(), miss.getVilleArrivee(),
				miss.getTransport(), miss.getStatut());
	}

	public static Mission toMissionAvecDto(MissionDto missDto) {
		return new Mission(missDto.getDateDebut(), missDto.getDateFin(), missDto.getVilleDepart(),
				missDto.getVilleArrivee(), missDto.getTransport(), missDto.getStatut());
	}
}

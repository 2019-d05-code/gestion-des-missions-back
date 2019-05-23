package dev.Utils;

import dev.domain.Mission;
import dev.domainDto.MissionDto;

public interface DtoUtils {

	public static MissionDto toMissionDto(Mission miss) {
		return new MissionDto(miss.getDateDebut(), miss.getDateFin(), miss.getNature(), miss.getVilleDepart(),
				miss.getVilleArrivee(), miss.getTransport());
	}

	public static Mission toMission(MissionDto missDto) {
		return new Mission(missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(), missDto.getVilleDepart(),
				missDto.getVilleArrivee(), missDto.getTransport());
	}

	public static MissionDto toMissionDtoAvecStatut(Mission miss) {
		return new MissionDto(miss.getId(), miss.getDateDebut(), miss.getDateFin(), miss.getNature(),
				miss.getVilleDepart(), miss.getVilleArrivee(), miss.getTransport(), miss.getStatut());
	}

	public static Mission toMissionAvecDto(MissionDto missDto) {
		return new Mission(missDto.getId(), missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(),
				missDto.getVilleDepart(), missDto.getVilleArrivee(), missDto.getTransport(), missDto.getStatut());
	}

	public static MissionDto toMissionDtoAvecId(Mission miss) {
		return new MissionDto(miss.getId(), miss.getDateDebut(), miss.getDateFin(), miss.getNature(),
				miss.getVilleDepart(), miss.getVilleArrivee(), miss.getTransport(), miss.getStatut());
	}

	public static Mission toMissionAvecId(MissionDto missDto) {
		return new Mission(missDto.getId(), missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(),
				missDto.getVilleDepart(), missDto.getVilleArrivee(), missDto.getTransport(), missDto.getStatut());
	}
}

package dev.Utils;

import org.springframework.stereotype.Service;

import dev.domain.Collegue;
import dev.domain.Mission;
import dev.domainDto.MissionDto;
import dev.service.CollegueService;

@Service
public interface DtoUtils {

	public static MissionDto toMissionDto(Mission miss) {
		return new MissionDto(miss.getDateDebut(), miss.getDateFin(), miss.getNature(), miss.getVilleDepart(),
				miss.getVilleArrivee(), miss.getTransport(), miss.getCollegue().getEmail());
	}

	public static Mission toMission(MissionDto missDto) {

		return new Mission(missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(), missDto.getVilleDepart(),
				missDto.getVilleArrivee(), missDto.getTransport());
	}

	/** pour modification */
	public static Mission toMissionAvecPrime(MissionDto missDto) {
		return new Mission(missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(), missDto.getVilleDepart(),
				missDto.getVilleArrivee(), missDto.getTransport(), missDto.getPrime());
	}

	public static MissionDto toMissionDtoAvecStatut(Mission miss) {
		return new MissionDto(miss.getId(), miss.getDateDebut(), miss.getDateFin(), miss.getNature(),
				miss.getVilleDepart(), miss.getVilleArrivee(), miss.getTransport(), miss.getStatut(), miss.getPrime());
	}

	public static Mission toMissionAvecDto(MissionDto missDto) {
		return new Mission(missDto.getId(), missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(),
				missDto.getVilleDepart(), missDto.getVilleArrivee(), missDto.getTransport(), missDto.getStatut());
	}

	public static MissionDto toMissionDtoAvecId(Mission miss) {
		return new MissionDto(miss.getId(), miss.getDateDebut(), miss.getDateFin(), miss.getNature(),
				miss.getVilleDepart(), miss.getVilleArrivee(), miss.getTransport(), miss.getStatut(), miss.getPrime());
	}

	public static Mission toMissionAvecId(MissionDto missDto) {
		return new Mission(missDto.getId(), missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(),
				missDto.getVilleDepart(), missDto.getVilleArrivee(), missDto.getTransport(), missDto.getStatut());
	}

	public static Mission toMissionAvecMail(MissionDto missDto, CollegueService col) {
		Collegue collegue = col.findCollegueByEmail(missDto.getEmailColl());
		return new Mission(missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(), missDto.getVilleDepart(),
				missDto.getVilleArrivee(), missDto.getTransport(), missDto.getPrime(), collegue);
	}

	public static Mission toMissionAvecPrime(MissionDto missDto, CollegueService col) {
		Collegue collegue = col.findCollegueByEmail(missDto.getEmailColl());
		return new Mission(missDto.getDateDebut(), missDto.getDateFin(), missDto.getNature(), missDto.getVilleDepart(),
				missDto.getVilleArrivee(), missDto.getTransport(), collegue);
	}

	public static MissionDto toMissionDtoAvecPrime(Mission miss) {
		return new MissionDto(miss.getDateDebut(), miss.getDateFin(), miss.getNature(), miss.getVilleDepart(),
				miss.getVilleArrivee(), miss.getTransport(), miss.getPrime());
	}

}

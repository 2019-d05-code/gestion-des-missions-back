package dev.Utils;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import dev.domain.Collegue;
import dev.domain.LigneDeFrais;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domainDto.FraisDto;
import dev.domainDto.MissionDto;
import dev.domainDto.NatureDTO;
import dev.service.CollegueService;

@Service
public interface DtoUtils {

	// -- Mission --

	public static MissionDto toMissionDto(Mission miss) {
		return new MissionDto(miss.getDateDebut(), miss.getDateFin(), miss.getNature(), miss.getVilleDepart(),
				miss.getVilleArrivee(), miss.getTransport(), miss.getCollegue().getEmail(), miss.getPrime());
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

	public static MissionDto toMissionDtoAvecEmail(Mission miss) {
		return new MissionDto(miss.getId(), miss.getDateDebut(), miss.getDateFin(), miss.getNature(),
				miss.getVilleDepart(), miss.getVilleArrivee(), miss.getTransport(), miss.getStatut(),
				miss.getCollegue().getEmail());
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

	// -- Ligne de Frais --
	public static LigneDeFrais dtoVersFrais(FraisDto dto) {
		return new LigneDeFrais(dto.getDate(), dto.getNature(), dto.getMontant());
	}

	public static FraisDto fraisVersDto(LigneDeFrais frais) {
		return new FraisDto(frais.getId(), frais.getDate(), frais.getNature(), frais.getMontant(),
				frais.getMission().getId());
	}

	public static MissionDto toMissionDtoAvecPrime(Mission miss) {
		return new MissionDto(miss.getDateDebut(), miss.getDateFin(), miss.getNature(), miss.getVilleDepart(),
				miss.getVilleArrivee(), miss.getTransport(), miss.getPrime());
	}

	public static NatureDTO toNAtureDto(Nature nature){
	return new NatureDTO(nature.getId(), nature.getNomNature(), nature.isFacturee(), nature.isPrime(),nature.getTauxJournalierMoyen(),
			nature.getPourcentPrime(), nature.getPlafondQuotidien(), nature.isDepassementFrais(), nature.getDateDebut(),nature.getDateFin());
	}
}

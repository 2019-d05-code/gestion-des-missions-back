package dev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Exception.NatureInvalideException;
import dev.Utils.DtoUtils;
import dev.domain.Nature;
import dev.domainDto.NatureDTO;
import dev.repository.NatureRepository;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Service
public class NatureService {
	private static final Logger LOG = LoggerFactory.getLogger(MissionService.class);
	@Autowired
	NatureRepository natureRepository;

	public Nature ajoutNature(NatureDTO nature) {

		if (natureRepository.findAll().stream()
				.anyMatch(nat -> nature.getNomNature().equalsIgnoreCase(nat.getNomNature()))) {
			throw new NatureInvalideException("Une nature avec ce nom existe déjà !");
		}

		// Si la nature est facturée, elle doit avoir un TJM moyen en euros
		// (négatif et 0 refusé)
		if (nature.isFacturee()) {
			if (nature.getPlafondQuotidien() < 0) {
				throw new NatureInvalideException("Le plafond quotidien doit être positif !");
			}

			if (nature.getTauxJournalierMoyen() <= 0) {
				throw new NatureInvalideException("Le taux journalier moyen doit exister pour une nature facturée !");
			}
		} else {
			nature.setPlafondQuotidien(0);
			nature.setTauxJournalierMoyen(0);
		}

		// Si la nature octroie une prime, elle doit avoir un % de prime
		// (négatif et 0 refusé)
		if (nature.isPrime() && nature.getPourcentPrime() <= 0) {
			throw new NatureInvalideException(
					"Le taux de la prime doit être renseigner pour une nature incluant une prime !");
		} else if (!nature.isPrime()) {
			nature.setPourcentPrime(0);
		}

		if (nature.getDateDebut() == null) {
			throw new NatureInvalideException("La date de début est obligatoire !");
		}

		if (nature.getDateFin() != null && nature.getDateFin().isBefore(nature.getDateDebut())) {
			throw new NatureInvalideException("La date de fin ne doit pas être précéder la date de début !");
		}

		Nature nat = new Nature(nature.getNomNature(), nature.isFacturee(), nature.isPrime(),
				nature.getTauxJournalierMoyen(), nature.getPourcentPrime(), nature.getPlafondQuotidien(),
				nature.isDepassementFrais(), nature.getDateDebut(), nature.getDateFin());
		natureRepository.save(nat);
		return nat;
	}

	public Nature modificationNature(int id, NatureDTO nature) {

		if (natureRepository.findAll().stream()
				.anyMatch(nat -> nature.getNomNature().equalsIgnoreCase(nat.getNomNature()))) {
			throw new NatureInvalideException("Une nature avec ce nom existe déjà !");
		}

		// Si la nature est facturée, elle doit avoir un TJM moyen en euros
		// (négatif et 0 refusé)
		if (nature.isFacturee()) {
			if (nature.getPlafondQuotidien() < 0) {
				throw new NatureInvalideException("Le plafond quotidien doit être positif !");
			}

			if (nature.getTauxJournalierMoyen() <= 0) {
				throw new NatureInvalideException("Le taux journalier moyen doit exister pour une nature facturée !");
			}
		}

		// Si la nature octroie une prime, elle doit avoir un % de prime
		// (négatif et 0 refusé)
		if (nature.isPrime() && nature.getPourcentPrime() <= 0) {
			throw new NatureInvalideException(
					"Le taux de la prime doit être renseigner pour une nature incluant une prime !");
		}

		if (nature.getDateDebut() == null) {
			throw new NatureInvalideException("La date de début est obligatoire !");
		}

		if (nature.getDateFin() != null && nature.getDateFin().isBefore(nature.getDateDebut())) {
			throw new NatureInvalideException("La date de fin ne doit pas être précéder la date de début !");
		}

		Nature nat = natureRepository.findById(id)
				.orElseThrow(() -> new NatureInvalideException("La nature n'a pas été trouvée !"));
		nat.setNomNature(nature.getNomNature());
		nat.setFacturee(nature.isFacturee());
		nat.setPrime(nature.isPrime());

		if (nat.isFacturee()) {
			nat.setTauxJournalierMoyen(nature.getTauxJournalierMoyen());
			nat.setPlafondQuotidien(nature.getPlafondQuotidien());
		}

		if (nat.isPrime()) {
			nat.setPourcentPrime(nature.getPourcentPrime());
		}

		nat.setDepassementFrais(nature.isDepassementFrais());

		nat.setDateDebut(nature.getDateDebut());

		if (nature.getDateFin() != null) {
			nat.setDateFin(nature.getDateFin());
		}

		natureRepository.save(nat);
		return nat;
	}

	public void suppressionNature(int id) {

		if (!natureRepository.existsById(id)) {
			throw new NatureInvalideException("La nature que vous voulez supprimée n'existe pas !");
		}

		natureRepository.deleteById(id);
	}

	public List<NatureDTO> afficherToutesNatures() {
		return natureRepository.findAll().stream()
				.map(nature -> new NatureDTO(nature.getId(), nature.getNomNature(), nature.isFacturee(),
						nature.isPrime(), nature.getTauxJournalierMoyen(), nature.getPourcentPrime(),
						nature.getPlafondQuotidien(), nature.isDepassementFrais(), nature.getDateDebut(),
						nature.getDateFin()))
				.collect(Collectors.toList());
	}

	public NatureRepository getNatureRepository() {
		return natureRepository;
	}

	public void setNatureRepository(NatureRepository natureRepository) {
		this.natureRepository = natureRepository;
	}

	public NatureDTO trouverNatureDepuisId(Integer id) {
		Optional<Nature> natureTrouve = natureRepository.findById(id);
		if (natureTrouve.isPresent()) {
			return DtoUtils.toNAtureDto(natureTrouve.get());
		} else {
			LOG.error("Aucune mission trouvée avec cet ID");
			return null;
		}
	}
}

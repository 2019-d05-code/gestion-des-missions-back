package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Exception.NatureInvalideException;
import dev.domain.Nature;
import dev.domainDto.NatureDTO;
import dev.repository.NatureRepository;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Service
public class NatureService {

	@Autowired
	NatureRepository natureRepository;

	public Nature ajoutNature (NatureDTO nature) {

		if (natureRepository.findAll ().stream().anyMatch(nat -> nature.getNomNature().equalsIgnoreCase(nat.getNomNature()))) {
			throw new NatureInvalideException ("Une nature avec ce nom existe déjà !");
		}

		// Si la nature est facturée, elle doit avoir un TJM moyen en euros (négatif et 0 refusé)
		if (nature.isFacturee()) {
			if (nature.getPlafondQuotidien() < 0) {
				throw new NatureInvalideException ("Le plafond quotidien doit être positif !");
			}

			if (nature.getTauxJournalierMoyen() <= 0) {
				throw new NatureInvalideException("Le taux journalier moyen doit exister pour une nature facturée !");
			}
		}

		// Si la nature octroie une prime, elle doit avoir un % de prime (négatif et 0 refusé)
		if (nature.isPrime() && nature.getPourcentPrime() <= 0) {
			throw new NatureInvalideException("Le taux de la prime doit être renseigner pour une nature incluant une prime !");
		}

		if (nature.getDateFin () != null && nature.getDateDebut().isBefore(nature.getDateFin())) {
			throw new NatureInvalideException("La date de fin ne doit pas être précéder la date de début !");
		}

		Nature nat = new Nature (nature.getNomNature (), nature.isFacturee(), nature.isPrime (), nature.getTauxJournalierMoyen(), nature.getPourcentPrime(), nature.getPlafondQuotidien(), nature.isDepassementFrais(), nature.getDateDebut(), nature.getDateFin());
		natureRepository.save(nat);
		return nat;
	}

	public Nature modificationNature (NatureDTO nature) {

		if (natureRepository.findAll ().stream().anyMatch(nat -> nature.getNomNature().equalsIgnoreCase(nat.getNomNature()))) {
			throw new NatureInvalideException ("Une nature avec ce nom existe déjà !");
		}

		// Si la nature est facturée, elle doit avoir un TJM moyen en euros (négatif et 0 refusé)
		if (nature.isFacturee()) {
			if (nature.getPlafondQuotidien() < 0) {
				throw new NatureInvalideException ("Le plafond quotidien doit être positif !");
			}

			if (nature.getTauxJournalierMoyen() <= 0) {
				throw new NatureInvalideException("Le taux journalier moyen doit exister pour une nature facturée !");
			}
		}

		// Si la nature octroie une prime, elle doit avoir un % de prime (négatif et 0 refusé)
		if (nature.isPrime() && nature.getPourcentPrime() <= 0) {
			throw new NatureInvalideException("Le taux de la prime doit être renseigner pour une nature incluant une prime !");
		}

		if (nature.getDateFin () != null && nature.getDateDebut().isBefore(nature.getDateFin())) {
			throw new NatureInvalideException("La date de fin ne doit pas être précéder la date de début !");
		}

		Nature nat = natureRepository.findById(nature.getId ()).orElseThrow(() -> new NatureInvalideException ("La nature n'a pas été trouvée !"));
		nat.setNomNature(nature.getNomNature ());
		nat.setFacturee(nature.isFacturee());
		nat.setPrime(nature.isPrime ());

		if (nat.isFacturee ()) {
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

	public void suppressionNature (int nature) {

		if (!natureRepository.existsById(nature)) {
			throw new NatureInvalideException ("La nature que vous voulez supprimée n'existe pas !");
		}

		natureRepository.deleteById(nature);
	}

	public List <NatureDTO> afficherToutesNatures () {
		return natureRepository.findAll().stream().map(nature -> new NatureDTO (nature.getId (), nature.getNomNature (), nature.isFacturee(), nature.isPrime (), nature.getTauxJournalierMoyen(), nature.getPourcentPrime(), nature.getPlafondQuotidien(), nature.isDepassementFrais(), nature.getDateDebut(), nature.getDateFin())).collect(Collectors.toList());
	}

	public NatureRepository getNatureRepository() {
		return natureRepository;
	}

	public void setNatureRepository(NatureRepository natureRepository) {
		this.natureRepository = natureRepository;
	}
}

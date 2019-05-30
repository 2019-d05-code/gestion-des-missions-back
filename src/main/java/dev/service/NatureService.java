/**
 *
 */
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

	if (nature.getPlafondQuotidien() < 0) {
	    throw new NatureInvalideException ("Le plafond quotidien doit être positif !");
	}

	// Si la nature est facturée, elle doit avoir un TJM moyen en euros (négatif et 0 refusé)
	if (nature.isFacturee()) {
	    if (nature.getTauxJournalierMoyen() <= 0) {
		throw new NatureInvalideException("Le taux journalier moyen doit exister pour une nature facturée !");
	    }

	    if (nature.getPlafondQuotidien () <= 0) {
		throw new NatureInvalideException("Pas de plafond quotidien pour une nature non facturée !");
	    }
	}

	// Si la nature octroie une prime, elle doit avoir un % de prime (négatif et 0 refusé)
	if (nature.isPrime()) {
	    if (nature.getPourcentPrime() <= 0) {
		throw new NatureInvalideException("Le taux de la prime doit être renseigner pour une nature incluant une prime !");
	    }
	}

	Nature nat = new Nature (nature.getNomNature (), nature.isFacturee(), nature.isPrime (), nature.getTauxJournalierMoyen(), nature.getPourcentPrime(), nature.getPlafondQuotidien(), nature.isDepassementFrais(), nature.getDateDebut(), nature.getDateFin());
	natureRepository.save(nat);
	return nat;
    }

    public Nature modificationNature (NatureDTO nature) {

	if (nature.getPlafondQuotidien() < 0) {
	    throw new NatureInvalideException ("Le plafond quotidien doit être positif !");
	}

	// Si la nature est facturée, elle doit avoir un TJM moyen en euros (négatif et 0 refusé)
	if (nature.isFacturee()) {
	    if (nature.getTauxJournalierMoyen() <= 0) {
		throw new NatureInvalideException("Le taux journalier moyen doit exister pour une nature facturée !");
	    }
	}

	// Si la nature octroie une prime, elle doit avoir un % de prime (négatif et 0 refusé)
	if (nature.isPrime()) {
	    if (nature.getPourcentPrime() <= 0) {
		throw new NatureInvalideException("Le taux de la prime doit être renseigner pour une nature incluant une prime !");
	    }
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
	nat.setDateFin(nature.getDateFin());

	natureRepository.save(nat);
	return nat;
    }

    public void suppressionNature (NatureDTO nature) {

	if (natureRepository.existsById(nature.getId ())) {
	    throw new NatureInvalideException ("La nature que vous voulez supprimée n'existe pas !");
	}

	natureRepository.deleteById(nature.getId());
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

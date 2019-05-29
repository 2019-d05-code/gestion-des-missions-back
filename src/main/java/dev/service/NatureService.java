/**
 *
 */
package dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	void ajoutNature (NatureDTO nature) {
		Nature nat = new Nature (nature.getNomNature (), nature.isFacturee(), nature.isPrime (), nature.getTauxJournalierMoyen(), nature.getPourcentPrime(), nature.getPlafondQuotidien(),nature.isDepassementFrais());
		natureRepository.save(nat);
	}
}

/**
 *
 */
package dev.domainDto;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class NatureDTO {

	int id;
	String nomNature;
	boolean facturee;
	boolean prime;
	double tauxJournalierMoyen;
	int pourcentPrime;
	int plafondQuotidien;
	boolean depassementFrais;

	public NatureDTO () {}

	public NatureDTO(String nature, boolean facturee, boolean prime, double tauxJournalierMoyen, int pourcentPrime, int plafondFrais, boolean departementFrais) {
		super();
		this.nomNature = nature;
		this.facturee = facturee;
		this.prime = prime;
		this.tauxJournalierMoyen = tauxJournalierMoyen;
		this.pourcentPrime = pourcentPrime;
		this.plafondQuotidien = plafondFrais;
		this.depassementFrais = departementFrais;
	}

	public NatureDTO(int id, String nature, boolean facturee, boolean prime, double tauxJournalierMoyen, int pourcentPrime, int plafondFrais, boolean departementFrais) {
		super();
		this.id = id;
		this.nomNature = nature;
		this.facturee = facturee;
		this.prime = prime;
		this.tauxJournalierMoyen = tauxJournalierMoyen;
		this.pourcentPrime = pourcentPrime;
		this.plafondQuotidien = plafondFrais;
		this.depassementFrais = departementFrais;
	}

	/**
	 * Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the nature
	 */
	public String getNomNature() {
		return nomNature;
	}

	/**
	 * Setter
	 * @param nature the nature to set
	 */
	public void setNomNature(String nature) {
		this.nomNature = nature;
	}

	/**
	 * Getter
	 * @return the facturee
	 */
	public boolean isFacturee() {
		return facturee;
	}

	/**
	 * Setter
	 * @param facturee the facturee to set
	 */
	public void setFacturee(boolean facturee) {
		this.facturee = facturee;
	}

	/**
	 * Getter
	 * @return the prime
	 */
	public boolean isPrime() {
		return prime;
	}

	/**
	 * Setter
	 * @param prime the prime to set
	 */
	public void setPrime(boolean prime) {
		this.prime = prime;
	}

	/**
	 * Getter
	 * @return the tauxJournalierMoyen
	 */
	public double getTauxJournalierMoyen() {
		return tauxJournalierMoyen;
	}

	/**
	 * Setter
	 * @param tauxJournalierMoyen the tauxJournalierMoyen to set
	 */
	public void setTauxJournalierMoyen(double tauxJournalierMoyen) {
		this.tauxJournalierMoyen = tauxJournalierMoyen;
	}

	/**
	 * Getter
	 * @return the pourcentPrime
	 */
	public int getPourcentPrime() {
		return pourcentPrime;
	}

	/**
	 * Setter
	 * @param pourcentPrime the pourcentPrime to set
	 */
	public void setPourcentPrime(int pourcentPrime) {
		this.pourcentPrime = pourcentPrime;
	}

	/**
	 * Getter
	 * @return the plafondFrais
	 */
	public int getPlafondQuotidien() {
		return plafondQuotidien;
	}

	/**
	 * Setter
	 * @param plafondFrais the plafondFrais to set
	 */
	public void setPlafondQuotidien(int plafondFrais) {
		this.plafondQuotidien = plafondFrais;
	}

	/**
	 * Getter
	 * @return the departementFrais
	 */
	public boolean isDepassementFrais() {
		return depassementFrais;
	}

	/**
	 * Setter
	 * @param departementFrais the departementFrais to set
	 */
	public void setDepassementFrais(boolean departementFrais) {
		this.depassementFrais = departementFrais;
	}
}

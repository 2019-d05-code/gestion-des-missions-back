/**
 *
 */
package dev.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Entity
public class Nature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column
	String nomNature;

	@Column
	boolean facturee;

	@Column
	boolean prime;

	@Column
	double tauxJournalierMoyen;

	@Column
	int pourcentPrime;

	@Column
	int plafondQuotidien;

	@Column
	boolean depassementFrais;

	@Column
	LocalDate dateDebut;

	@Column
	LocalDate dateFin;

	public Nature () {}

	public Nature(int id, String nomNature, boolean facturee, boolean prime, double tauxJournalierMoyen,
			int pourcentPrime, int plafondQuotidien, boolean depassementFrais, LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.id = id;
		this.nomNature = nomNature;
		this.facturee = facturee;
		this.prime = prime;
		this.tauxJournalierMoyen = tauxJournalierMoyen;
		this.pourcentPrime = pourcentPrime;
		this.plafondQuotidien = plafondQuotidien;
		this.depassementFrais = depassementFrais;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Nature(int id, String nomNature, boolean facturee, boolean prime, double tauxJournalierMoyen,
			int pourcentPrime, int plafondQuotidien, boolean depassementFrais, LocalDate dateDebut) {
		super();
		this.id = id;
		this.nomNature = nomNature;
		this.facturee = facturee;
		this.prime = prime;
		this.tauxJournalierMoyen = tauxJournalierMoyen;
		this.pourcentPrime = pourcentPrime;
		this.plafondQuotidien = plafondQuotidien;
		this.depassementFrais = depassementFrais;
		this.dateDebut = dateDebut;
	}

	public Nature(String nomNature, boolean facturee, boolean prime, double tauxJournalierMoyen, int pourcentPrime,
			int plafondQuotidien, boolean depassementFrais, LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.nomNature = nomNature;
		this.facturee = facturee;
		this.prime = prime;
		this.tauxJournalierMoyen = tauxJournalierMoyen;
		this.pourcentPrime = pourcentPrime;
		this.plafondQuotidien = plafondQuotidien;
		this.depassementFrais = depassementFrais;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Nature(String nomNature, boolean facturee, boolean prime, double tauxJournalierMoyen,
			int pourcentPrime, int plafondQuotidien, boolean depassementFrais, LocalDate dateDebut) {
		super();
		this.nomNature = nomNature;
		this.facturee = facturee;
		this.prime = prime;
		this.tauxJournalierMoyen = tauxJournalierMoyen;
		this.pourcentPrime = pourcentPrime;
		this.plafondQuotidien = plafondQuotidien;
		this.depassementFrais = depassementFrais;
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
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

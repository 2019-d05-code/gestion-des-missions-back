package dev.domainDto;

import java.time.LocalDate;

import dev.domain.Statut;
import dev.domain.Transport;

public class MissionDto {

	private LocalDate dateDebut;

	private LocalDate dateFin;
	// private Nature nature;
	private String villeDepart;

	private String villeArrivee;

	private Transport transport;

	private Statut statut;

	// - constructeur -
	public MissionDto() {
	}

	public MissionDto(LocalDate dateDebut, LocalDate dateFin, String villeDepart, String villeArrivee,
			Transport transport) {

		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.setStatut(Statut.INITIALE);
	}

	public MissionDto(LocalDate dateDebut, LocalDate dateFin, String villeDepart, String villeArrivee,
			Transport transport, Statut statut) {

		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.statut = statut;
	}

	// - getter/setter
	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the villeDepart
	 */
	public String getVilleDepart() {
		return villeDepart;
	}

	/**
	 * @param villeDepart
	 *            the villeDepart to set
	 */
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	/**
	 * @return the villeArrivee
	 */
	public String getVilleArrivee() {
		return villeArrivee;
	}

	/**
	 * @param villeArrivee
	 *            the villeArrivee to set
	 */
	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	/**
	 * @return the transport
	 */
	public Transport getTransport() {
		return transport;
	}

	/**
	 * @param transport
	 *            the transport to set
	 */
	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	/**
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}

	/**
	 * @param statut
	 *            the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

}

package dev.domainDto;

import java.time.LocalDate;

import dev.domain.Nature;
import dev.domain.Statut;
import dev.domain.Transport;

public class MissionDto {

	private Integer id;

	private LocalDate dateDebut;

	private LocalDate dateFin;

	private Nature nature;

	private String villeDepart;

	private String villeArrivee;

	private Transport transport;

	private Statut statut;
	
	private String emailColl;

	// - constructeur -
	public MissionDto() {
	}

	public MissionDto(LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart, String villeArrivee,
			Transport transport, String email) {

		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.setStatut(Statut.INITIALE);
		this.emailColl = email;
	}

	/** touch pas cest pour modif */

	public MissionDto(Integer id, LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart,
			String villeArrivee, Transport transport, Statut statut) {

		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
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

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((transport == null) ? 0 : transport.hashCode());
		result = prime * result + ((villeArrivee == null) ? 0 : villeArrivee.hashCode());
		result = prime * result + ((villeDepart == null) ? 0 : villeDepart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MissionDto other = (MissionDto) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (statut != other.statut)
			return false;
		if (transport != other.transport)
			return false;
		if (villeArrivee == null) {
			if (other.villeArrivee != null)
				return false;
		} else if (!villeArrivee.equals(other.villeArrivee))
			return false;
		if (villeDepart == null) {
			if (other.villeDepart != null)
				return false;
		} else if (!villeDepart.equals(other.villeDepart))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmailColl() {
		return emailColl;
	}

	public void setEmailColl(String emailColl) {
		this.emailColl = emailColl;
	}
}

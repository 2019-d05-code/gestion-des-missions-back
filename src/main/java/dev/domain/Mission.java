package dev.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Mission {
	// - attribut -
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private LocalDate dateDebut;
	@Column
	private LocalDate dateFin;
	@Column
	@Enumerated(EnumType.STRING)
	private Nature nature;
	@Column
	private String villeDepart;
	@Column
	private String villeArrivee;
	@Column
	@Enumerated(EnumType.STRING)
	private Transport transport;
	@Column
	@Enumerated(EnumType.STRING)
	private Statut statut;
	@Column
	private double prime = 0;

	@ManyToOne
	@JoinColumn(name = "collegue_id")
	private Collegue collegue;
	
	@Column
	@OneToMany(mappedBy = "mission", cascade = CascadeType.PERSIST)
	private List<LigneDeFrais> notesFrais;

	// - constructeur -
	public Mission() {
	}

	public Mission(LocalDate debut, LocalDate fin, Nature nature, String depart, String arrivee, Transport transport) {
		this.dateDebut = debut;
		this.dateFin = fin;
		this.nature = nature;
		this.villeDepart = depart;
		this.villeArrivee = arrivee;
		this.transport = transport;
		this.setStatut(Statut.INITIALE);
		this.notesFrais = new ArrayList<>();
	}

	public Mission(LocalDate debut, LocalDate fin, Nature nature, String depart, String arrivee, Transport transport,
			Collegue coll) {
		this.dateDebut = debut;
		this.dateFin = fin;
		this.nature = nature;
		this.villeDepart = depart;
		this.villeArrivee = arrivee;
		this.transport = transport;
		this.setStatut(Statut.INITIALE);
		this.collegue = coll;
		this.notesFrais = new ArrayList<>();
	}

	/** touch pas cest pour primes */
	public Mission(LocalDate debut, LocalDate fin, Nature nature, String depart, String arrivee, Transport transport,
			double prime, Collegue coll) {
		this.dateDebut = debut;
		this.dateFin = fin;
		this.nature = nature;
		this.villeDepart = depart;
		this.villeArrivee = arrivee;
		this.transport = transport;
		this.prime = prime;
		this.setStatut(Statut.INITIALE);
		this.collegue = coll;
	}

	/** touch pas cest pour modif */

	public Mission(Integer id, LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart,
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

	/** touch pas cest pour modif avec prime */
	public Mission(LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart, String villeArrivee,
			Transport transport, double prime) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.prime = prime;
		this.setStatut(Statut.INITIALE);


		this.notesFrais = new ArrayList<>();

	}

	public Mission(int id, LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart,
			String villeArrivee, Transport transport, Collegue collegue) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.collegue = collegue;
		this.notesFrais = new ArrayList<>();
	}

	// - getter/setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}


	public double getPrime() {
		return prime;
	}

	public void setPrime(double prime) {
		this.prime = prime;
	}


	
	public List<LigneDeFrais> getNotesFrais() {
		return notesFrais;
	}

	public void setNotesFrais(List<LigneDeFrais> notesFrais) {
		this.notesFrais = notesFrais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
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
		Mission other = (Mission) obj;
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
		if (nature != other.nature)
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

}

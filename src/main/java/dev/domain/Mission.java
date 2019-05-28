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
	private Integer id;
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

	/** touch pas cest pour modif */
	public Mission(Integer id, LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart, String villeArrivee,
			Transport transport, Statut statut) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.statut = statut;
		this.nature = nature;
		this.notesFrais = new ArrayList<>();
	}

	public Mission(Integer id, LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart,
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
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	public List<LigneDeFrais> getNotesFrais() {
		return notesFrais;
	}

	public void setNotesFrais(List<LigneDeFrais> notesFrais) {
		this.notesFrais = notesFrais;
	}
}

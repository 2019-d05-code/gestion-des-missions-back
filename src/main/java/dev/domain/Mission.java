package dev.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mission
{
	// -  attribut - 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private LocalDate dateDebut;
	@Column
	private LocalDate dateFin;
	//private Nature nature;
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
	
	// - constructeur - 
	public Mission () {}
	
	public Mission(LocalDate debut,	LocalDate fin, /*Nature nature,*/ String depart, String arrivee, Transport transport, Collegue coll)
	{
		this.dateDebut = debut;
		this.dateFin = fin;
		this.villeDepart = depart;
		this.villeArrivee = arrivee;
		this.transport = transport;
		this.setStatut(Statut.INITIALE);
		this.collegue = coll;
	}

	// - getter/setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	

}

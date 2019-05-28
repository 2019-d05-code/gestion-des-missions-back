package dev.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LigneDeFrais
{
	// - attribut - 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private LocalDate date;
	@Enumerated(EnumType.STRING)
	@Column
	private NatureFrais nature;
	@Column
	private double montant;
	
	@ManyToOne
	@JoinColumn(name = "mission_id")
	private Mission mission;
	
	// - constructeur - 
	public LigneDeFrais() {}
	
	public LigneDeFrais(LocalDate date, NatureFrais nature, double montant, Mission miss)
	{
		this.date = date;
		this.nature = nature;
		this.montant = montant;
		this.mission = miss;
	}
	

	// - getter/setter - 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public NatureFrais getNature() {
		return nature;
	}

	public void setNature(NatureFrais nature) {
		this.nature = nature;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}


}

package dev.domainDto;

import java.time.LocalDate;

import dev.domain.NatureFrais;

public class FraisDto
{
	// - attribut - 

	private int id;
	private LocalDate date;
	private NatureFrais nature;
	private double montant;
	private int idMiss;

	// - constructeur - 
	public FraisDto() {}

	public FraisDto(LocalDate date, NatureFrais nature, double montant, int idMiss)
	{
		this.setId(-1);
		this.date = date;
		this.nature = nature;
		this.montant = montant;
		this.idMiss = idMiss;
	}
	public FraisDto(int id, LocalDate date, NatureFrais nature, double montant, int idMiss)
	{
		this.id = id;
		this.date = date;
		this.nature = nature;
		this.montant = montant;
		this.idMiss = idMiss;
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

	public int getIdMiss() {
		return idMiss;
	}

	public void setIdMiss(int idMiss) {
		this.idMiss = idMiss;
	}
}

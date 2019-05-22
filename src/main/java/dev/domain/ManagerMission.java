package dev.domain;

public class ManagerMission {

	private Integer id;

	private Statut statut;

	public ManagerMission() {
		super();
	}

	public ManagerMission(Integer id, Statut statut) {
		super();
		this.id = id;
		this.statut = statut;
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

	// getter & setter
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the valide
	 */

}

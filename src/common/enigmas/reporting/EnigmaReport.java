package common.enigmas.reporting;

import common.entities.GameObject;

import java.util.ArrayList;

/**
 * Le message retourné l'exécution d'une opération
 * ou d'une condition.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1 12/02/2020
 * @since 6.0 12/02/2020
 */
public class EnigmaReport {

	/**
	 * Message sur la réalisation de l'action
	 */
	private Report report;

	/**
	 * true si l'action s'est faite sinon false
	 */
	private boolean fulfilled;

	/**
	 * Liste d'entitées concernées par un changement
	 */
	private ArrayList<GameObject> entities;

	/**
	 * Le message retourné l'exécution d'une opération
	 * ou d'une condition.
	 *
	 * @param report Message sur la réalisation de l'action
	 * @param entities Entitées qui sont concernées par un changement
	 */
	public EnigmaReport(Report report, GameObject ... entities) {
		this.report = report;
		this.fulfilled = false;
		for (GameObject e : entities) this.add(e);
	}

	/**
	 * Le message retourné l'exécution d'une opération
	 * ou d'une condition.
	 *
	 * @param report    Message sur la réalisation de l'action
	 * @param fulfilled true si l'action c'est faite sinon false
	 * @param entities Entitées qui sont concernées par un changement
	 */
	public EnigmaReport(Report report, boolean fulfilled, GameObject ... entities) {
		this.report = report;
		this.fulfilled = fulfilled;
		for (GameObject e : entities) this.add(e);
	}

	/**
	 * Message sur la réalisation de l'action
	 *
	 * @return Message sur la réalisation de l'action
	 */
	public String getReport() {
		return this.report.getReport();
	}

	/**
	 * true si l'action c'est faite sinon false
	 *
	 * @return true si l'action c'est faite sinon false
	 */
	public boolean isFulfilled() {
		return this.fulfilled;
	}

	/**
	 * true si l'action c'est faite sinon false
	 *
	 * @param fulfilled true si l'action c'est faite sinon false
	 */
	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	@Override
	public String toString() {
		return "EnigmaReport{" + "report=" + report + ", fulfilled=" + fulfilled + '}';
	}

	/**
	 * Ajoute une entitée
	 *
	 * @param entity Entitée concernée par une modification
	 */
	public void add(GameObject entity){
		this.entities.add(entity);
	}

	/**
	 * Supprime une entitée
	 *
	 * @param entity Entitée
	 */
	public void remove(GameObject entity){
		this.entities.remove(entity);
	}

	/**
	 * Retourne les entitées
	 *
	 * @return Entitées
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<GameObject> getEntities() {
		return (ArrayList<GameObject>) entities.clone();
	}
}

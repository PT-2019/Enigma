package common.enigmas.reporting;

import com.badlogic.gdx.utils.Array;
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
	private ArrayList<Object> entities;

	/**
	 * Le message retourné l'exécution d'une opération
	 * ou d'une condition.
	 *
	 * @param report   Message sur la réalisation de l'action
	 * @param entities Entités qui sont concernées par un changement
	 */
	public EnigmaReport(Report report, GameObject... entities) {
		this.report = report;
		this.fulfilled = false;
		this.entities = new ArrayList<>();
		if (entities != null) for (GameObject e : entities) this.add(e);
	}

	/**
	 * Le message retourné l'exécution d'une opération
	 * ou d'une condition.
	 *
	 * @param report    Message sur la réalisation de l'action
	 * @param fulfilled true si l'action c'est faite sinon false
	 * @param entities  Entités qui sont concernées par un changement
	 */
	public EnigmaReport(Report report, boolean fulfilled, Object... entities) {
		this.report = report;
		this.fulfilled = fulfilled;
		this.entities = new ArrayList<>();
		if (entities != null) for (Object e : entities) this.add(e);
	}

	/**
	 * Retourne liste des rapports depuis liste des enigmaReport
	 *
	 * @param reports liste des enigmaReport
	 * @return liste des rapports depuis liste des enigmaReport
	 */
	public static ArrayList<Report> getAllReports(ArrayList<EnigmaReport> reports) {
		ArrayList<Report> cpy = new ArrayList<>();
		for (EnigmaReport r : reports) {
			cpy.add(r.report);
		}
		return cpy;
	}

	/**
	 * Retourne liste des entités depuis liste des enigmaReport
	 *
	 * @param reports liste des enigmaReport
	 * @return liste des entités depuis liste des enigmaReport
	 */
	public static Array<GameObject> getAllEntities(ArrayList<EnigmaReport> reports) {
		Array<GameObject> entities = new Array<>();
		for (EnigmaReport r : reports) {
			for (Object o : r.entities) {
				if (o instanceof GameObject) entities.add((GameObject) o);
			}
		}
		return entities;
	}

	/**
	 * Message sur la réalisation de l'action
	 *
	 * @return Message sur la réalisation de l'action
	 */
	public Report getReport() {
		return this.report;
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
	 * Ajoute une entité
	 *
	 * @param entity Entitée concernée par une modification
	 */
	public void add(Object entity) {
		this.entities.add(entity);
	}

	/**
	 * Supprime une entité
	 *
	 * @param entity Entitée
	 */
	public void remove(Object entity) {
		this.entities.remove(entity);
	}

	/**
	 * Retourne les entités
	 *
	 * @return entités
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getEntities() {
		return (ArrayList<Object>) entities.clone();
	}
}

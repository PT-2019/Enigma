package common.enigmas.condition;

import common.enigmas.reporting.ConditionReport;
import common.enigmas.reporting.EnigmaReport;
import common.entities.GameObject;
import common.entities.players.Player;
import common.entities.types.Content;
import common.entities.types.EnigmaContainer;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.language.EnigmaField;
import common.map.AbstractMap;
import common.map.GameMap;
import common.save.enigmas.EnigmaAttributes;
import common.utils.Question;
import data.NeedToBeTranslated;
import game.EnigmaGame;

import java.util.HashMap;
import java.util.Map;

import static common.language.GameLanguage.gl;

/**
 * Vérifie que l'utilisateur donne une réponse correcte
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see Condition
 * @since 2.0
 */
public class Answer extends Condition {

	private static final String ANSWER = "answer", QUESTION = "question";

	/**
	 * Question
	 */
	private Question question;

	/**
	 * Réponse saisie
	 */
	private String answer;

	/**
	 * Vérifie que l'utilisateur donne une réponse correcte
	 *
	 * @param ent      entité qui pose la question
	 * @param question la question dont on attends une réponse
	 */
	public Answer(EnigmaContainer ent, Question question) {
		super((GameObject) ent);
		this.question = question;
		this.answer = null;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Answer(Map<String, Object> attributes) {
		super(attributes);

		//re-crée la question
		if(attributes.containsKey(QUESTION) && attributes.containsKey(ANSWER)){
			this.question = new Question((String)attributes.get(QUESTION), (String) attributes.get(ANSWER));
		}

		this.answer = null;
	}

	@Override
	public HashMap<String, Object> objectToMap() {
		HashMap<String, Object> object = super.objectToMap();
		object.put(QUESTION, this.question.getQuestion());
		object.put(ANSWER, this.question.getAnswer());
		return object;
	}

	/**
	 * Vérifie que la condition est satisfaite
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 * @return true si la condition est satisfaite, false sinon
	 */
	@Override
	public EnigmaReport verify(Player p) {
		if(this.answer == null){
			return new EnigmaReport(ConditionReport.NO_ANSWER, false, this);
		}

		//s'il a donné une réponse qui contient la bonne
		if(this.answer.trim().contains(this.question.getAnswer().trim()))
			return new EnigmaReport(ConditionReport.CORRECT_ANSWER, true);

		return new EnigmaReport(ConditionReport.WRONG_ANSWER, false);
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[Answer]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toLongString() {
		return "[Answer]";
	}

	/**
	 * Retourne la question
	 *
	 * @return Question
	 */
	public Question getQuestion() {
		return question;
	}

	public String getEnigmaElementReadablePrint() {
		return "[" + gl.get(EnigmaField.INPUT_ANSWER) + ": " +
				NeedToBeTranslated.QUESTION + " = \"" + this.question.getQuestion() + "\"" +
				gl.get(EnigmaField.ANSWER) + " = \"" + this.question.getAnswer() + "\" ]";
	}

	/**
	 * Définit la réponse de l'utilisateur
	 * @param answer la réponse de l'utilisateur
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}

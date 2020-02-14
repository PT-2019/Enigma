package common.enigmas.condition;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.ConditionReport;
import common.entities.Entity;
import common.entities.GameObject;
import common.entities.players.Player;
import common.entities.types.Content;
import common.entities.types.EnigmaContainer;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.language.EnigmaField;
import common.utils.Question;
import data.NeedToBeTranslated;

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

	/**
	 * Question
	 */
	private Question question;

	/**
	 * Vérifie que l'utilisateur donne une réponse correcte
	 *
	 * @param ent entité qui pose la question
	 * @param question  la question dont on attends une réponse
	 */
	public Answer(EnigmaContainer ent, Question question) {
		super((GameObject) ent);
		this.question = question;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Answer(Map<String, Object> attributes) {
		super(attributes);
	}

	/**
	 * Vérifie que la condition est satisfaite
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 * @return true si la condition est satisfaite, false sinon
	 */
	@Override
	public EnigmaReport verify(Player p) {
		Content c = (Content) this.entity;
		//TODO: changer inclure réponse ...
		String answer = EnigmaOptionPane.showInputDialog(new EnigmaWindow(), c.getContent());
		return new EnigmaReport(ConditionReport.DONE, (answer.equals(this.question.getAnswer())));
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
	 * @return Question
	 */
	public Question getQuestion() {
		return question;
	}

	public String getEnigmaElementReadablePrint() {
		return "[" + gl.get(EnigmaField.INPUT_ANSWER) + ": " +
				NeedToBeTranslated.QUESTION + " = \"" + this.question.getQuestion() + "\""+
				gl.get(EnigmaField.ANSWER) + " = \"" + this.question.getAnswer() + "\" ]";
	}


}

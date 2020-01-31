package general.enigmas.condition;

import general.entities.Entity;
import general.entities.players.Player;
import general.entities.types.Content;

import java.util.Map;

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
	 * Réponse
	 */
	private String answer;

	/**
	 * @param ent Question
	 * @param answer Réponse
	 */
	public Answer(Content ent, String answer) {
		super((Entity) ent);
		this.answer = answer;
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
	 * @return true si la condtion est satisfaite, false sinon
	 */
	@Override
	public boolean verify(Player p) {
		Content c = (Content) this.entity;
		String answer = EnigmaOptionPane.showInputDialog(new EnigmaWindow(),c.getContent());
		return (answer.equals(this.answer));
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

	public String getContent() {
		return ((Content) entity).getContent();
	}
}

package editor.enigma.condition;

import api.entity.Entity;
import api.entity.types.Content;
import editor.entity.Player;

import java.util.Map;

/**
 * Vérifie que l'utilisateur donne une réponse correcte
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.2
 * @see editor.enigma.condition.Condition
 * @since 2.0
 */
public class Answer extends Condition {

	public Answer(Content ent) {
		super((Entity) ent);
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
		//poser la question et tester si la réponse est bonne
		return false;
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

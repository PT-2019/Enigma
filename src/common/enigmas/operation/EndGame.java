package common.enigmas.operation;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.GameObject;
import common.entities.NullGameObject;
import common.entities.players.Player;
import common.language.EnigmaField;
import data.EnigmaScreens;
import game.EnigmaGame;
import game.screens.GameEndScreen;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static common.language.GameLanguage.gl;

/**
 * Lorsqu'on a fini le jeu (victoire)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 18/02/2020
 * @since 6.0 18/02/2020
 */
public class EndGame extends Operation {

	/**
	 * Lorsqu'on a fini le jeu
	 *
	 * @param object On peut mettre n'importe quel objet/null
	 */
	public EndGame(@Nullable GameObject object) {
		super(new NullGameObject());
		if (object != null) {
			this.entity = object;
		}
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	@SuppressWarnings("unused")
	public EndGame(Map<String, Object> attributes) {
		super(new NullGameObject());
	}

	@Override
	public EnigmaReport run(Player p) {
		//a gagné
		GameEndScreen.setHasWin(true);
		//on affiche l'écran de fin
		EnigmaGame.reload(EnigmaScreens.GAME_END.name());

		return new EnigmaReport(OperationReport.END, true);
	}

	@Override
	public String toLongString() {
		return "[End]";
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[End]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + gl.get(EnigmaField.END) + " ]";
	}
}

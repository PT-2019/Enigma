package common.enigmas.operation;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.players.Player;
import common.entities.special.GameMusic;
import common.entities.special.MusicEditor;
import common.map.GameMap;
import data.NeedToBeTranslated;
import game.EnigmaGame;

import java.util.Map;

/**
 * Changer la musique de fond suite à la résolution d'une énigme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class ChangeMainMusic extends Operation {

	/**
	 * Musique
	 */
	private GameMusic musicGame;

	/**
	 * Change la musique ambiante
	 *
	 * @param object une musique
	 */
	public ChangeMainMusic(MusicEditor object) {
		super(object);
	}

	/**
	 * Change la musique ambiante
	 *
	 * @param object une musique
	 * @param music  gestionnaire des musiques
	 */
	@SuppressWarnings("unused")
	public ChangeMainMusic(MusicEditor object, GameMusic music) {
		super(object);
		this.musicGame = music;
	}

	/**
	 * Change la musique ambiante
	 *
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	@SuppressWarnings("unused")
	public ChangeMainMusic(Map<String, Object> attributes) {
		super(attributes);
		//récupère le gestionnaire
		this.musicGame = ((GameMap) EnigmaGame.getCurrentScreen().getMap()).getGameMusic();
	}

	@Override
	public EnigmaReport run(Player p) {
		if(!this.fulfilled) {
			this.musicGame.changeMusic((MusicEditor) this.entity);
			this.fulfilled = true;
		}
		return new EnigmaReport(OperationReport.MAIN_MUSIC_CHANGED, true);
	}

	@Override
	public String toLongString() {
		return "[MainMusic  : entity = " + this.entity + "]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + NeedToBeTranslated.CHANGE_MAIN_MUSIC + ": " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}

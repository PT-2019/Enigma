package common.enigmas.operation;

import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.OperationReport;
import common.entities.players.Player;
import common.entities.special.GameMusic;
import common.entities.special.MusicEditor;
import data.NeedToBeTranslated;

/**
 * Lance un son suite à la résolution d'une énigme
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class LaunchSound extends Operation {

	/**
	 * Musique
	 */
	private GameMusic musicGame;

	/**
	 * Lance une musique
	 *
	 * @param object une musique
	 */
	public LaunchSound(MusicEditor object) {
		super(object);
	}

	/**
	 * Lance une musique
	 *
	 * @param object une musique
	 * @param music  gestionnaire des musiques
	 */
	@SuppressWarnings("unused")
	public LaunchSound(MusicEditor object, GameMusic music) {
		super(object);
		this.musicGame = music;
	}

	@Override
	public EnigmaReport run(Player p) {
		this.musicGame.playSound((MusicEditor) this.entity);
		return new EnigmaReport(OperationReport.SOUND_LAUNCHED, true);
	}

	@Override
	public String toLongString() {
		return "[Music  : entity = " + this.entity + "]";
	}

	@Override
	public String getEnigmaElementReadablePrint() {
		return "[" + NeedToBeTranslated.MUSIC + ": " + NeedToBeTranslated.WITH + " " +
				this.entity.getReadableName() + " (id=" + this.entity.getID() + ") ]";
	}
}

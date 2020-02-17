package common.entities.types;

import com.badlogic.gdx.utils.Array;
import common.enigmas.TileEventEnum;
import common.enigmas.reporting.EnigmaReport;
import common.entities.players.PlayerGame;
import data.Layer;

/**
 * Pour les objects ayant un état
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 17/02/2020
 * @since 6.0 17/02/2020
 */
public interface ChangeState {

	/**
	 * Méthode qui change l'état
	 *
	 * @param actor l'acteur qui a déclenché l'événement
	 * @param event un événement
	 * @return message de changement d'état
	 */
	EnigmaReport changeState(PlayerGame actor, TileEventEnum event);

	/**
	 * Retourne si l'object est dans son état normal
	 * @return true si l'object est dans son état normal
	 */
	boolean isNormalState();

	/**
	 * Retourne les tiles alternatives ou les tiles normales en fonction de l'état
	 * @return les tiles alternatives ou les tiles normales en fonction de l'état
	 * @since 6.0
	 * @param layer un niveau
	 */
	Array<Float> getTilesFromState(Layer layer);

	/**
	 * true pour être "automatiquement" re-dessiné au changement
	 * @return true pour être "automatiquement" re-dessiné au changement
	 */
	boolean shouldAutomaticRepaint();

}

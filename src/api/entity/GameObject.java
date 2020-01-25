package api.entity;

import api.entity.utils.IDInterface;
import api.enums.Layer;
import api.enums.TypeEntity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.EnumMap;

/**
 * Un object du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 23/12/2019
 * @see api.entity.Entity
 * @see game.entity.item.Room
 * @since 4.0 23/12/2019
 */
public interface GameObject extends IDInterface {

	/**
	 * Renvoi la largeur de l'entité (en cases de 16x16)
	 *
	 * @return la largeur de l'entité (en cases de 16x16)
	 * @since 4.0
	 */
	float getGameObjectWidth();

	/**
	 * Renvoi la hauteur de l'entité (en cases de 16x16)
	 *
	 * @return la hauteur de l'entité (en cases de 16x16)
	 * @since 4.0
	 */
	float getGameObjectHeight();

	/**
	 * Définit la taille de l'entité (en cases de 16x16)
	 *
	 * @param width  la largeur de l'entité (en cases de 16x16)
	 * @param height la hauteur de l'entité (en cases de 16x16)
	 * @since 4.0
	 */
	void setDimension(int width, int height);

	/**
	 * Retourne la position de l'entité en indices ligne, colonne
	 *
	 * @return la position de l'entité en indices ligne, colonne
	 * @since 4.0
	 */
	Vector2 getGameObjectPosition();

	/**
	 * Définit la position de l'entité en indices ligne, colonne
	 *
	 * @param pos vecteur x=colonne, y=ligne
	 * @since 4.0
	 */
	void setGameObjectPosition(Vector2 pos);

	/**
	 * Renvoi les tiles représentant la texture de l'entité au niveau Layer
	 *
	 * @param layer un niveau de la map
	 * @return les tiles représentant la texture de l'entité au niveau Layer
	 * @see Layer
	 * @since 4.0
	 */
	Array<Float> getTiles(Layer layer);

	/**
	 * Définit les tiles représentant la texture de l'entité au niveau Layer
	 *
	 * @param texture les tiles
	 * @param layer   le niveau
	 * @see Layer
	 * @since 4.0
	 */
	void setTiles(Array<Float> texture, Layer layer);

	@Deprecated
	default String getClassName() {
		return "";
	}

	/**
	 * Renvoi les différentes classes implémenté par l'entité
	 *
	 * @return EnumMap&lt;TypeEntite, Boolean&gt;
	 * @since 4.0
	 */
	EnumMap<TypeEntity, Boolean> getImplements();

	/**
	 * Retourne le nom de l'entité
	 *
	 * @return le nom de l'entité
	 */
	String getReadableName();
}

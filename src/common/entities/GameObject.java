package common.entities;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.entities.types.IDInterface;
import common.save.entities.SaveKey;
import common.save.entities.serialization.EntitySerializable;
import data.Layer;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Un object du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.2
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
	 * Renvoi les tiles représentant la texture de l'entité
	 *
	 * @return les tiles représentant la texture de l'entité
	 * @see Layer
	 * @since 6.2
	 */
	Map<Layer, Array<Float>> getTiles();

	/**
	 * Définit les tiles représentant la texture de l'entité au niveau Layer
	 *
	 * @param texture les tiles
	 * @param layer   le niveau
	 * @see Layer
	 * @since 4.0
	 */
	void setTiles(Array<Float> texture, Layer layer);

	/**
	 * Renvoi les différentes classes implémenté par l'entité
	 *
	 * @return EnumMap&lt;TypeEntite, Boolean&gt;
	 * @since 4.1
	 */
	EnumMap<TypeEntity, Boolean> getImplements();

	/**
	 * Retourne le nom de l'entité
	 *
	 * @return le nom de l'entité
	 * @since 4.2
	 */
	String getReadableName();

	/**
	 * Retourne les données additionnelles a sauvegarder pour une entitée,
	 * par exemple dans le .tmx
	 *
	 * @return map des données additionnelles
	 * @see SaveKey
	 * @since 5.0
	 */
	default HashMap<SaveKey, String> getSave() {
		return new HashMap<>();
	}

	/**
	 * Charge les données additionnelles sauvegardées par une entitée,
	 * par exemple dans un .tmx
	 *
	 * @param data données sauvegardées
	 * @see SaveKey
	 * @since 5.0
	 */
	default void load(MapProperties data) {
	}

	/**
	 * Retourne si un gameObject est temporaire
	 *
	 * @return true si temporaire
	 * @since 6.0
	 */
	boolean isTemp();

	/**
	 * Définit un gameObject comme étant temporaire
	 *
	 * @param temp true si temporaire
	 * @since 6.0
	 */
	void setTemp(boolean temp);

	/**
	 * Méthode pour charger des données en plus selon le gameObject
	 *
	 * @param serializable entitySerializable
	 * @param created      l'object (this)
	 * @since 6.1
	 */
	default void serialization(EntitySerializable serializable, GameObject created) {
	}
}
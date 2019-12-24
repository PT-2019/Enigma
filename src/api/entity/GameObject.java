package api.entity;

import api.entity.interfaces.Entity;
import api.entity.interfaces.IDInterface;
import api.enums.Layer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Un object du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 23/12/2019
 * @see Entity
 * @see editor.entity.map.Room
 * @since 4.0 23/12/2019
 */
public interface GameObject extends IDInterface {

	/**
	 * Renvoi la largeur de l'entité (en cases de 16x16)
	 *
	 * @return la largeur de l'entité (en cases de 16x16)
	 * @since 4.0
	 */
	float getWidth();

	/**
	 * Renvoi la hauteur de l'entité (en cases de 16x16)
	 *
	 * @return la hauteur de l'entité (en cases de 16x16)
	 * @since 4.0
	 */
	float getHeight();

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
	Vector2 getPosition();

	/**
	 * Définit la position de l'entité en indices ligne, colonne
	 *
	 * @param pos vecteur x=colonne, y=ligne
	 * @since 4.0
	 */
	void setPosition(Vector2 pos);

	/**
	 * Renvoi les tiles représentant la texture de l'entité au niveau Layer
	 *
	 * @param layer un niveau de la map
	 * @return les tiles représentant la texture de l'entité au niveau Layer
	 * @see Layer
	 * @since 4.0
	 */
	Array<Float> getTexture(Layer layer);

	/**
	 * Définit les tiles représentant la texture de l'entité au niveau Layer
	 *
	 * @param texture les tiles
	 * @param layer   le niveau
	 * @see Layer
	 * @since 4.0
	 */
	void setTexture(Array<Float> texture, Layer layer);
}

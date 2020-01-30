package api.libgdx.utils;

import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import general.entities.GameObject;

/**
 * Tout un paquet de méthodes utiles
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.3
 * @since 2.0 27 novembre 2019
 */
@ConvenienceClass
public final class LibgdxUtility {

	/**
	 * Retourne un skin depuis json et un atlas
	 *
	 * @param json  fichier json
	 * @param atlas fichier atlas
	 * @return le fichier skin
	 * @since 4.0
	 */
	@ConvenienceMethod
	public static Skin loadSkin(String json, String atlas) {
		return new Skin(Gdx.files.internal(json), new TextureAtlas(atlas));
	}

	/**
	 * Regarde s'il y a collision entre deux rectangle, dans un repère orthonormé  bas gauche
	 *
	 * @param rect1 un rect
	 * @param rect2 un rect
	 * @return true s'il y a collision
	 */
	public static boolean overlapsBottomLeftOrigin(Rectangle rect1, Rectangle rect2) {
		if (rect2.x + rect2.width > rect1.x && rect2.x < rect1.x + rect1.width) {
			if (rect2.y - rect2.height < rect1.y && rect2.y > rect1.y - rect1.height) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Regarde si un point est dans une entité, dans un repère orthonormé  bas gauche
	 *
	 * @param parent    objet parent
	 * @param parentPos et sa position
	 * @param x         position x a tester
	 * @param y         position y a tester
	 * @return true si l'object contient le point x,y
	 */
	public static boolean containsBottomLeftOrigin(GameObject parent, Vector2 parentPos, int x, int y) {
		if (x >= parentPos.x && x < parentPos.x + parent.getGameObjectWidth()) {
			if (y < parentPos.y && y >= parentPos.y - parent.getGameObjectHeight()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Calcule l'offset entre deux vecteur, avec value un object ayant une taille
	 *
	 * @param vector1 vec1
	 * @param vector2 vec2
	 * @param value   object avec une taille
	 * @return le décalage (indice tableau une dimension) entre les deux vecteurs.
	 */
	public static int calculatesOffset(Vector2 vector1, Vector2 vector2, GameObject value) {
		if (vector1.equals(vector2)) return 0;
		float x = (vector1.x - vector2.x), y = (vector2.y - vector1.y);
		return MathUtils.ceil(y * value.getGameObjectWidth() + x);
	}

}

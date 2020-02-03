package api.libgdx.actor;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Arrays;

/**
 * Méthode pour permettre des calculs sur des actors
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 21/12/2019
 * @since 4.0 21/12/2019
 */
public class GameActorUtilities {

	/**
	 * Retourne la position absolue dans entité dans l'écran et non
	 * relativement a son parent
	 *
	 * @param actor l'acteur dont on veut la position absolue
	 * @return la position absolue dans entité dans l'écran et non
	 * relativement a son parent
	 * @since 4.0 20/12/2019
	 */
	public static Vector2 getAbsolutePosition(Actor actor) {
		Actor a = actor.getParent();
		Vector2 coords = new Vector2(actor.getX(), actor.getY());
		while (a != null && a.getParent() != null) {//itère tous sauf dernier parent
			a.localToParentCoordinates(coords);
			a = a.getParent();
		}
		return coords;
	}

	/**
	 * Renvoi s'il y a collision entre deux acteurs
	 *
	 * @param actor  un actor
	 * @param target une autre acteur
	 * @return true s'il y a collision sinon false
	 * @since 4.0 20/12/2019
	 */
	public static boolean overlaps(Actor actor, Actor target) {
		//récupère le recouvrement (mis à jour)
		Polygon poly1, poly2;

		//get polygons
		if (actor instanceof GameActor) poly1 = ((GameActor) actor).getBounds();
		else poly1 = getBounds(actor);

		if (target instanceof GameActor) {
			poly2 = ((GameActor) target).getBounds();
		}
		else poly2 = getBounds(target);

		//correct coordinates
		Vector2 coordinates = getAbsolutePosition(actor), autre = getAbsolutePosition(target);

		poly1.setPosition(coordinates.x, coordinates.y);
		poly2.setPosition(autre.x, autre.y);

		//test rectangles
		if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
			return false;

		//test polygons
		return Intersector.overlapConvexPolygons(poly1, poly2);
	}

	/**
	 * Renvoi s'il y a collision entre deux acteurs
	 *
	 * @param actor  un actor
	 * @param target une autre acteur
	 * @return true s'il y a collision sinon false
	 * @since 4.0 20/12/2019
	 */
	public static boolean overlaps(Actor actor, Actor target,float movx,float movy) {
		//récupère le recouvrement (mis à jour)
		Polygon poly1, poly2;

		//get polygons
		if (actor instanceof GameActor) poly1 = ((GameActor) actor).getBounds();
		else poly1 = getBounds(actor);

		if (target instanceof GameActor) {
			poly2 = ((GameActor) target).getBounds();
		}
		else poly2 = getBounds(target);

		//correct coordinates
		Vector2 coordinates = getAbsolutePosition(actor), autre = getAbsolutePosition(target);

		poly1.setPosition(coordinates.x + movx, coordinates.y + movy);
		poly2.setPosition(autre.x, autre.y);

		//test rectangles
		if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
			return false;

		//test polygons
		return Intersector.overlapConvexPolygons(poly1, poly2);
	}

	/**
	 * Retourne si un acteur contient un point
	 *
	 * @param actor l'acteur
	 * @param point le point
	 * @return true si l'acteur contient le point sinon false
	 * @since 4.0 20/12/2019
	 */
	public static boolean contains(Actor actor, Vector2 point) {
		//récupère le recouvrement (mis à jour)
		Polygon poly1 = getBounds(actor);
		Vector2 coords = getAbsolutePosition(actor);
		poly1.setPosition(coords.x, coords.y);

		return poly1.getBoundingRectangle().contains(point);
	}

	/**
	 * Retourne un polygon de recouvrement de l'acteur s'il n'en a pas
	 *
	 * @param a un acteur
	 * @return un polygon de recouvrement de l'acteur s'il n'en a pas
	 * @since 4.0 20/12/2019
	 */
	private static Polygon getBounds(Actor a) {
		float w = a.getWidth(), h = a.getHeight();
		return new Polygon(new float[]{0, 0, w, 0, w, h, 0, h});
	}

}

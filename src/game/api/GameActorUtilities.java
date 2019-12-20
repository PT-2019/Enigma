package game.api;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameActorUtilities {

	public static Vector2 getAbsolutePosition(Actor actor){
		Actor a = actor.getParent();
		Vector2 coords = new Vector2(actor.getX(), actor.getY());
		while (a.getParent() != null) {//itère tous sauf dernier parent
			a.localToParentCoordinates(coords);
			a = a.getParent();
		}
		return coords;
	}

	public static boolean overlaps(Actor actor, Actor target){
		//récupère le recouvrement (mis à jour)
		Polygon poly1, poly2;

		//get polygons
		if(actor instanceof GameActor) poly1 = ((GameActor)actor).getRecouvrement();
		else poly1 = getBounds(actor);

		if(target instanceof GameActor)	poly2 = ((GameActor)target).getRecouvrement();
		else poly2 = getBounds(target);

		//correct coordinates
		Vector2 coordinates = getAbsolutePosition(actor), autre = getAbsolutePosition(target);

		poly1.setPosition(coordinates.x,coordinates.y);
		poly2.setPosition(autre.x,autre.y);

		//test rectangles
		if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
			return false;

		//test polygons
		return Intersector.overlapConvexPolygons(poly1,poly2);
	}

	public static boolean contains(Actor actor, Vector2 point){
		//récupère le recouvrement (mis à jour)
		Polygon poly1 = getBounds(actor);
		Vector2 coords = getAbsolutePosition(actor);
		poly1.setPosition(coords.x,coords.y);

		return poly1.getBoundingRectangle().contains(point);
	}

	private static Polygon getBounds(Actor a){
		float w = a.getWidth(), h = a.getHeight();
		return new Polygon(new float[]{0,0,w,0,w,h,0,h});
	}

}

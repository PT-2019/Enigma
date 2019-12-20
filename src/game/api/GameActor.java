package game.api;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GameActor extends Group {

	private Polygon recouvrement;

	public void setOpacity(float alpha){ this.getColor().a = alpha; }

	public boolean insertersectionAvec(GameActor acteur){
		//récupère le recouvrement (mis à jour)
		Polygon poly1 = this.getRecouvrement(), poly2 = acteur.getRecouvrement();

		Vector2 coords = this.getPositionAbsolue(), autre = acteur.getPositionAbsolue();

		poly1.setPosition(coords.x,coords.y);
		poly2.setPosition(autre.x,autre.y);

		if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
			return false;

		return Intersector.overlapConvexPolygons(poly1,poly2);
	}

	public Vector2 getPositionAbsolue() {
		return GameActorUtilities.getAbsolutePosition(this);
	}

	protected void setRecouvrement(int nbPoints){
		float w = getWidth(), h = getHeight();

		float[] points = new float[2*nbPoints];

		//calcul mathématique qui recouvre notre entité par un polygon
		for (int i = 0; i < nbPoints; i++) {
			float angle = i*6.28f/nbPoints;
			points[2*i] = w/2 * MathUtils.cos(angle) + w/2;
			points[2*i+1] = h/2 * MathUtils.sin(angle) + h/2;
		}

		this.recouvrement = new Polygon(points);
	}

	protected void setRecouvrement(){
		float w = getWidth(), h = getHeight();
		this.recouvrement = new Polygon(new float[]{0,0,w,0,w,h,0,h});
	}

	public Polygon getRecouvrement() {
		if(this.recouvrement == null) this.setRecouvrement(8);
		//mise à jour
		this.recouvrement.setPosition(this.getX(),this.getY());
		this.recouvrement.setOrigin(this.getOriginX(),this.getOriginY());
		this.recouvrement.setRotation(this.getRotation());
		this.recouvrement.setScale(this.getScaleX(),this.getScaleY());
		return this.recouvrement;
	}
}

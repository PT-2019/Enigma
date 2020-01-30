package api.libgdx.actor;

import api.enums.Layer;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Crée un acteur basique qui peut gérer la collision.
 * <p>
 * On commence par utiliser {@link #setBounds(int)} pu {@link #setBounds()} pour créer
 * un Polygon qui recouvre l'entité. Après la méthode {@link #overlaps(Actor)} renvoi s'il y a collision.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 20/12/2019
 * @since 4.0 20/12/2019
 */
public class GameActor extends Group {
	/**
	 * Layer
	 */
	private Layer layer;
	/**
	 * Recouvrement du GameActor
	 */
	private Polygon bounds;

	public GameActor() {
		this.bounds = null;
	}

	/**
	 * Méthode pour changer la transparence facilement
	 *
	 * @param alpha entre 0.0 et 1.0.
	 * @since 4.0 20/12/2019
	 */
	@ConvenienceMethod
	public void setOpacity(float alpha) {
		this.getColor().a = alpha;
	}

	/**
	 * Renvoi s'il y a collision entre deux acteurs
	 *
	 * @param actor une autre acteur
	 * @return true s'il y a collision sinon false
	 * @since 4.0 20/12/2019
	 */
	public boolean overlaps(Actor actor) {
		return GameActorUtilities.overlaps(this, actor);
	}

	/**
	 * Retourne la position absolue dans entité dans l'écran et non
	 * relativement a son parent
	 *
	 * @return la position absolue dans entité dans l'écran et non
	 * relativement a son parent
	 * @since 4.0 20/12/2019
	 */
	public Vector2 getAbsolutePosition() {
		return GameActorUtilities.getAbsolutePosition(this);
	}

	/**
	 * Pour la collision, recouvre l'entité d'un rectangle
	 *
	 * @since 4.0 20/12/2019
	 */
	public void setBounds() {
		float w = getWidth(), h = getHeight();
		this.bounds = new Polygon(new float[]{0, 0, w, 0, w, h, 0, h});
	}

	/**
	 * Retourne le recouvrement du gameActor
	 *
	 * @return le recouvrement du gameActor
	 * @since 4.0 20/12/2019
	 */
	public Polygon getBounds() {
		if (this.bounds == null) this.setBounds(8);
		//mise à jour
		this.bounds.setPosition(this.getX(), this.getY());
		this.bounds.setOrigin(this.getOriginX(), this.getOriginY());
		this.bounds.setRotation(this.getRotation());
		this.bounds.setScale(this.getScaleX(), this.getScaleY());
		return this.bounds;
	}

	/**
	 * Recouvre l'entité avec un polygon de nbPoints points.
	 *
	 * @param nbPoints nombre de points du polygon,
	 *                 plus de 8 risque de dégrader les performances.
	 * @since 4.0 20/12/2019
	 */
	public void setBounds(int nbPoints) {
		if (nbPoints == 4) this.setBounds();
		float w = getWidth(), h = getHeight();

		float[] points = new float[2 * nbPoints];

		//calcul mathématique qui recouvre notre entité par un polygon
		for (int i = 0; i < nbPoints; i++) {
			float angle = i * 6.28f / nbPoints;
			points[2 * i] = w / 2 * MathUtils.cos(angle) + w / 2;
			points[2 * i + 1] = h / 2 * MathUtils.sin(angle) + h / 2;
		}

		this.bounds = new Polygon(points);
	}

	/**
	 *  Retourne le niveau auquel l'entité sera dessiné
	 * @return le niveau
	 */
	public Layer getLayer() {
		return this.layer;
	}
	/**
	 *  Définit le niveau auquel l'entité sera dessiné
	 * @param l niveau
	 */
	public void setLayer(Layer l) {
		this.layer = l;
	}
}

package game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

/**
 * Une composant de l'application ayant
 * une texture ou une animation.
 *
 * @author Maxime Huyghe
 * @author Quentin Ramsamy--Ageorges
 * @author Thibault Meynier
 * @author Loïc Senecat
 * @author Louka Doz
 * @author Lucca Anthoine
 * @version 1.0 27 octobre 2019
 */
public abstract class ActeurTextureAnimation extends Group {

	/**
	 * Stocke l'animation
	 */
	private Animation<TextureRegion> texture;

	/**
	 * Temps écoulé, pour faire avancer l'animation
	 */
	private float tempsEcoule;

	/**
	 * Indique si une entité est en pause
	 */
	private boolean enPause;

	/**
	 * Texture version patch
	 */
	private NinePatch patch;

	/**
	 * Il s'agît d'un polygon qui recouvre l'entité
	 * le plus précisément possible avant de détecter les collisions
	 */
	private Polygon recouvrement;

	/**
	 * Créer un acteur qui a une texture
	 * et une animation
	 */
	public ActeurTextureAnimation(){
		this.enPause = false;
		this.texture = null;
		this.patch = null;
		this.tempsEcoule = 0;
	}

	/**
	 * définit la texture de l'entité, peut être remplacé par une
	 * animation
	 *
	 * @param texture la texture à associer au composant
	 *
	 * @see ActeurTextureAnimation#setAnimation(String, int, int, float)
	 */
	public void setTexture(String texture){
		Array<TextureRegion> liste = new Array<>();
		liste.add(new TextureRegion(new Texture(texture)));
		this.texture = new Animation<>(1,liste);
		this.faireBoucler(true);
		TextureRegion textureRegion = this.texture.getKeyFrame(0);
		float width = textureRegion.getRegionWidth(), height = textureRegion.getRegionHeight();
		this.setSize(width, height);
		this.setOrigin(width/2, height/2);
	}

	/**
	 * définit la texture de l'entité, peut être remplacé par une
	 * animation
	 *
	 * @param texture la texture à associer au composant
	 *
	 * @see ActeurTextureAnimation#setAnimation(String, int, int, float)
	 */
	protected void setTexture(Texture texture) {
		Array<TextureRegion> liste = new Array<>();
		liste.add(new TextureRegion(texture));
		this.texture = new Animation<>(1,liste);
		this.faireBoucler(true);
		TextureRegion textureRegion = this.texture.getKeyFrame(0);
		float width = textureRegion.getRegionWidth(), height = textureRegion.getRegionHeight();
		this.setSize(width, height);
		this.setOrigin(width/2,height/2);
	}

	/**
	 * définit la texture de l'entité, peut être remplacé par une
	 * animation. Ecrase la texture actuelle si dessiné.
	 *
	 * version "9-patch"
	 *
	 * @param ninePatchPath la texture à associer au composant
	 *
	 * @see ActeurTextureAnimation#setAnimation(String, int, int, float)
	 * @see ActeurTextureAnimation#setTexture(String)
	 */
	protected void setPatch(String ninePatchPath) {
		this.patch = new NinePatch(new Texture(ninePatchPath));
	}

	/**
	 * Défini l'animation de l'entité, peut être remplacé par une texture
	 *
	 * @param texture une texture qui contient les animations
	 * @param nbCol le nombre d'animations sur une ligne
	 * @param nbLigne le nombre d'animations sur une colonne
	 * @param secondeParImage combien de temps rester sur chaque animation
	 *
	 * @see ActeurTextureAnimation#setTexture(String)
	 */
	public void setAnimation(String texture, int nbCol, int nbLigne, float secondeParImage){
		//récupération d'un array de texture region
		Array<TextureRegion> listeAnim = new Array<>();

		Texture animation = new Texture(texture);
		animation.setFilter( Texture.TextureFilter.Linear, Texture.TextureFilter.Linear );
		nbCol = animation.getWidth() / nbCol;
		nbLigne = animation.getHeight() / nbLigne;

		for (TextureRegion[] sousRegion: new TextureRegion(animation).split(nbCol,nbLigne)) {
			listeAnim.addAll(sousRegion);
		}

		this.texture = new Animation<>(secondeParImage, listeAnim);

		TextureRegion textureRegion = this.texture.getKeyFrame(0);
		float width = textureRegion.getRegionWidth(), height = textureRegion.getRegionHeight();
		this.setSize(width, height);
		this.setOrigin(width/2,height/2);
	}

	/**
	 * Indique si on doit faire boucler l'animation
	 * @param boucler true pour faire tourner en boucle l'animation
	 */
	public void faireBoucler(boolean boucler){
		if(boucler){
			texture.setPlayMode(Animation.PlayMode.LOOP);
		} else {
			texture.setPlayMode(Animation.PlayMode.NORMAL);
		}
	}

	/**
	 * Contient le code de mise à jour du composant
	 *
	 * @param delta le temps depuis le dernier appel
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		if(!enPause)
			this.tempsEcoule += delta;
	}

	/**
	 * Méthode appelé par le stage lors du dessin du compossant.
	 * Ajout le dessin de la texture.
	 *
	 * @param batch le batch envoyé à OPENGL
	 * @param parentAlpha la transparence
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color parentColor = getColor();
		batch.setColor(parentColor.r, parentColor.g, parentColor.b, parentColor.a);

		//si visible & possède une texture
		if(this.isVisible()){
			if(this.patch != null){
				this.patch.draw(batch,this.getX(), this.getY(),
						this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(), this.getScaleX(),
						this.getScaleY(), this.getRotation()

				);
			} else if(this.texture != null){
				batch.draw(this.texture.getKeyFrame(this.tempsEcoule),this.getX(), this.getY(),
						this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(), this.getScaleX(),
						this.getScaleY(), this.getRotation());
			}
		}

		super.draw(batch, parentAlpha);
	}

	/**
	 * Indique si l'animation est en train de boucler
	 * @return true si l'animation est en train de boucler
	 */
	public boolean isEnTrainDeBoucler(){ return texture.getPlayMode().equals(Animation.PlayMode.LOOP); }

	/**
	 * Met en pause l'animation
	 * @param pause true pour mettre en pause
	 */
	public void setAnimationPause(boolean pause){ this.enPause = pause; }

	/**
	 * Retourne si l'animation est en pause
	 * @return true si l'animation est en pause
	 */
	public boolean isAnimationEnPause() { return enPause; }

	/**
	 * Retourne si l'animation est terminée
	 * @return true si l'animation est terminée
	 */
	public boolean isAnimationTerminee(){ return texture.isAnimationFinished(this.tempsEcoule); }

	/**
	 * Déplace l'animation à la frame désignée par index
	 * @param index l'image de l'animation à jouer, comme à zéro
	 *
	 * @throws IllegalStateException si l'index est invalide
	 */
	protected void setAnimationIndex(int index) {
		if(index < 0) throw new IllegalArgumentException("Index doit être positif");
		int count = (int) (this.texture.getAnimationDuration()/this.texture.getFrameDuration());
		if(index >= count)
			throw new IllegalArgumentException("Cette index est trop grand (max="+(count-1)+") !");
		this.tempsEcoule = index * this.texture.getFrameDuration();
	}

	/**
	 * Définit la transparence de la texture
	 *
	 * @param transparence le taux de transparence (de 0 à 1)
	 */
	public void setOpaque(float transparence) {
		this.getColor().a = transparence;
	}

	/**
	 * Retourne s'il y a collision entre deux entités.
	 *
	 * @param acteur l'acteur à tester
	 *
	 * @return true si les deux acteurs s'intersectent sinon false
	 */
	public boolean insertersectionAvec(ActeurTextureAnimation acteur){
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
		Actor a = this.getParent();
		Vector2 coords = new Vector2(this.getX(), this.getY());
		while (a.getParent() != null) {//itère tous sauf dernier parent
			a.localToParentCoordinates(coords);
			a = a.getParent();
		}
		return coords;

	}

	/**
	 * Recouvre une entité avec un polygon non rectangulaire
	 * @param nbPoints le nombre de points pour recouvrir l'entité
	 *                 Attention, l'opération est couteuse, 6-8 sont raisonnables
	 */
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

	/**
	 * Recouvre l'entité avec un rectangle
	 */
	protected void setRecouvrement(){
		float w = getWidth(), h = getHeight();

		float[] points = new float[]{0,0,w,0,w,h,0,h};

		this.recouvrement = new Polygon(points);
	}

	/**
	 * Retourne le recouvrement de l'entité, si elle n'est pas recouvert, alors
	 * on la recouvre avec 8 points
	 * @return polygon de recouvrement
	 */
	private Polygon getRecouvrement() {
		if(this.recouvrement == null) this.setRecouvrement(8);
		//mise à jour
		this.recouvrement.setPosition(this.getX(),this.getY());
		this.recouvrement.setOrigin(this.getOriginX(),this.getOriginY());
		this.recouvrement.setRotation(this.getRotation());
		this.recouvrement.setScale(this.getScaleX(),this.getScaleY());
		return this.recouvrement;
	}
}

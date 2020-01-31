package api.libgdx.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import datas.Direction;

/**
 * Crée un acteur avec une animation
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 5.0
 */
public class GameActorAnimation extends GameActor {

	/**
	 * Valeur qui représente l'écoulement du temps
	 */
	private float animationElapsedTime;

	/**
	 * boolean qui indique si l'animation est en pause
	 */
	private boolean animationPaused;

	/**
	 * Animation
	 *
	 * @see Animation
	 */
	private Animation<TextureRegion> animation;

	/**
	 * Direction de l'acteur dans l'espace 2d
	 */
	protected Direction facedDirection;

	/**
	 * Creates an Actor witch is able to have
	 * an animation and move
	 */
	public GameActorAnimation() {
		this.animationElapsedTime = 0f;
		this.animation = null;
		this.animationPaused = false;
	}

	/**
	 * Définit l'animation
	 *
	 * @param texture      chemin texture
	 * @param nbCol        nombre de colonnes d'une image
	 * @param nbRow        nombre de lignes d'une image
	 * @param timePerFrame nombre de secondes par image, 0.3f par exemple est une bonne valeur
	 * @param colPerImage  nombre de colonnes totales de l'image
	 * @param rowPerImage  nombre de lignes totales de l'image
	 * @param index        index, commence à zéro représentant la position de la sous-image dans l'atlas
	 */
	public void setAnimation(String texture, int nbCol, int nbRow, float timePerFrame,
	                            int colPerImage, int rowPerImage, int index) {
		//position des colonne et ligne des sous ensembles de sprite
		int col = (index % colPerImage) / nbCol, row = index / (colPerImage * nbRow);
		//nb de sous ensembles de sprites par colonne et image
		int nbimgCol = colPerImage/nbCol;
		int nbimgRow = rowPerImage/nbRow;

		Array<TextureRegion> listeAnim = new Array<>();

		//load the texture witch contains the animation
		Texture animation = new Texture(texture);

		animation.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		//on divise par le nombre de different sprite pour obtenir la taille d'une colonne et ligne
		int sizeCol = animation.getWidth() / nbimgCol;
		int sizeRow = animation.getHeight() / nbimgRow;

		//put all the animations in an array
		TextureRegion[][] regions = new TextureRegion(animation).split(sizeCol, sizeRow);

		//on prends la texture qui nous intéresse
		TextureRegion playerRegion = regions[row][col];

		//on calcul cette fois la taille d'un sprite
		sizeCol = playerRegion.getRegionWidth() / nbCol;
		sizeRow = playerRegion.getRegionHeight() / nbRow;
		//on split pour avoir une nouvelle région
		regions = playerRegion.split(sizeCol,sizeRow);

		//on permute les derniers sprites pour que l'animation se termine sur le sprite fixe
		for (int i = 0; i < nbRow; i++) {
			for (int j = 0; j < nbCol-1; j++) {
				if (j == nbCol-2){
					playerRegion = regions[i][j];
					regions[i][j] = regions[i][j+1];
					regions[i][j+1] = playerRegion;
				}
			}
		}

		//on ajoute tout les sprites
		for (int i = 0; i < nbRow; i++) {
			for (int j = 0; j < nbCol; j++) {
				listeAnim.add(regions[i][j]);
			}
		}

		//creates the animation
		this.animation = new Animation<>(timePerFrame, listeAnim);

		//set Actor informations such as size and origin
		TextureRegion textureRegion = this.animation.getKeyFrame(0);
		float width = textureRegion.getRegionWidth(), height = textureRegion.getRegionHeight();
		this.setSize(width, height);
		this.setOrigin(width / 2, height / 2);
		this.setKeyFrame(2);
	}

	/**
	 * L'animation boucle ?
	 *
	 * @param loop true oui, false non
	 */
	protected void setAnimationLooping(boolean loop) {
		if (loop) this.animation.setPlayMode(Animation.PlayMode.LOOP);
		else this.animation.setPlayMode(Animation.PlayMode.NORMAL);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (!isAnimationPaused()) {//stop avancement delta
			this.animationElapsedTime += delta;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		//render actor
		if (isVisible() && this.animation != null) {
			//trouve automatique l'animation a dessiner
			batch.draw(this.animation.getKeyFrame(this.animationElapsedTime), this.getX(), this.getY(),
					this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(), this.getScaleX(),
					this.getScaleY(), this.getRotation());
		}
		super.draw(batch, parentAlpha);
	}

	/**
	 * L'animation correspond a index est lancée
	 *
	 * @param index commence à zéro, sous-image dans la sous-image de la texture-atlas
	 */
	protected void setKeyFrame(int index) {
		if (index < 0) throw new IllegalArgumentException("Index must be positive!");
		int count = (int) (this.animation.getAnimationDuration() / this.animation.getFrameDuration());
		if (index >= count)
			throw new IllegalArgumentException("Index array out of bounds (max=" + (count - 1) + ") !");
		this.animationElapsedTime = index * this.animation.getFrameDuration();
	}

	/**
	 * Retourne l'animation correspond a index est lancée
	 *
	 * @return index commence à zéro, sous-image dans la sous-image de la texture-atlas
	 */
	protected int getKeyFrameIndex() {
		float elapsed = this.animationElapsedTime;
		float duration = this.animation.getFrameDuration();
		int index = 0;
		while (elapsed - duration > 0) {
			elapsed -= duration;
			index++;
		}

		return index;
	}

	public boolean isAnimationPaused() {
		return this.animationPaused;
	}

	protected void setAnimationPaused(boolean pause) {
		this.animationPaused = pause;
	}

	public void setFacedDirection(Direction facedDirection) {
		this.facedDirection = facedDirection;
	}
}

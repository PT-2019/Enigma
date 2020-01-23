package api.entity.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

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
	 * @see Animation
	 */
	private Animation<TextureRegion> animation;

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
	 * @param texture chemin texture
	 * @param nbCol nombre de colonnes d'une image
	 * @param nbRow nombre de lignes d'une image
	 * @param timePerFrame nombre de secondes par image, 0.3f par exemple est une bonne valeur
	 * @param colPerImage nombre de colonnes totales de l'image
	 * @param rowPerImage nombre de lignes totales de l'image
	 * @param index index, commence à zéro représentant la position de la sous-image dans l'atlas
	 */
	protected void setAnimation(String texture, int nbCol, int nbRow, float timePerFrame,
	                            int colPerImage, int rowPerImage, int index) {
		int row = index / (nbCol / colPerImage), col = index % (nbCol / colPerImage);
		row *= rowPerImage;
		col *= colPerImage;

		Array<TextureRegion> listeAnim = new Array<>();

		//load the texture witch contains the animation
		Texture animation = new Texture(texture);
		animation.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		nbCol = animation.getWidth() / nbCol;
		nbRow = animation.getHeight() / nbRow;

		//put all the animations in an array
		TextureRegion[][] regions = new TextureRegion(animation).split(nbCol, nbRow);

		for (int i = row; i < row + rowPerImage; i++) {
			for (int j = col; j < col + colPerImage; j++) {
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
	}

	/**
	 * L'animation boucle ?
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
	 * @param index commence à zéro, sous-image dans la sous-image de la texture-atlas
	 */
	private void setKeyFrame(int index) {
		if (index < 0) throw new IllegalArgumentException("Index must be positive!");
		int count = (int) (this.animation.getAnimationDuration() / this.animation.getFrameDuration());
		if (index >= count)
			throw new IllegalArgumentException("Index array out of bounds (max=" + (count - 1) + ") !");
		this.animationElapsedTime = index * this.animation.getFrameDuration();
	}

	/**
	 * Retourne l'animation correspond a index est lancée
	 * @return index commence à zéro, sous-image dans la sous-image de la texture-atlas
	 */
	private int getKeyFrameIndex() {
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
}

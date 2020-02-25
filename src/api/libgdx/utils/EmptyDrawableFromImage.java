package api.libgdx.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Crée un drawable vide, copié d'une image.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 26/02/2020
 * @since 6.0 26/02/2020
 */
public class EmptyDrawableFromImage implements Drawable {

	private final Drawable drawable;

	/**
	 * Crée un drawable vide, copié d'une image.
	 * @param image image
	 */
	public EmptyDrawableFromImage(Image image) {
		this.drawable = image.getDrawable();
	}

	@Override//nothing
	public void draw(Batch batch, float v, float v1, float v2, float v3) {
	}

	@Override
	public float getLeftWidth() {
		return this.drawable.getLeftWidth();
	}

	@Override
	public void setLeftWidth(float v) {

	}

	@Override
	public float getRightWidth() {
		return this.drawable.getRightWidth();
	}

	@Override
	public void setRightWidth(float v) {

	}

	@Override
	public float getTopHeight() {
		return this.drawable.getTopHeight();
	}

	@Override
	public void setTopHeight(float v) {

	}

	@Override
	public float getBottomHeight() {
		return this.drawable.getBottomHeight();
	}

	@Override
	public void setBottomHeight(float v) {

	}

	@Override
	public float getMinWidth() {
		return this.drawable.getMinWidth();
	}

	@Override
	public void setMinWidth(float v) {

	}

	@Override
	public float getMinHeight() {
		return this.drawable.getMinHeight();
	}

	@Override
	public void setMinHeight(float v) {

	}
}

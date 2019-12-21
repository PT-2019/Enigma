package api.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Crée un acteur avec une texture
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0 20/12/2019
 * @since 4.0 20/12/2019
 */
public class GameActorTextured extends GameActor {

	/**
	 * Son image
	 */
	private TextureRegion texture;

	/**
	 * Crée un acteur avec une texture
	 * @see #setTexture(String)
	 * @see #setTexture(Texture)
	 *
	 * @since 4.0 20/12/2019
	 */
	public GameActorTextured(){
		this.texture = null;
	}

	/**
	 * Crée un acteur avec une texture
	 *
	 * @param texture chemin de la texture
	 *
	 * @since 4.0 20/12/2019
	 */
	public GameActorTextured(String texture){
		this.setTexture(texture);
	}

	/**
	 * Définit la texture de l'acteur depuis un chemin
	 *
	 * @param texture le chemin de la texture
	 *
	 * @since 4.0 20/12/2019
	 */
	public void setTexture(String texture){
		this.texture = new TextureRegion(new Texture(texture));
		float width = this.texture.getRegionWidth(), height = this.texture.getRegionHeight();
		this.setSize(width, height);
		this.setOrigin(width/2, height/2);
	}

	/**
	 * Définit la texture de l'acteur depuis une texture
	 *
	 * @param texture la texture
	 *
	 * @since 4.0 20/12/2019
	 */
	public void setTexture(Texture texture) {
		this.texture = new TextureRegion(texture);
		float width = this.texture.getRegionWidth(), height = this.texture.getRegionHeight();
		this.setSize(width, height);
		this.setOrigin(width/2, height/2);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color parentColor = getColor();
		batch.setColor(parentColor.r, parentColor.g, parentColor.b, parentColor.a);

		//si visible et a une texture, on le dessine
		if(this.isVisible() && this.texture != null){
			batch.draw(this.texture,this.getX(), this.getY(),
					this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(), this.getScaleX(),
					this.getScaleY(), this.getRotation());

		}

		super.draw(batch, parentAlpha);
	}
}

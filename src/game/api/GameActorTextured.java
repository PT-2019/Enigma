package game.api;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GameActorTextured extends GameActor {

	private TextureRegion texture;

	public GameActorTextured(){
		this.texture = null;
	}

	public GameActorTextured(String texture){
		this.setTexture(texture);
	}

	public void setTexture(String texture){
		this.texture = new TextureRegion(new Texture(texture));
		float width = this.texture.getRegionWidth(), height = this.texture.getRegionHeight();
		this.setSize(width, height);
		this.setOrigin(width/2, height/2);
	}

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

		if(this.isVisible() && this.texture != null){
			batch.draw(this.texture,this.getX(), this.getY(),
					this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(), this.getScaleX(),
					this.getScaleY(), this.getRotation());

		}

		super.draw(batch, parentAlpha);
	}
}

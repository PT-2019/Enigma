package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.api.LibgdxGame;
import game.screen.TestScreen;

//CECI EST L'APPLICATION DE BASE DE LA LIBGDX
public class EnigmaGame extends LibgdxGame {

	@Override
	public void start() {
		setCurrentScreen(new TestScreen());
	}

}

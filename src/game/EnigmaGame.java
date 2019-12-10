package game;

import com.badlogic.gdx.Gdx;
import game.api.LibgdxGame;
import game.screen.TestScreen;

public class EnigmaGame extends LibgdxGame {

	@Override
	public void start() {
		//we should load all screens here ?
		setCurrentScreen(new TestScreen());//load first screen
	}

}

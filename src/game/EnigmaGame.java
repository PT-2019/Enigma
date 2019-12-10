package game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import game.api.LibgdxGame;
import game.screen.TestScreen;

public class EnigmaGame extends LibgdxGame {

	//here player and such should be loaded as they live in every screens

	@Override
	public void start() {
		Gdx.gl.glClearColor(255, 255, 255, 255);
		Gdx.app.setLogLevel(Application.LOG_ERROR|Application.LOG_DEBUG);

		EnigmaGame.addScreen("Test", TestScreen.class);
		//EnigmaGame.load("Test"); //we could do this to load screen as the program starts

		EnigmaGame.setScreen("Test");
	}
}

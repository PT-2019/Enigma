package game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import editor.enigma.Enigma;
import game.api.LibgdxGame;
import game.screen.TestScreen;

import java.awt.*;

public class EnigmaGame extends LibgdxGame {

	//here player and such should be loaded as they live in every screens

	private static EnigmaGame enigmaGame;

	private Container container;

	private EnigmaGame(){}

	private EnigmaGame(Container container){
		this.container = container;
	}

	@Override
	public void start() {
		enigmaGame = this;
		Gdx.gl.glClearColor(255, 255, 255, 255);
		Gdx.app.setLogLevel(Application.LOG_ERROR|Application.LOG_DEBUG);

		EnigmaGame.addScreen("Test", TestScreen.class);
		//EnigmaGame.load("Test"); //we could do this to load screen as the program starts

		EnigmaGame.setScreen("Test");
	}

	public static EnigmaGame getInstance() {
		if(enigmaGame == null){
			enigmaGame = new EnigmaGame();
		}
		return enigmaGame;
	}

	public static EnigmaGame getInstance(Container container){
		if(enigmaGame == null){
			enigmaGame = new EnigmaGame(container);
		}
		return enigmaGame;
	}
}

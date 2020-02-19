package game.screens;

import api.libgdx.LibgdxScreen;
import api.utils.annotations.DoNothing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import common.map.AbstractMap;
import common.utils.Logger;
import game.EnigmaGame;

/**
 * écran de fin du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 5.0
 */
public class GameEndScreen extends LibgdxScreen {

	private static boolean win = true;
	/**
	 * Stage de la map et du jeu
	 */
	private Stage main;

	@Override
	public void init() {
		this.main = new Stage();

		//Screen de fin
		EndScreen endScreen = new EndScreen();
		//écran de fin
		this.main.addActor(endScreen);

		if(win){
			endScreen.showVictory();
		} else {
			endScreen.showGameOver();
		}

		//écoute des inputProcessor et des listeners
		this.listen(this.main);
	}

	@Override//géré par input processor
	public void input() {
	}

	@Override
	public void update(float dt) {
		this.main.act(dt);
	}

	@Override
	public void render() {
		this.main.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.main.getViewport().setScreenSize(width, height);
		this.main.getViewport().update(width, height);
		EnigmaGame.setScreenSize(width, height);
	}

	@Override
	public void dispose() {
		try {
			this.main.dispose();
		} catch (Exception e) {
			Logger.printError("BlankScreen#dispose", "échec de la libération des ressources.");
		}
	}

	@Override
	public void show() {
		super.show();
		Gdx.gl20.glClearColor( 1.0f,1.0f,1.0f,1.0f);
	}

	@Override@DoNothing
	public boolean setMap(String absolutePath) {return false;}

	@Override@DoNothing
	public AbstractMap getMap() { return null; }

	/**
	 * Définit s'il ont gagné ou perdu
	 * @param win true gagné sinon perdu
	 */
	public static void setHasWin(boolean win){
		GameEndScreen.win = win;
	}
}
package game.screens;

import api.libgdx.LibgdxScreen;
import api.libgdx.actor.GameActor;
import api.utils.Utility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import common.entities.players.PlayerGame;
import common.map.AbstractMap;
import common.map.GameMap;
import common.utils.Logger;
import game.EnigmaGame;

import java.util.ArrayList;
import java.util.logging.FileHandler;

/**
 * écran du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 5.0
 */
public class GameScreen extends LibgdxScreen {

	/**
	 * Chemin de la map du jeu
	 */
	private static String MAP_PATH = "assets/files/map/cocoa.tmx";

	/**
	 * Stage de la map et du jeu
	 */
	private Stage main;
	/**
	 * Stage de l'interface
	 */
	private Stage hud;

	/**
	 * La map libgdx
	 */
	private GameMap map;

	@Override
	public void init() {
		this.main = new Stage();
		this.hud = new Stage();

		this.map = new GameMap(MAP_PATH, 2.5f);
		//ajout au stage
		this.main.addActor(this.map);
		this.map.showGrid(false);

		//compléter ici
		ArrayList<GameActor> actors = this.map.getGameEntities();

		for (GameActor actor : actors) {
			if (actor instanceof PlayerGame){
				((PlayerGame) actor).center();
				this.listen(((PlayerGame) actor));
			}
		}

		this.map.launchMusic();
		//écoute des inputProcessor et des listeners
		this.listen(this.hud);
		this.listen(this.main);
	}

	@Override//géré par input processor
	public void input() {
	}

	@Override
	public void update(float dt) {
		this.hud.act(dt);
		this.main.act(dt);
	}

	@Override
	public void render() {
		this.main.draw();
		this.hud.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.hud.getViewport().setScreenSize(width, height);
		this.hud.getViewport().update(width, height);
		this.main.getViewport().setScreenSize(width, height);
		this.main.getViewport().update(width, height);
		EnigmaGame.setScreenSize(width, height);
	}

	@Override
	public void dispose() {
		try {
			this.main.dispose();
			this.hud.dispose();
		} catch (Exception e) {
			Logger.printError("GameScreen#dispose", "échec de la libération des ressources.");
		}
	}

	@Override
	public void show() {
		super.show();
		Gdx.gl20.glClearColor(0.20f, 0.20f, 0.20f, 1.0f);
	}

	@Override
	public boolean setMap(String absolutePath) {
		if (!absolutePath.equals(MAP_PATH)) {
			MAP_PATH = absolutePath;
			return true;
		}
		return false;
	}

	@Override
	public AbstractMap getMap() {
		return this.map;
	}

	/**
	 * Retourne le chemin de la map actuelle
	 * @return le chemin de la map
	 */
	public static String getMapPath() {
		return MAP_PATH;
	}
}
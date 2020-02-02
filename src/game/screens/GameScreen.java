package game.screens;

import api.libgdx.LibgdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import common.entities.players.PlayerGame;
import common.map.AbstractMap;
import common.map.GameMap;
import common.save.entities.serialization.PlayerFactory;
import game.EnigmaGame;

/**
 * Ecran du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 31/01/2020
 * @since 5.0 31/01/2020
 */
public class GameScreen extends LibgdxScreen {

	private static String MAP_PATH = "assets/files/map/test5.tmx";

	/**
	 * Stage de la map et du jeu
	 */
	private Stage main;
	/**
	 * Stage de l'interface
	 */
	private Stage hud;
	/**
	 * Joueur
	 */
	private PlayerGame player;
	/**
	 * La map libgdx
	 */
	private GameMap map;

	@Override
	public void init() {
		try {
			this.main = new Stage();
			this.hud = new Stage();

			this.map = new GameMap(MAP_PATH, 2.5f);
			//ajout au stage
			this.main.addActor(this.map);
			this.map.showGrid(false);

			//compléter ici
			player = PlayerFactory.createPlayerGame("Blonde", "assets/entities/players/players.json", this.map);
			this.map.addEntity(player);
			player.center();

			//écoute des inputProcessor et des listeners
			this.listen(this.hud);
			this.listen(player);
			this.listen(this.main);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			Gdx.app.error("GameScreen", "dispose failed");
		}
	}

	@Override
	public void show() {
		super.show();
		Gdx.gl20.glClearColor(0.20f,0.20f,0.20f,1.0f);
	}

	public AbstractMap getMap() {
		return this.map;
	}
}
package game.screens;

import api.libgdx.LibgdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import datas.Layer;
import game.EnigmaGame;
import general.entities.players.PlayerGame;
import general.entities.serialization.PlayerFactory;
import general.map.MapGame;

/**
 * Ecran du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 31/01/2020
 * @since 5.0 31/01/2020
 */
public class GameScreen extends LibgdxScreen {
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
	private MapGame map;

	private static String MAP_PATH = "assets/map/Licht.tmx" ;
	@Override
	public void init() {
		try {
			this.main = new Stage();
			this.hud = new Stage();

			this.map = new MapGame(MAP_PATH, 2.5f);
			//ajout au stage
			this.main.addActor(this.map);
			this.map.showGrid(false);

			//compléter ici
			player = PlayerFactory.createPlayerGame("Blonde","assets/entities/players/players.json", this.map);
			player.setLayer(Layer.FLOOR2);
			//main.addActor(player);

			this.map.addEntity(player);

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
	public void display(boolean display) {

	}
}
package game.screen;

import api.LibgdxScreen;
import api.entity.actor.GameActorAnimation;
import api.enums.Layer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import editor.entity.Player;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import editor.view.TestMapControl;
import game.EnigmaGame;
import game.entity.PlayerFactory;
import game.entity.PlayerGame;
import game.entity.map.MapGame;
import game.entity.map.MapGame;
import game.entity.map.MapTestScreen;
import game.hud.CategoriesMenu;

import java.awt.*;

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
	private MapGame  map;

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

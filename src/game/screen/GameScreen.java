package game.screen;

import api.LibgdxScreen;
import api.entity.actor.GameActorAnimation;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import editor.entity.Player;
import game.EnigmaGame;
import game.entity.PlayerGame;
import game.entity.map.MapGame;

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


	@Override
	public void init() {
		this.main = new Stage();
		this.hud = new Stage();

		//compléter ici
		player = new PlayerGame(new MapGame("",45));
		main.addActor(player);

		//écoute des inputProcessor et des listeners
		this.listen(this.hud);
		this.listen(player);
		this.listen(this.main);
	}

	@Override
	public void show() {
		super.show();
		//Met l'écran en rouge, utilisé juste pour voir que c'est l'écran de jeu
		Gdx.gl.glClearColor(255, 0, 0, 255);
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
			Gdx.app.error("TestScreen", "dispose failed");
		}
	}

	@Override
	public void display(boolean display) {

	}
}

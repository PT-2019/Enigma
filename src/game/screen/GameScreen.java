package game.screen;

import api.LibgdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import editor.view.TestMapControl;
import game.EnigmaGame;
import game.entity.map.MapGame;
import game.entity.map.MapTestScreen;
import game.hud.CategoriesMenu;

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
	 * La map libgdx
	 */
	private MapGame map;

	private static String MAP_PATH = "assets/map/Licht.tmx" ;
	@Override
	public void init() {
		try {
			//Colorie l'écran en blanc
			Gdx.gl.glClearColor(255, 0, 0, 255);

			boolean noMap = false;

			//Regarde si on a une map
			if (MAP_PATH == null || MAP_PATH.length() == 0)
				noMap = true;

			this.main = new Stage();
			if (!noMap) {
				this.map = new MapGame(MAP_PATH, 1f);
				this.map.showGrid(true);
				//ajout au stage
				this.main.addActor(this.map);
			}

			this.hud = new Stage();

			//cameras
			if (!noMap) {
				this.main.setViewport(new ScreenViewport());
				//centre map dans l'écran
				this.main.getViewport().setCamera(this.map.getCamera());
				this.main.getCamera().position.set(
						map.getMapWidth() /2 - CategoriesMenu.WIDTH / 2f,
						map.getMapHeight() /2, 0
				);
			}

			//écoute des inputProcessor et des listeners
			this.listen(this.hud);
			this.listen(this.main);

		} catch (Exception e) {
			System.err.println("échec création GameScreen");
			e.printStackTrace();
		}
	}

	@Override
	public void show() {
		super.show();
		//Met l'écran en rouge, utilisé juste pour voir que c'est l'écran de jeu
		Gdx.gl.glClearColor(255, 255, 255, 255);
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
		this.map.showGrid(false);
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

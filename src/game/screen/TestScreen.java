package game.screen;

import api.LibgdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import editor.entity.view.TestMapControl;
import game.EnigmaGame;
import game.entity.map.MapTestScreen;
import game.hud.CategoriesMenu;

/**
 * TestScreen de la libgdx dans l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.4
 * @since 2.0
 */
public class TestScreen extends LibgdxScreen {

	private static String MAP_PATH = "assets/map/EmptyMap.tmx";
	/**
	 * Stage de la map et du jeu
	 */
	private Stage main;
	/**
	 * Stage de l'interface
	 */
	private Stage hud;
	/**
	 * Stage du drag and drop
	 */
	private Stage dnd;
	/**
	 * La map libgdx
	 */
	private MapTestScreen map;

	/**
	 * Prépare les stages, la map et la caméra
	 */
	@Override
	public void init() {
		Gdx.gl.glClearColor(255, 255, 255, 255);

		//map
		this.main = new Stage();
		this.map = new MapTestScreen(MAP_PATH, 1f);
		this.map.showGrid(true);
		this.main.addActor(this.map);

		//Drag and drop
		this.dnd = new Stage();

		//hud (interface)
		this.hud = new Stage();
		this.hud.addActor(new CategoriesMenu(dnd));

		//cameras
		//cameras
	//	this.main.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		//this.main.getViewport().setCamera(map.getCamera());

		//écoute inputProcessors
		this.listen(this.dnd);
		this.listen(this.hud);
		this.listen(this.main);
		this.listen(new TestMapControl(map));
	}

	@Override
	public void show() {
		super.show();
		Gdx.gl.glClearColor(255, 255, 255, 255);
	}

	@Override//géré par input processor
	public void input() {
	}

	@Override
	public void update(float dt) {
		this.dnd.act(dt);
		this.hud.act(dt);
		this.main.act(dt);
	}

	@Override
	public void render() {
		this.main.draw();
		this.hud.draw();
		this.dnd.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.dnd.getViewport().setScreenSize(width, height);
		this.hud.getViewport().setScreenSize(width, height);
		this.main.getViewport().setScreenSize(width, height);
		this.main.getViewport().update(width, height);
		EnigmaGame.setScreenSize(width, height);
	}

	@Override
	public void dispose() {
		try {
			this.main.dispose();
			this.dnd.dispose();
			this.hud.dispose();
		} catch (Exception e) {
			Gdx.app.error("TestScreen","dispose failed");
		}
	}

	@Override
	public void display(boolean display) {

	}

	/**
	 * Retourne le chemin de la map
	 * @return chemin de la map
	 */
	public static String getMapPath() {
		return MAP_PATH;
	}

	/**
	 * Définit la map de l'écran
	 * @param absolutePath chemin
	 * @return true si map changée sinon false, pas changée si c'est déjà la bonne
	 */
	public boolean setMap(String absolutePath) {
		if (!absolutePath.equals(MAP_PATH)) {
			MAP_PATH = absolutePath;
			return true;
		}
		return false;
	}

	/**
	 * Retourne la map
	 * @return la map
	 */
	public MapTestScreen getMap() {
		return map;
	}
}

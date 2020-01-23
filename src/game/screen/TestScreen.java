package game.screen;

import api.LibgdxScreen;
import api.enums.EditorState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import editor.view.TestMapControl;
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

	//si tu veux charger une map c'est ici sans passer par le launcher
	private static String MAP_PATH = "assets/map/map_system/EmptyMap.tmx";

	/**
	 * L'état de l'éditeur
	 */
	private static EditorState state;

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
	 * Retourne le chemin de la map
	 *
	 * @return chemin de la map
	 */
	public static String getMapPath() {
		return MAP_PATH;
	}

	/**
	 * Prépare les stages, la map et la caméra
	 */
	@Override
	public void init() {
		try {
			//Colorie l'écran en blanc
			Gdx.gl.glClearColor(255, 255, 255, 255);

			boolean noMap = false;

			//Regarde si on a une map
			if (MAP_PATH == null || MAP_PATH.length() == 0)
				noMap = true;

			//map
			this.main = new Stage();
			if (!noMap) {
				this.map = new MapTestScreen(MAP_PATH, 1f);
				this.map.showGrid(true);
				//ajout au stage
				this.main.addActor(this.map);
			}

			//Drag and drop
			this.dnd = new Stage();

			//hud (interface)
			this.hud = new Stage();
			this.hud.addActor(new CategoriesMenu(dnd));

			//cameras
			if (!noMap) {
				this.main.setViewport(new ScreenViewport());
				//centre map dans l'écran
				this.main.getViewport().setCamera(this.map.getCamera());
				this.main.getCamera().position.set(
						map.getMapWidth() / 2 - CategoriesMenu.WIDTH / 2f,
						map.getMapHeight() / 2, 0
				);
			}

			//écoute inputProcessors
			//leurs listeners seront appelés...
			this.listen(this.dnd);
			this.listen(this.hud);
			this.listen(this.main);
			if (!noMap)
				this.listen(new TestMapControl(map));
		} catch (Exception e) {
			System.err.println("échec création testScreen");
			e.printStackTrace();
		}
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
		this.hud.getViewport().update(width, height);
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
			Gdx.app.error("TestScreen", "dispose failed");
		}
	}

	@Override
	public void display(boolean display) {
	}

	/**
	 * Définit la map de l'écran
	 *
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
	 *
	 * @return la map
	 */
	public MapTestScreen getMap() {
		return map;
	}

	public static EditorState getState() {
		return TestScreen.state;
	}

	public static boolean isState(EditorState state){
		return state.equals(TestScreen.state);
	}

	public static void setState(EditorState state) {
		TestScreen.state = state;
	}
}

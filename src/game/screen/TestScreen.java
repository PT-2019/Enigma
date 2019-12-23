package game.screen;

import api.LibgdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import editor.entity.view.TestMapControl;
import game.EnigmaGame;
import game.entity.MapLibgdx;
import game.ui.CategoriesMenu;

/**
 * TestScreen de la libgdx dans l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.3
 * @since 2.0
 */
public class TestScreen extends LibgdxScreen {

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
	private MapLibgdx map;

	/**
	 * Prépare les stages, la map et la caméra
	 */
	@Override
	public void init() {
		Gdx.gl.glClearColor(255, 255, 255, 255);

		//map
		this.main = new Stage();
		this.map = new MapLibgdx("assets/map/Empty.tmx");
		this.main.addActor(this.map);

		//Drag and drop
		this.dnd = new Stage();

		//hud (interface)
		this.hud = new Stage();
		this.hud.addActor(new CategoriesMenu(dnd));

		//cameras
		this.main.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		//centre map dans l'écran
		this.main.getCamera().position.set(
				map.getMapWidth() / 2 - CategoriesMenu.WIDTH / 2f,
				map.getMapHeight() / 2, 0
		);

		//écoute inputProcessors
		this.listen(this.dnd);
		this.listen(this.hud);
		this.listen(this.main);
		this.listen(new TestMapControl(map));
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

	public MapLibgdx getMap() {
		return map;
	}

	@Override
	public void display(boolean display) {
	}

	@Override
	public void dispose() {
		this.main.dispose();
		this.dnd.dispose();
		this.hud.dispose();
	}
}

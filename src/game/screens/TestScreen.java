package game.screens;

import api.libgdx.LibgdxScreen;
import api.libgdx.ui.Toast;
import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import common.map.MapTestScreen;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.config.Config;
import editor.popup.TestMapControl;
import game.EnigmaGame;
import game.gui.CategoriesMenu;
import game.gui.EnigmaEditorToast;
import game.gui.listeners.CreateMapListener;
import game.gui.listeners.OpenMapListener;

/**
 * TestScreen de la libgdx dans l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 2.0
 */
public class TestScreen extends LibgdxScreen {

	/**
	 * Couleur du fond (gris clari)
	 */
	private static final float BG_R=0.6f, BG_G=0.6f, BG_B=0.6f, BG_A = 1.0f;
	private static final String OPEN_MAP = NeedToBeTranslated.OPEN_MAP;
	private static final String CREATE_MAP = NeedToBeTranslated.NEW_MAP;
	//blanc
	//private static final float BG_R=1.0f, BG_G=1.0f, BG_B=1.0f, BG_A = 1.0f;

	//si tu veux charger une map c'est ici sans passer par le launcher
	//private static String MAP_PATH = "assets/map/map_system/EmptyMap.tmx";
	//private static String MAP_PATH = Config.MAP_FOLDER + "cocoa.tmx";
	private static String MAP_PATH = "";

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
	 * Toast pour afficher info
	 */
	private Toast toast;

	/**
	 * Retourne le chemin de la map actuelle
	 *
	 * @return le chemin de la map
	 */
	public static String getMapPath() {
		return MAP_PATH;
	}

	/**
	 * Prépare les stages, la map et la caméra
	 */
	@Override
	public void init() {
		//création des stages
		this.main = new Stage();//Jeu
		this.hud = new Stage();//Interface
		this.dnd = new Stage();//Drag and drop

		//Colorie l'écran en gris
		Gdx.gl.glClearColor(BG_R, BG_G, BG_B , BG_A);

		//s'il y a une map
		if(TestScreen.MAP_PATH != null && TestScreen.MAP_PATH.length() != 0){
			try {
				//Création de la map
				this.map = new MapTestScreen(MAP_PATH, 1f);
				this.map.showGrid(true);
				this.main.addActor(this.map); //ajout au stage

				//menu des catégories
				this.hud.addActor(new CategoriesMenu(dnd));

				//cameras
				this.main.setViewport(new ScreenViewport());
				//centre map dans l'écran
				this.main.getViewport().setCamera(this.map.getCamera());
				this.main.getCamera().position.set(
						this.map.getMapWidth() / 2 - CategoriesMenu.WIDTH / 2f,
						this.map.getMapHeight() / 2, 0
				);

				//crée le toast
				this.toast = new EnigmaEditorToast();
				this.hud.addActor(this.toast);//ajout au stage

				//listen la map en premier !
				this.listen(new TestMapControl(this.map));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//crée le toast
			this.toast = new EnigmaEditorToast();
			this.dnd.addActor(this.toast);//ajout au stage DND car vide (!)
			Skin skin = LibgdxUtility.loadSkin(Config.JSON_PATH, Config.SKIN_PATH);
			Table layout = new Table(skin);
			layout.setFillParent(true);
			TextButton openMap = new TextButton(OPEN_MAP, skin, "map");
			TextButton createMap = new TextButton(CREATE_MAP, skin, "map");
			openMap.pad(5);
			createMap.pad(5);
			openMap.addListener(new OpenMapListener());
			createMap.addListener(new CreateMapListener());

			HorizontalGroup p = new HorizontalGroup();
			p.space(50);
			p.addActor(openMap);
			p.addActor(createMap);

			layout.add(p).expand();

			this.hud.addActor(layout);
		}

		//écoute des événements
		this.listen(this.dnd);
		this.listen(this.hud);
		this.listen(this.main);
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
			MAP_PATH = ""; //retire la map
			this.main.dispose();
			this.dnd.dispose();
			this.hud.dispose();
		} catch (Exception e) {
			Logger.printError("TestScreen#dispose", "échec de la libération des ressources.");
		}
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
	public void showToast(String message) {
		this.toast.update(message);
	}

	@Override
	public MapTestScreen getMap() {
		return this.map;
	}

	@Override
	public void show() {
		super.show();
		Gdx.gl.glClearColor(BG_R, BG_G, BG_B , BG_A);
	}
}

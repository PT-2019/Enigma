package game.screens;

import api.libgdx.LibgdxScreen;
import api.libgdx.actor.GameActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import common.dialog.EnigmaDialogPopup;
import common.entities.players.PlayerGame;
import common.entities.special.inventory.InventoryDisplay;
import common.map.AbstractMap;
import common.map.GameMap;
import common.timer.TimerFrame;
import common.utils.Logger;
import data.config.Config;
import game.EnigmaGame;

import java.util.ArrayList;

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
	private static String MAP_PATH = "";
	/**
	 * Timer personnalisé
	 */
	private static float timer = 0;
	/**
	 * Stage de la map et du jeu
	 */
	private Stage main;
	/**
	 * Stage de l'interface
	 */
	private Stage hud;
	/**
	 * Stage du dnd
	 */
	private Stage dnd;
	/**
	 * La map libgdx
	 */
	private GameMap map;

	/**
	 * Le stage en charge de l'inventaire
	 */
	private InventoryDisplay inventoryDisplay, playerInventory;

	/**
	 * Retourne le chemin de la map actuelle
	 *
	 * @return le chemin de la map
	 */
	public static String getMapPath() {
		return MAP_PATH;
	}

	/**
	 * Définit la valeur de départ pour un timer personnalisé
	 *
	 * @param minutes nombre de minutes
	 */
	public static void setTimerDuration(float minutes) {
		timer = minutes;
	}

	@Override
	public void init() {
		try {
			this.main = new Stage();
			this.hud = new Stage();
			this.dnd = new Stage();

			if (MAP_PATH != null && !MAP_PATH.isEmpty()) {
				//création de l'inventaire d'un objet
				this.inventoryDisplay = new InventoryDisplay(this);

				this.map = new GameMap(MAP_PATH, Config.UNIT_SCALE, this);
				EnigmaDialogPopup dialog = GameMap.getEnigmaDialog();

				//ajout au stage
				this.main.addActor(this.map);
				this.map.showGrid(false);

				//compléter ici
				ArrayList<GameActor> actors = this.map.getGameEntities();

				for (GameActor actor : actors) {
					if (actor instanceof PlayerGame) {
						((PlayerGame) actor).center();
						this.listen(((PlayerGame) actor));
						this.hud.addActor(((PlayerGame) actor).getInventoryView());
					}
				}
				this.hud.addActor(inventoryDisplay);
				this.hud.addActor(dialog);
				//timer
				if (timer == 0) this.hud.addActor(new TimerFrame());
				else {
					this.hud.addActor(new TimerFrame(0, timer));
					timer = 0;//supprime le timer custom pour la prochaine partie
				}
				this.listen(dialog);
			}

			//écoute des inputProcessor et des listeners
			this.listen(this.dnd);
			this.listen(this.hud);
			this.listen(this.main);
		}catch (Exception e){
			e.printStackTrace();
		}
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
		this.dnd.getViewport().update(width, height);
		this.hud.getViewport().setScreenSize(width, height);
		this.hud.getViewport().update(width, height);
		this.main.getViewport().setScreenSize(width, height);
		this.main.getViewport().update(width, height);
		EnigmaGame.setScreenSize(width, height);
	}

	@Override
	public void dispose() {
		try {
			MAP_PATH = "";
			this.map.getGameMusic().dispose();
			this.main.dispose();
			this.hud.dispose();
			this.dnd.dispose();
		} catch (Exception e) {
			Logger.printError("GameScreen#dispose", "échec de la libération des ressources.");
		}
	}

	@Override
	public void show() {
		super.show();
		Gdx.gl20.glClearColor(0.20f, 0.20f, 0.20f, 1.0f);

		if (this.map != null) this.map.launchMusic();
	}

	@Override
	public void hide() {
		super.hide();

		if (this.map != null && this.map.getGameMusic().hasMusic()) {
			this.map.getGameMusic().stop();
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
	public AbstractMap getMap() {
		return this.map;
	}

	/**
	 * Retourne l'affichage de l'inventaire
	 * @return l'affichage de l'inventaire
	 */
	public InventoryDisplay getInventoryDisplay(){
		return inventoryDisplay;
	}

	/**
	 * Retourne le stage du drag and drop
	 * @return le stage du drag and drop
	 */
	public Stage getDnd() {
		return dnd;
	}

	public InventoryDisplay getPlayerInventory() {
		return playerInventory;
	}

	public void setPlayerInventory(InventoryDisplay playerInventory) {
		this.playerInventory = playerInventory;
	}
}
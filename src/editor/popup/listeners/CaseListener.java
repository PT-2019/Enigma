package editor.popup.listeners;

import api.utils.Observer;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import common.enigmas.Enigma;
import common.entities.GameObject;
import common.map.MapTestScreenCell;
import data.EditorState;
import data.NeedToBeTranslated;
import data.TypeEntity;
import editor.EditorLauncher;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.CaseView;
import editor.popup.cases.SpecialPopUp;
import editor.popup.cases.listeners.CasePopWindowListener;
import game.EnigmaGame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Cette classe permet d'écouter les cliques de la souris sur les cases
 * de la map.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 3.0
 */
public class CaseListener extends ClickListener {

	private static CaseListener caseListener = null;

	/**
	 * PopUp qui représente graphiquement toutes les informations de la case cliquée
	 */
	private CasePopUp popUp;

	/**
	 * boolean qui dit si nous sommes entrain de créer une énigme ou pas.
	 */
	private boolean enigmaCreate;

	/**
	 * Second popup
	 */
	private SpecialPopUp pop;

	private static Observer available = null;

	public CaseListener(CasePopUp pop) {
		super(Input.Buttons.LEFT);
		CaseListener.caseListener = this;
		this.popUp = pop;
		this.enigmaCreate = false;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (EditorLauncher.containsState(EditorState.ERASE)) {//clic avec gomme
			if(!EditorLauncher.containsState(EditorState.ZOOM)) {//pas en zoom
				CaseView actor = (CaseView) event.getTarget();
				MapTestScreenCell cell = getRelevantEntity(actor);
				if (cell != null && cell.getEntity() != null) cell.removeEntity();
			} else {
				EnigmaGame.getCurrentScreen().showToast(NeedToBeTranslated.ERASE_FAILED_ZOOM);
			}
		} else
		//on ne peut cliquer que si l'état est normal
		if (EditorLauncher.containsState(EditorState.ZOOM, true)
		|| EditorLauncher.containsState(EditorState.NORMAL)) {
			if (this.enigmaCreate) { //une popup est ouverte
				//on doit être dans un menu qui nécessite une deuxième popup
				if (CaseListener.getAvailable() != null) {
					//deuxième fenêtre ok on ne quitte pas
					if (this.pop != null) {
						this.pop.setAlwaysOnTop(true);
						this.pop.revalidate();
						this.pop.setAlwaysOnTop(false);
						return;
					}
				} else {
					if (this.pop == null) {
						//on met la window au premier plan
						this.popUp.setAlwaysOnTop(true);
						this.popUp.revalidate();
						this.popUp.setAlwaysOnTop(false);
					} else {
						this.pop.setAlwaysOnTop(true);
						this.pop.revalidate();
						this.pop.setAlwaysOnTop(false);
					}
					//équivalent d'un focus mais en mode bizarre
					return;
				}
			}

			CaseView actor = (CaseView) event.getTarget();
			MapTestScreenCell cell = getRelevantEntity(actor);

			if (this.enigmaCreate) {
				this.pop = new SpecialPopUp(this.popUp.getComponent(), this.popUp.getTileMap(), this.popUp, this);
				this.pop.setCell(cell);
				this.pop.display();
				this.pop.setVisible(true);
				this.pop.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						pop = null;
					}
				});
			} else {
				this.popUp.setCell(cell);
				Group g = actor.getParent();
				//on récupère tout les actors
				SnapshotArray<Actor> arr = g.getChildren();

				//on passe dans le mode enigmaCreate
				for (Actor act : arr) {
					Array<EventListener> listeners = act.getListeners();
					((CaseListener) listeners.get(0)).setEnigmaCreate(true);
				}
				this.popUp.addWindowListener(new CasePopWindowListener(this.popUp, g));
				this.popUp.display();
			}
		}
	}

	/**
	 * Booléen si on peut ouvrir autre fenêtre
	 * @param b true pour si on peut ouvrir autre fenêtre sinon false
	 */
	public void setEnigmaCreate(boolean b) {
		this.enigmaCreate = b;
	}

	public void setPop(SpecialPopUp pop) {
		this.pop = pop;
	}

	/**
	 * Retourne la cellule contenant l'entité la plus intéressante
	 * @param actor case view
	 * @return la cellule contenant l'entité la plus intéressante
	 */
	private MapTestScreenCell getRelevantEntity(CaseView actor) {
		MapTestScreenCell cell = actor.getCell();

		//on parcours tout les layers à la recherche d'une entité
		// pour afficher le panneau avec l'entité en premier
		GameObject entity = actor.getCell().getEntity();
		if (entity == null) {
			TiledMap map = this.popUp.getTileMap();
			MapLayers layers = map.getLayers();
			MapTestScreenCell tmp;

			for (int i = 0; i < 4; i++) {
				TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
				tmp = (MapTestScreenCell) layer.getCell(cell.getIndex() % layer.getWidth(),
						cell.getIndex() / layer.getWidth());
				if (tmp.getEntity() != null) {
					cell = tmp;
				}
			}
			//si ya déjà une entité mais c'est une pièce
		} else if (entity.getImplements().get(TypeEntity.CONTAINER_MANAGER)) {//on regarde si on a quelque chose de mieux
			TiledMap map = this.popUp.getTileMap();
			MapLayers layers = map.getLayers();
			MapTestScreenCell tmp;

			for (int i = 0; i < 4; i++) {
				TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
				tmp = (MapTestScreenCell) layer.getCell(cell.getIndex() % layer.getWidth(),
						cell.getIndex() / layer.getWidth());
				if (tmp.getEntity() != null && !tmp.getEntity().getImplements().get(TypeEntity.CONTAINER_MANAGER)) {
					cell = tmp;
				}
			}
		}

		return cell;
	}

	/**
	 * Retourne l'écran monopolisant le 2ème popup ou null si aucun
	 * @return l'écran monopolisant le 2ème popup ou null si aucun
	 */
	public static Observer getAvailable() {
		return CaseListener.available;
	}

	/**
	 * Définit l'écran monopolisant le 2ème popup ou null si aucun
	 * @param available l'écran monopolisant le 2ème popup ou null si aucun
	 */
	public static void setAvailable(Observer available) {
		CaseListener.available = available;
	}

	/**
	 * Retourne true si l'écran monopolisant le 2ème popup est cette écran
	 * @param observer écran
	 * @return true si l'écran monopolisant le 2ème popup est cette écran
	 */
	public static boolean isNotAvailable(Observer observer) {
		if (CaseListener.available == null) return true;
		return !CaseListener.available.equals(observer);
	}

	/**
	 * Ferme tous les popups
	 */
	public static void close(){
		CasePopUp popUp = CaseListener.caseListener.popUp;
		if(popUp != null){
			popUp.clean();
			popUp.setVisible(false);
			CaseListener.caseListener.enigmaCreate = false;
		}
	}
}

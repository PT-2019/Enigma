package editor.view.listeners;

import api.entity.GameObject;
import api.enums.EditorState;
import api.enums.TypeEntity;
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
import editor.enigma.create.enigma.EnigmaView;
import editor.view.cases.CasePopUp;
import editor.view.cases.CaseView;
import editor.view.cases.SpecialPopUp;
import editor.view.cases.listeners.CasePopWindowListener;
import game.entity.map.MapTestScreenCell;
import game.screen.TestScreen;

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
 *
 * @version 5.0
 * @since 3.0
 */
public class CaseListener extends ClickListener {

	/**
	 * PopUp qui représente graphiquement toutes les informations de la case cliquée
	 */
	private CasePopUp popUp;

	/**
	 * boolean qui dit si nous sommes entrain de créer une enigme ou pas.
	 */
	private boolean enigmacreate;
	private SpecialPopUp pop;

	public CaseListener(CasePopUp pop) {
		super(Input.Buttons.LEFT);
		popUp = pop;
		enigmacreate = false;
		pop = null;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		//on ne peut cliquer que si l'état est normal
		if(TestScreen.isState(EditorState.NORMAL)) {
			if(enigmacreate){ //une popup est ouverte
				//on doit être dans un menu qui nécessite une deuxième popup
				if(EnigmaView.getAvailable() != null){
					//deuxième fenêtre ok on ne quitte pas
					System.out.println(pop);
					if(pop != null){
						pop.setAlwaysOnTop(true);
						pop.revalidate();
						pop.setAlwaysOnTop(false);
						return;
					}
				} else {
					if(pop == null) {
						//on met la window au premier plan
						popUp.setAlwaysOnTop(true);
						popUp.revalidate();
						popUp.setAlwaysOnTop(false);
					} else {
						pop.setAlwaysOnTop(true);
						pop.revalidate();
						pop.setAlwaysOnTop(false);
					}
					//équivalent d'un focus mais en mode bizarre
					return;
				}
			}

			CaseView actor = (CaseView) event.getTarget();
			MapTestScreenCell cell = getRevelantEntity(actor);

			if (enigmacreate) {
				pop = new SpecialPopUp(popUp.getComponent(), popUp.getTileMap(), popUp, this);
				pop.setCell(cell);
				pop.display();
				pop.setVisible(true);
				pop.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						pop = null;
					}
				});
			} else {
				popUp.setCell(cell);
				Group g = actor.getParent();
				//on récupère tout les actors
				SnapshotArray<Actor> arr = g.getChildren();

				//on passe dans le mode enigmacreate
				for (Actor act : arr) {
					Array<EventListener> listeners = act.getListeners();
					((CaseListener) listeners.get(0)).setEnigmacreate(true);
				}
				popUp.addWindowListener(new CasePopWindowListener(popUp, g));
				popUp.display();
			}
		} else if(TestScreen.isState(EditorState.ERASE)){
			CaseView actor = (CaseView) event.getTarget();
			MapTestScreenCell cell = getRevelantEntity(actor);
			if(cell != null && cell.getEntity() != null) cell.removeEntity();
		}
	}

	public void setEnigmacreate(boolean b) {
		this.enigmacreate = b;
	}

	public void setPop(SpecialPopUp pop) {
		this.pop = pop;
	}

	private MapTestScreenCell getRevelantEntity(CaseView actor) {
		MapTestScreenCell cell = actor.getCell();

		//on parcours tout les layers à la recherche d'une entité
		// pour afficher le panneau avec l'entité en premier
		GameObject entity = actor.getCell().getEntity();
		if (entity == null) {
			TiledMap map = popUp.getTileMap();
			MapLayers layers = map.getLayers();
			MapTestScreenCell tmp;

			for (int i = 0; i < 4; i++) {
				TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
				tmp = (MapTestScreenCell) layer.getCell(cell.getIndex() % layer.getWidth(), cell.getIndex() / layer.getWidth());
				if (tmp.getEntity() != null) {
					cell = tmp;
				}
			}
			//si ya déjà une entité mais c'est une pièce
		} else if(entity.getImplements().get(TypeEntity.CONTAINER_MANAGER)) {//on regarde si on a quelque chose de mieux
			TiledMap map = popUp.getTileMap();
			MapLayers layers = map.getLayers();
			MapTestScreenCell tmp;

			for (int i = 0; i < 4; i++) {
				TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
				tmp = (MapTestScreenCell) layer.getCell(cell.getIndex() % layer.getWidth(), cell.getIndex() / layer.getWidth());
				if (tmp.getEntity() != null && !tmp.getEntity().getImplements().get(TypeEntity.CONTAINER_MANAGER)) {
					cell = tmp;
				}
			}
		}

		return cell;
	}
}

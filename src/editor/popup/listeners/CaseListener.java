package editor.popup.listeners;

import api.utils.Observer;
import api.utils.WindowClosing;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import common.map.MapTestScreenCell;
import common.utils.EnigmaUtility;
import data.EditorState;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.CaseView;
import editor.popup.cases.SpecialPopUp;
import editor.popup.cases.listeners.CasePopWindowListener;
import game.EnigmaGame;

/**
 * Cette classe permet d'écouter les cliques de la souris sur les cases
 * de la map.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 3.0
 */
public class CaseListener extends ClickListener {

	private static CaseListener caseListener = null;
	private static Observer available = null;
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

	public CaseListener(CasePopUp pop) {
		super(Input.Buttons.LEFT);
		CaseListener.caseListener = this;
		this.popUp = pop;
		this.enigmaCreate = false;
	}

	/**
	 * Retourne l'écran monopolisant le 2ème popup ou null si aucun
	 *
	 * @return l'écran monopolisant le 2ème popup ou null si aucun
	 * @since 5.0
	 */
	public static Observer getAvailable() {
		return CaseListener.available;
	}

	/**
	 * Définit l'écran monopolisant le 2ème popup ou null si aucun
	 *
	 * @param available l'écran monopolisant le 2ème popup ou null si aucun
	 * @since 5.0
	 */
	public static void setAvailable(Observer available) {
		CaseListener.available = available;
	}

	/**
	 * Retourne true si l'écran monopolisant le 2ème popup est cette écran
	 *
	 * @param observer écran
	 * @return true si l'écran monopolisant le 2ème popup est cette écran
	 * @since 5.0
	 */
	public static boolean isNotAvailable(Observer observer) {
		if (CaseListener.available == null) return true;
		return !CaseListener.available.equals(observer);
	}

	/**
	 * Ferme tous les popups
	 *
	 * @since 6.0
	 */
	public static void close() {
		close(ClosePopup.ALL);
	}

	/**
	 * Ferme tous les popups
	 *
	 * @since 6.1
	 */
	public static void close(ClosePopup closePopup) {
		if (CaseListener.caseListener == null) return;

		boolean all = closePopup.equals(ClosePopup.ALL);

		//popup principal
		if (all || closePopup.equals(ClosePopup.PRIMARY)) {
			CasePopUp popUp = CaseListener.caseListener.popUp;
			if (popUp != null) {
				popUp.clean();
				popUp.setVisible(false);
				CaseListener.caseListener.enigmaCreate = false;
			}
		}

		//popup secondaire
		if (all || closePopup.equals(ClosePopup.SECONDARY)) {
			SpecialPopUp pop = CaseListener.caseListener.pop;
			if (pop != null) {
				pop.dispose();
				CaseListener.caseListener.pop = null;
			}
		}
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (EditorLauncher.containsState(EditorState.ERASE)) {//clic avec gomme
			if (!EditorLauncher.containsState(EditorState.ZOOM))//pas en zoom
				this.erase(event); //supprime
			else //sinon affiche message échec
				EnigmaGame.getCurrentScreen().showToast(NeedToBeTranslated.ERASE_FAILED_ZOOM);
		} else
			//on ne peut cliquer que si l'état est normal
			if ((EditorLauncher.containsState(EditorState.ZOOM, true) //en mode (normal)+zoom
					|| EditorLauncher.containsState(EditorState.NORMAL)
					|| EditorLauncher.containsState(EditorState.SPECIAL_POPUP_ENABLED)
					|| EditorLauncher.containsState(EditorState.SPECIAL_POPUP_CONTENT)
			)) {
				this.open(event);
			}
	}

	/**
	 * Supprime l'entité où on a cliqué
	 *
	 * @param event événement du clic
	 * @since 6.1
	 */
	private void erase(InputEvent event) {
		CaseView actor = (CaseView) event.getTarget();
		MapTestScreenCell cell = EnigmaUtility.getRelevantEntity(actor.getCell(), this.popUp.getTileMap());
		if (cell != null && cell.getEntity() != null) {
			if (this.popUp != null && this.popUp.getCell() != null
					&& this.popUp.getCell().getEntity() != null) {
				//si supprime l'entité dans le popup
				if (this.popUp.getCell().getEntity().getID() == cell.getEntity().getID()) {
					close();
				}
			}
			String s = cell.removeEntity();
			if (s != null) {//error
				EnigmaGame.getCurrentScreen().showToast(s);
			}
		}
	}

	/**
	 * Ouvre un popup (spécial ou non) sur la case cliquée
	 *
	 * @param event événement du clic
	 * @since 6.1
	 */
	private void open(InputEvent event) {
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
				//sinon on continue
			} else {
				if (this.pop == null) { //pas de seconde fenêtre
					if (!this.popUp.isVisible()) //si la fenêtre est cachée, alors c'est pas la seule
						return;
					//sinon on ferme la popup pour en ouvrir une autre
					CaseListener.close();
				} else {
					//équivalent d'un focus mais en mode bizarre
					this.pop.setAlwaysOnTop(true);
					this.pop.revalidate();
					this.pop.setAlwaysOnTop(false);
					return;
				}
			}
		}

		CaseView actor = (CaseView) event.getTarget();
		MapTestScreenCell cell = EnigmaUtility.getRelevantEntity(actor.getCell(), this.popUp.getTileMap());

		//on peut ouvrir une deuxième popup
		if (this.enigmaCreate) {
			boolean use = EditorLauncher.containsState(EditorState.SPECIAL_POPUP_ENABLED);
			boolean content = EditorLauncher.containsState(EditorState.SPECIAL_POPUP_CONTENT);
			if (!use && !content) return; //pas de deuxième popup autorisé
			this.pop = new SpecialPopUp(this.popUp, use, content, this);
			this.pop.setCell(cell);
			this.pop.display();
			this.pop.setVisible(true);
			this.pop.addWindowListener((WindowClosing) e -> pop = null);
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

	/**
	 * Booléen si on peut ouvrir autre fenêtre
	 *
	 * @param b true pour si on peut ouvrir autre fenêtre sinon false
	 * @since 3.0
	 */
	public void setEnigmaCreate(boolean b) {
		this.enigmaCreate = b;
	}

	/**
	 * Définition du popup secondaire
	 *
	 * @param pop popup secondaire
	 * @since 5.0
	 */
	public void setPop(SpecialPopUp pop) {
		this.pop = pop;
	}



	/**
	 * Ferme un popup
	 *
	 * @since 6.1
	 */
	public enum ClosePopup {
		/**
		 * Popup principal
		 */
		PRIMARY,
		/**
		 * Popup secondaire
		 */
		SECONDARY,
		/**
		 * tous
		 */
		ALL,
	}
}

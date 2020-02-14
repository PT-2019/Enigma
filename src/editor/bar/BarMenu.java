package editor.bar;

import api.utils.Utility;
import com.badlogic.gdx.utils.Array;
import common.hud.EnigmaMenu;
import common.hud.EnigmaMenuBar;
import common.hud.EnigmaMenuItem;
import common.hud.EnigmaWindow;
import common.hud.ui.EnigmaMenuUI;
import common.language.GameFields;
import common.language.HUDFields;
import data.EnigmaScreens;
import data.config.EnigmaUIValues;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Barre du menu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.2
 * @since 3.0
 */
public class BarMenu extends EnigmaMenuBar {

	//3eme onglet
	private EnigmaMenu music = new EnigmaMenu(gl.get(GameFields.MUSIC));
	private EnigmaMenuItem chose = new EnigmaMenuItem(gl.get(HUDFields.CHOSE));
	this.chose.addActionListener(new ChooseListener(window));


	/**
	 * Sauvegarde des menus particuliers si on veut les cacher
	 *
	 * @since 6.2
	 */
	private ArrayList<String> lastStated;

	/**
	 * Crée une barre de menus
	 *
	 * @param window fenêtre
	 * @since 3.0
	 */
	public BarMenu(EnigmaWindow window) {
		//attribut
		this.lastStated = new ArrayList<>();
		//ui
		EnigmaMenuUI mui = new EnigmaMenuUI();
		mui.setPopupBackground(Color.WHITE);
		mui.setPopupBorderSize(1);
		mui.setShowedPopupBorders(EnigmaUIValues.ALL_BORDERS_SHOWED);

		for (EnigmaMenuBarMenus menuCategory : EnigmaMenuBarMenus.values()) {
			//crée le menu
			EnigmaMenu menu = new EnigmaMenu(menuCategory.name);
			//parcours des fils
			for (EnigmaMenuBarItems itemCategory : menuCategory.items) {
				//création
				EnigmaMenuItem item = new EnigmaMenuItem(itemCategory.name);
				//ajout listener
				if (itemCategory.run != null) {
					item.addActionListener((ActionListener) Utility.instance(itemCategory.run, window, item));
				}
				//définit l'état
				item.setEnabled(itemCategory.enabled);

				//ajout des fils
				menu.add(item);
			}
			//ajout de l'ui
			menu.setComponentUI(mui);
			//ajout à la barre
			this.add(menu);
		}
	}

	/**
	 * Active/Désactive tous les menus sauf exceptions
	 *
	 * @param enable     true pour activer sinon false pour désactiver
	 * @param exceptions nom des menus à ne pas désactiver
	 * @since 6.2
	 */
	public void enableAll(boolean enable, String... exceptions) {
		//Crée une liste comme ça c'est plus rapide
		ArrayList<String> exceptionsList = new ArrayList<>();
		if (exceptions != null && exceptions.length > 0) exceptionsList.addAll(Arrays.asList(exceptions));
		//parcours des grand menus
		for (Component c : new Array.ArrayIterator<>(this.getMenus())) {
			if (!(c instanceof EnigmaMenu)) continue;
			//parcours de leur contenu
			for (Component cItem : ((EnigmaMenu) c).getItems()) {
				if (!(cItem instanceof EnigmaMenuItem)) continue;
				EnigmaMenuItem item = (EnigmaMenuItem) cItem;
				//si je ne dois pas toucher à l'object
				String text = item.getText();
				boolean enabled = item.isEnabled();
				if (exceptionsList.contains(text)) continue;

				//si bouton activé, et que je veux l'activer
				//ou si bouton désactivé, et que je veux le désactiver
				if (enable == enabled) {
					lastStated.add(text);
				} else {
					//s'il était dans un état différent, alors je le rétablis pas
					if (lastStated.contains(text)) lastStated.remove(text);
					else item.setEnabled(enable);
				}
			}
		}
	}
}

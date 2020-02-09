package editor.bar;

import api.utils.Utility;
import com.badlogic.gdx.utils.Array;
import common.hud.EnigmaMenu;
import common.hud.EnigmaMenuBar;
import common.hud.EnigmaMenuItem;
import common.hud.EnigmaWindow;
import common.hud.ui.EnigmaMenuUI;
import data.config.EnigmaUIValues;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;

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

	/**
	 * Crée une barre de menus
	 * @param window fenêtre
	 * @since 3.0
	 */
	public BarMenu(EnigmaWindow window) {
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
	 * Retourne un item de la barre depuis son nom
	 * @param itemName un nom d'item
	 * @return l'item ou null
	 * @since 6.2
	 */
	public EnigmaMenuItem getItem(EnigmaMenuBarItems itemName){
		for (Component menu : new Array.ArrayIterator<>(this.getMenus())) {
			for (Object item: ((EnigmaMenu)menu).getItems()){
				if(!(item instanceof EnigmaMenuItem)) continue;
				if(itemName.name.equals(((EnigmaMenuItem)item).getText())) return (EnigmaMenuItem) item;
			}
		}
		return null;
	}
}

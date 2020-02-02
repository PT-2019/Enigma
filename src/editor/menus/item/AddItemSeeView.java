package editor.menus.item;

import common.utils.Logger;
import api.utils.Utility;
import common.entities.GameObject;
import common.entities.Item;
import common.hud.EnigmaPanel;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
import editor.menus.OptionRunnableFactory;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.EnumMap;

/**
 * Vue de l'item sélectionné
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0
 * @since 5.0
 */
public class AddItemSeeView extends AbstractSubPopUpView implements Drawable {

	private static final String TITLE = "Consulter les informations de l'objet";

	/**
	 * Les lanceurs des options
	 */
	private EnumMap<AvailablePopUpOption, AvailableOptionRunnable> runnables;

	/**
	 * Menu extra
	 */
	private EnigmaPanel extra;

	/**
	 * Vue de l'item sélectionné
	 * @param parent parent
	 */
	AddItemSeeView(AbstractPopUpView parent) {
		super(TITLE, parent, true);

		this.runnables = new EnumMap<>(AvailablePopUpOption.class);
		for (Class<?> c : OptionRunnableFactory.getAll()) {
			AvailableOptionRunnable runnable = (AvailableOptionRunnable) Utility.instance(c, parent.getPopUp());
			this.runnables.put(runnable.getOption(), runnable);
		}
	}

	void setChecked(Item checked) {
		Logger.printDebug("AddContainer#See", checked + " {state=checked}");

		this.content.removeAll();
		this.content.setLayout(new GridLayout(2, 1));
		this.extra = new EnigmaPanel();
		this.extra.setLayout(new GridBagLayout());

		for (AvailablePopUpOption option : AvailablePopUpOption.values()) {
			if (AvailablePopUpOption.isAvailable(option, checked)) {
				AvailableOptionRunnable runnable = this.runnables.get(option);
				if (runnable != null) {
					runnable.run(this.parent, this, checked);
				}
			}
		}

		EnigmaPanel nom = new EnigmaPanel(new GridBagLayout());
		this.infoLabel.setText(checked.getReadableName());
		nom.add(this.infoLabel);

		this.content.add(nom);
		this.content.add(this.extra);
		this.content.revalidate();
	}

	@Override
	public Container getDrawable() {
		return this.extra;
	}

	@Override
	public void invalidateDrawable() {
		//close and invalidate list
		this.parent.getCardLayout().show(this.parent.getPanel(), AddItemView.MENU);
		this.parent.invalidateDrawable();
	}

	@Override
	public void clean() {
	}

	@Override
	public void initComponent() {
	}
}

package editor.view.listeners.available.view.item;

import api.entity.GameObject;
import api.entity.Item;
import api.enums.AvailablePopUpOption;
import api.utils.Utility;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.view.listeners.AvailableOptionRunnable;
import editor.view.listeners.available.view.AbstractPopUpView;
import editor.view.listeners.available.view.AbstractSubPopUpView;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.EnumMap;

public class AddItemSeeView extends AbstractSubPopUpView {

	private static final String TITLE = "Consulter les informations de l'objet";
	private static final String SUMBIT = "Supprimer";

	private EnumMap<AvailablePopUpOption, AvailableOptionRunnable> runnables;

	public AddItemSeeView(AbstractPopUpView parent) {
		super(TITLE, parent, true);

		this.runnables = new EnumMap<>(AvailablePopUpOption.class);
		for (Class<?> c : AvailableOptionRunnable.classes) {
			AvailableOptionRunnable runnable = (AvailableOptionRunnable) Utility.instance(c, parent.getPopUp());
			this.runnables.put(runnable.getOption(), runnable);
		}
	}

	@Override
	public void update(GameObject object) {

	}

	@Override
	public void clean() {

	}

	@Override
	public void onHide() {
	}

	@Override
	public void onShow() {

	}

	@Override
	public void initComponent() {
	}

	public void setChecked(Item checked){
		Utility.printDebug("AddContainer#See",checked+" {state=checked}");

		content.removeAll();
		content.setLayout(new GridLayout(2, 1));
		EnigmaPanel extra = new EnigmaPanel();
		extra.setLayout(new GridBagLayout());

		for (AvailablePopUpOption option : AvailablePopUpOption.values()) {
			if (AvailablePopUpOption.isAvailable(option, checked )) {
				AvailableOptionRunnable runnable = runnables.get(option);
				if (runnable != null){
					runnable.run(parent, extra, checked);
				}
			}
		}

		EnigmaPanel nom = new EnigmaPanel(new GridBagLayout());
		this.infoLabel.setText(checked.getReadableName());
		nom.add(this.infoLabel);

		content.add(nom);
		content.add(extra);
		content.revalidate();
	}

	public EnigmaLabel getInfoLabel() {
		return this.infoLabel;
	}

}

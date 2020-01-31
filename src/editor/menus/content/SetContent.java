package editor.menus.content;

import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.enimas.listeners.EnigmaWindowListener;
import editor.popup.cases.CasePopUp;
import general.entities.GameObject;
import general.hud.EnigmaButton;
import general.hud.EnigmaPanel;

/**
 * Permet de changer la propriété d'accès (collision)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class SetContent implements AvailableOptionRunnable {

	private static final String DIALOG = "Définir le contenu";
	private final CasePopUp parent;
	/**
	 * Affiche la gestion de contenu pour les entités.
	 *
	 * @see general.entities.types.Content
	 */
	private EnigmaButton contentButton;

	public SetContent(CasePopUp parent) {
		this.parent = parent;
		this.contentButton = new EnigmaButton(DIALOG);
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.SET_CONTENT;
	}

	@Override
	public void run() {
		this.parent.getPanel().add(this.contentButton);
		this.contentButton.addActionListener((e) -> {
			this.parent.setVisible(false);
			AddContentView addItemView = new AddContentView(this.parent, null);
			addItemView.setVisible(true);
			addItemView.addWindowListener(new EnigmaWindowListener(this.parent));
		});
	}

	@Override
	public void run(AbstractPopUpView view, EnigmaPanel panel, GameObject object) {
		panel.add(this.contentButton);
		this.contentButton.addActionListener((e) -> {
			this.parent.setVisible(false);
			AddContentView addItemView = new AddContentView(this.parent, object);
			addItemView.setVisible(true);
			addItemView.addWindowListener(new EnigmaWindowListener(this.parent));
		});
	}
}

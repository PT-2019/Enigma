package editor.view.listeners.available;

import api.entity.GameObject;
import api.enums.AvailablePopUpOption;
import editor.enigma.create.listeners.EnigmaWindowListener;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaPanel;
import editor.view.cases.CasePopUp;
import editor.view.listeners.AvailableOptionRunnable;
import editor.view.listeners.available.view.AbstractPopUpView;
import editor.view.listeners.available.view.content.AddContentView;

/**
 * Permet de changer la propriété d'accès (collision)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class SetContent implements AvailableOptionRunnable {

	private static final String DIALOG = "Définir le contenu";

	/**
	 * Affiche la gestion de contenu pour les entités.
	 * @see api.entity.types.Content
	 */
	private EnigmaButton contentButton;

	private final CasePopUp parent;

	public SetContent(CasePopUp parent){
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

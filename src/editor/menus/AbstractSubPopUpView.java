package editor.menus;

import api.ui.base.ResetComponent;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Une vue du cardLayout de la vue d'un popup
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 27/01/2020
 * @since 5.0 27/01/2020
 */
public abstract class AbstractSubPopUpView extends EnigmaPanel implements ResetComponent {

	protected final EnigmaLabel infoLabel;
	protected final EnigmaPanel footer;
	protected final EnigmaPanel content;
	protected final AbstractPopUpView parent;
	protected final MenuPopUp menuPopUp;

	/**
	 * Une vue du cardLayout de la vue d'un popup
	 *
	 * @param title  titre
	 * @param parent parent
	 */
	public AbstractSubPopUpView(String title, AbstractPopUpView parent) {
		this(title, parent, true);
	}

	/**
	 * Une vue du cardLayout de la vue d'un popup
	 *
	 * @param title    titre
	 * @param parent   parent
	 * @param showBack afficher bouton retour
	 */
	public AbstractSubPopUpView(String title, AbstractPopUpView parent, boolean showBack) {
		super();
		this.parent = parent;
		this.content = new EnigmaPanel(new BorderLayout());

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent evt) {
				onShow();
			}

			@Override
			public void componentShown(ComponentEvent evt) {
				onHide();
			}
		});

		this.footer = new EnigmaPanel();
		this.footer.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		this.infoLabel = new EnigmaLabel("");
		this.infoLabel.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 2;
		this.footer.add(this.infoLabel, gbc);

		this.setLayout(new BorderLayout());
		this.menuPopUp = new MenuPopUp(title, "", parent, this, showBack);
		this.add(this.menuPopUp, BorderLayout.NORTH);
		this.add(this.content, BorderLayout.CENTER);
		this.add(this.footer, BorderLayout.SOUTH);
	}

	/**
	 * Affichage de la carte
	 */
	public void onShow() {
	}

	/**
	 * Moment carte n'est plus affichée
	 */
	public void onHide() {
	}
}

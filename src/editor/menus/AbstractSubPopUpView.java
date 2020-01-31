package editor.menus;

import api.hud.base.ResetComponent;
import api.utils.Observer;
import general.entities.GameObject;
import general.hud.EnigmaLabel;
import general.hud.EnigmaPanel;

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
public abstract class AbstractSubPopUpView extends EnigmaPanel implements Observer<GameObject>, ResetComponent {

	protected final EnigmaLabel infoLabel;
	protected final EnigmaPanel footer;
	protected final EnigmaPanel content;
	protected final AbstractPopUpView parent;

	public AbstractSubPopUpView(String title, AbstractPopUpView parent) {
		this(title, parent, true);
	}

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

		footer = new EnigmaPanel();
		footer.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		infoLabel = new EnigmaLabel("");
		infoLabel.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 2;
		footer.add(infoLabel, gbc);

		this.setLayout(new BorderLayout());
		this.add(new MenuPopUp(title, "", parent, this, showBack), BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);
	}

	public abstract void onShow();

	public abstract void onHide();

	public EnigmaLabel getInfoLabel() {
		return this.infoLabel;
	}
}

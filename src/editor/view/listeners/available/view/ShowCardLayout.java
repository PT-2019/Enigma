package editor.view.listeners.available.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Affichage la card d'un parent depuis son nom
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 27/01/2020
 * @since 5.0 27/01/2020
 */
public class ShowCardLayout implements ActionListener {

	private final String cardName;
	private final AbstractPopUpView parent;

	public ShowCardLayout(String cardName, AbstractPopUpView parent){
		this.cardName = cardName;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		parent.getCardLayout().show(parent.getPanel(), cardName);
	}
}

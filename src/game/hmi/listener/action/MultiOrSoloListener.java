package game.hmi.listener.action;

import game.hmi.content.Create;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire des boutons "oui" et "non" pour indiquer si la partie créée sera multijoueurs
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class MultiOrSoloListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (Create.getInstance().getState() == Create.SOLO_STATE)
			Create.getInstance().refresh(Create.MULTI_STATE);
		else if (Create.getInstance().getState() == Create.MULTI_STATE)
			Create.getInstance().refresh(Create.SOLO_STATE);
	}
}

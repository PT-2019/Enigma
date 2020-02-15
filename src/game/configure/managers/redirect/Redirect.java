package game.configure.managers.redirect;

import game.configure.LaunchGameDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gère l'action de rediriger vers un autre affichage
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 * @deprecated
 */
@Deprecated
public class Redirect implements ActionListener {
	private String redirect;

	public Redirect(String redirect) {
		this.redirect = redirect;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		LaunchGameDisplay.getInstance().showDisplay(this.redirect);
	}
}

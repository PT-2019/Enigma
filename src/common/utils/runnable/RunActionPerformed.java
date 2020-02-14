package common.utils.runnable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lance la méthode actionPerformed sur un bouton
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class RunActionPerformed implements Runnable {

	private final ActionListener actionListener;
	private final Object context;

	/**
	 * Lance la méthode actionPerformed sur un bouton
	 *
	 * @param actionListener listener
	 * @param context        appelant/contexte d'appel
	 * @since 6.0
	 */
	public RunActionPerformed(ActionListener actionListener, Object context) {
		this.actionListener = actionListener;
		this.context = context;
	}

	@Override
	public void run() {
		//on met un actionEvent sans importance car pas utilisé dans les actionPerformed
		this.actionListener.actionPerformed(new ActionEvent(this.context, 0, ""));
	}
}

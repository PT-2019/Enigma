package editor.popup.listeners;

import common.hud.EnigmaWindow;
import data.NeedToBeTranslated;
import editor.bar.listeners.BrushListener;
import editor.bar.listeners.EraserListener;
import editor.bar.listeners.MoveListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener des changements d'états de l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 08/02/2020
 * @since 6.0 08/02/2020
 */
public class EnigmaStateListener implements ActionListener {

	private final EraserListener eraserListener;
	private final BrushListener brushListener;
	private final MoveListener moveListener;

	public EnigmaStateListener(EnigmaWindow window) {
		this.eraserListener = new EraserListener(window, null);
		this.brushListener = new BrushListener(window, null);
		this.moveListener = new MoveListener(window, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case NeedToBeTranslated.BRUSH:
				this.brushListener.actionPerformed(e);//lance listener pinceau
				break;

			case NeedToBeTranslated.ERASER:
				this.eraserListener.actionPerformed(e);//lance listener gomme
				break;

			case NeedToBeTranslated.MOVE:
				this.moveListener.actionPerformed(e);//lance listener déplacement
				break;
		}
	}
}

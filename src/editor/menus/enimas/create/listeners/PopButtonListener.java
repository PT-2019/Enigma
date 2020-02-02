package editor.menus.enimas.create.listeners;

import common.entities.GameObject;
import editor.menus.EnigmaWindowListener;
import editor.menus.enimas.ManageEnigmasView;
import editor.popup.cases.CasePopUp;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui affiche un enigmaView lorsqu'il y a un évènement
 */
public class PopButtonListener implements ActionListener {

	private final JDialog close;
	private final GameObject object;
	/**
	 * Fenetre du popUp de la case
	 */
	private CasePopUp frame;

	public PopButtonListener(CasePopUp frame) {
		this(frame, frame, null);
	}

	public PopButtonListener(CasePopUp frame, JDialog close, GameObject object) {
		this.frame = frame;
		this.close = close;
		this.object = object;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.close.setVisible(false);
		ManageEnigmasView manageEnigmasView = new ManageEnigmasView(this.frame, this.object);
		manageEnigmasView.setVisible(true);
		manageEnigmasView.addWindowListener(new EnigmaWindowListener(this.close));
	}
}

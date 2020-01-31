package editor.menus.enimas.listeners;

import editor.menus.enimas.view.EnigmaView;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.listeners.EntityChoseListener;
import general.map.MapTestScreenCell;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui affiche un enigmaView lorsqu'il y a un évènement
 */
public class PopButtonListener implements ActionListener {

	private final JDialog close;
	/**
	 * Fenetre du popUp de la case
	 */
	private CasePopUp frame;
	private MapTestScreenCell cell;
	private EntityChoseListener observer;

	public PopButtonListener(CasePopUp frame, MapTestScreenCell cell, EntityChoseListener observer) {
		this(frame, cell, observer, frame);
	}

	public PopButtonListener(CasePopUp frame, MapTestScreenCell cell, EntityChoseListener observer,
	                         JDialog close) {
		this.frame = frame;
		this.cell = cell;
		this.observer = observer;
		this.close = close;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		close.setVisible(false);
		EnigmaView enigmaView = new EnigmaView(frame, cell, observer);
		enigmaView.setVisible(true);
		enigmaView.addWindowListener(new EnigmaWindowListener(close));
	}
}

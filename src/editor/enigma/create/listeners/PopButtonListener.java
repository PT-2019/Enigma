package editor.enigma.create.listeners;


import editor.enigma.create.enigma.EnigmaView;
import editor.view.cases.CasePopUp;
import editor.view.cases.listeners.EntityChoseListener;
import editor.view.listeners.available.view.AbstractPopUpView;
import game.entity.map.MapTestScreenCell;

import javax.swing.JDialog;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui affiche un enigmaView lorsqu'il y a un évènement
 */
public class PopButtonListener implements ActionListener {

	/**
	 * Fenetre du popUp de la case
	 */
	private CasePopUp frame;

	private MapTestScreenCell cell;
	private EntityChoseListener observer;
	private final JDialog close;

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

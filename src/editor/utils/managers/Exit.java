package editor.utils.managers;

import editor.utils.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements ActionListener {

	public final static boolean CLOSE_WITHOUT_ASKING = false;
	public final static boolean ASK_BEFORE_CLOSING = true;

	private Window window;
	private boolean ask;

	public Exit(Window window){
		this.window = window;
		this.ask = false;
	}

	public Exit(Window window, boolean askBeforeClosing){
		this.window = window;
		this.ask = askBeforeClosing;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(this.ask) {
			if (JOptionPane.showConfirmDialog(this.window, "Voulez-vous vraiment nous quitter si tôt?", "Vous nous quittez?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
				this.window.dispose();
		}else this.window.dispose();
	}
}

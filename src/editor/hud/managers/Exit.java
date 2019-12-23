package editor.hud.managers;

import editor.hud.EnigmaOptionPane;
import editor.hud.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements ActionListener {

	public final static boolean CLOSE_WITHOUT_ASKING = false;
	public final static boolean ASK_BEFORE_CLOSING = true;

	private Window window;

	public Exit(Window window){
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(this.window.willAskBeforeClosing()) {
			if (EnigmaOptionPane.showConfirmDialog(this.window, "Voulez vous vraiment quittez?"))
				this.window.dispose();
		}else this.window.dispose();

	}
}

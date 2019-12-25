package editor.hud.managers;

import api.utils.LoadGameLibgdxApplication;
import api.utils.annotations.NeedPatch;
import editor.EditorLauncher;
import editor.hud.Window;
import game.EnigmaGameLauncher;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class Exit implements ActionListener {

	private Window window;

	public Exit(Window window) {
		this.window = window;
	}

	@Override
	@NeedPatch
	public void actionPerformed(ActionEvent actionEvent) {
		/*if(this.window.willAskBeforeClosing()) {
			if (EnigmaOptionPane.showConfirmDialog(this.window, "Voulez vous vraiment quittez?"))
				this.window.dispose();
		}else this.window.dispose();*/
		//EnigmaOptionPane o = new EnigmaOptionPane(this.window,"meee");
		//o.show();
		//o.waitForAnswer();

		int message = JOptionPane.showOptionDialog(window, "Voulez vous vraiment quittez?", "Quitter",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,
				0);
		if (message == 0) {
			//TODO: ceci est du bricolage qui ferme le jeu et l'éditeur!
			Window window = EditorLauncher.getInstance().getWindow();
			if (window != null)
				for (WindowListener l : window.getWindowListeners()) {
					if ((l instanceof LoadGameLibgdxApplication.CloseWindowLibgdxApplication)) {
						//fire close event
						l.windowClosing(null);
					}
				}

			Window w2 = EnigmaGameLauncher.getInstance().getWindow();
			if (w2 != null)
				for (WindowListener l : w2.getWindowListeners()) {
					if ((l instanceof LoadGameLibgdxApplication.CloseWindowLibgdxApplication)) {
						//fire close event
						l.windowClosing(null);
					}
				}
		}
	}
}

package editor.window;

import editor.utils.enigmaOptionPane.EnigmaOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements ActionListener {

	private Window window;

	public Exit(Window window){
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(!this.window.willAskBeforeClosing()) {
			if (EnigmaOptionPane.showConfirmDialog(this.window,new Dimension(300,200),"Voulez vous vraiment quittez?"))
				this.window.dispose();
		}else this.window.dispose();
	}
}

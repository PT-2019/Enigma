package editor.utils.managers;

import editor.utils.EnigmaOptionPane;
import editor.utils.EnigmaOptionPanem;
import editor.window.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements ActionListener {

	private Window window;

	public Exit(Window window){
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		/*if(this.window.willAskBeforeClosing()) {
			if (EnigmaOptionPane.showConfirmDialog(this.window, "Voulez vous vraiment quittez?"))
				this.window.dispose();
		}else this.window.dispose();*/
		//EnigmaOptionPane.showConfirmDialog(this.window,"djbgosjb");
		//if(EnigmaOptionPanem.oui(this.window,"oui").equals("oui")) System.out.println("caca");
		System.out.println("--"+ThreadO.oui());
	}
}

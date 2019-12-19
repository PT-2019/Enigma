package editor;

import editor.api.Application;
import editor.enigma.Enigma;
import editor.utils.*;
import editor.utils.Window;
import editor.utils.ui.EnigmaComboBoxUI;
import editor.utils.ui.EnigmaTextAreaUI;

import javax.swing.*;
import java.awt.*;

/**
 * Lanceur de l'éditeur d'escape game
 */
public class EditorLauncher implements Application {

	/** la fenêtre dans laquelle il est lancé */
	private Window window;

	/**
	 * Construit l'éditeur d'escape game
	 */
	public EditorLauncher() {
		this.window = new Window("Editeur de maps");
	}

	@Override
	public void start() {
		//on démarre l'application
		EnigmaComboBox b = new EnigmaComboBox();
		b.setComboBoxUI(new EnigmaComboBoxUI());
		b.addItem("oui");
		/*EnigmaPanel b = new EnigmaPanel();
		b.setBackground(Color.RED);
		EnigmaPopupMenu pp = new EnigmaPopupMenu();
		pp.add(new EnigmaMenuItem("kiki"));
		b.add(pp);
		b.setComponentPopupMenu(pp);*/
		this.window.add(b);
		this.window.setSize(Window.HALF_SCREEN_SIZE);
		this.window.setVisible(true);
	}
}
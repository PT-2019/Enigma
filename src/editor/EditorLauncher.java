package editor;

import editor.api.Application;
import editor.utils.EnigmaTextArea;
import editor.utils.Window;
import editor.utils.ui.EnigmaTextAreaUI;

import javax.swing.*;

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
		EnigmaTextArea ta = new EnigmaTextArea();
		EnigmaTextAreaUI ui = new EnigmaTextAreaUI();
		ta.setTextAreaUI(ui);
		this.window.add(ta);
		this.window.setVisible(true);
	}
}
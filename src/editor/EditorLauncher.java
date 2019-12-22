package editor;

import editor.api.Application;
import editor.utils.*;
import editor.utils.Window;

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
		EnigmaMenuItem item = new EnigmaMenuItem("oui");
		b.addItem(item);
		item = new EnigmaMenuItem("non");
		b.addItem(item);
		this.window.add(b);

		this.window.addWindowListener(new AppClosingManager());
		this.window.setSize(Window.HALF_SCREEN_SIZE);
		this.window.setVisible(true);
	}
}
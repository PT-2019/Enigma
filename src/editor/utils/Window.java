package editor.utils;

import javax.swing.JFrame;

//TODO: permettre le plein écran (avec une méthode)
//TODO: permettre l'ajout d'un fond d'écran

public class Window extends JFrame {

	public Window(int width, int height) {
		super();
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//définir la position de la fenêtre (centrée).
		//vérifier la taille (w, h) par rapport à l'écran
	}
}

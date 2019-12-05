package editor.utils;

import javax.swing.*;
import java.awt.*;

//TODO: permettre le plein écran (avec une méthode)
//TODO: permettre l'ajout d'un fond d'écran

public class Window extends JFrame {

	/**
	 * Initialise une fenêtre de la taille de l'écran
	 * @param title Titre de la fenêtre
	 */
	public Window(String title) {
		super(title);
		this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,0);
	}

	public Window(String title, int width, int height) {
		super(title);
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int)screen.getWidth() - width) / 2,((int)screen.getHeight() - height) / 2);
	}

	public void setBackground(ImageIcon image){}
}

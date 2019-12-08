package editor.utils;

import org.lwjgl.Sys;

import javax.swing.*;
import javax.swing.plaf.MenuBarUI;
import javax.swing.plaf.multi.MultiLabelUI;
import javax.swing.plaf.multi.MultiMenuBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//TODO: permettre le plein écran (avec une méthode)
//TODO: permettre l'ajout d'un fond d'écran

public class Window extends JFrame {

	public final static int FULL_SCREEN_SIZE = 0;
	public final static int HALF_SCREEN_SIZE = 1;

	public final static int NORTH = 2;
	public final static int EAST = 3;
	public final static int SOUTH = 4;
	public final static int WEST = 5;
	public final static int NORTH_EAST = 6;
	public final static int SOUTH_EAST = 7;
	public final static int SOUTH_WEST = 8;
	public final static int NORTH_WEST = 9;
	public final static int CENTER = 10;

	/**
	 * Initialise une fenêtre de la taille de l'écran
	 * @param title Titre de la fenêtre
	 */
	public Window(String title) {
		super(title);
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screen);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FULL_SCREEN_SIZE);
		this.setLocation(0,0);
		this.setMinimumSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
		this.setUndecorated(false);
		MenuBar menu = new MenuBar();
		menu.add(Box.createHorizontalGlue());
		MenuItem i = new MenuItem("Quit");
		Button b = new Button("quit");
		b.getUi().setAllBorders(BorderFactory.createEmptyBorder(), BorderFactory.createEmptyBorder(), BorderFactory.createEmptyBorder());
		b.addActionListener(new Exit(this, Exit.ASK_BEFORE_CLOSING));
		menu.add(b);
		this.setJMenuBar(menu);
	}

	public Window(String title, int width, int height) {
		super(title);
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,0);
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setMinimumSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
	}

	public void setSize(int size){
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		if(size == FULL_SCREEN_SIZE) this.setSize(screenSize);
		if(size == HALF_SCREEN_SIZE) this.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
	}

	public void setLocation(int location){

		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int sizeW = (int) screenSize.getWidth();
		int sizeH = (int) screenSize.getHeight();

		if(location == CENTER) this.setLocation((sizeW - this.getWidth()) / 2, (sizeH - this.getHeight()) / 2);
		if(location == NORTH_WEST) this.setLocation(0,0);
		if(location == NORTH) this.setLocation((sizeW - this.getWidth()) / 2, 0);
		if(location == NORTH_EAST) this.setLocation(sizeW - this.getWidth(), 0);
		if(location == EAST) this.setLocation(sizeW - this.getWidth(), (sizeH - this.getHeight()) / 2);
		if(location == SOUTH_EAST) this.setLocation(sizeW - this.getWidth(), sizeH - this.getHeight());
		if(location == SOUTH) this.setLocation((sizeW - this.getWidth()) / 2, sizeH - this.getHeight());
		if(location == SOUTH_WEST) this.setLocation(0, sizeH - this.getHeight());
		if(location == WEST) this.setLocation(0, (sizeH - this.getHeight()) / 2);
	}

	public void setBackground(ImageIcon image){}
}

class Exit implements ActionListener {

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

class Minimize implements ActionListener {

	private Window window;

	public Minimize(Window window){
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.window.setExtendedState(JFrame.ICONIFIED);
	}
}

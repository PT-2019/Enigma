package editor.utils;

import com.badlogic.gdx.Gdx;
import editor.utils.ui.ButtonUI;
import editor.utils.ui.MenuBarUI;
import editor.utils.ui.MenuItemUI;
import editor.utils.ui.MenuUI;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//TODO: permettre le plein écran (avec une méthode)
//TODO: permettre l'ajout d'un fond d'écran

public class Window extends JFrame {

	public final static int FULL_SCREEN_SIZE = 0;
	public final static int HALF_SCREEN_SIZE = 1;
	public final static int LAST_SCREEN_SIZE = 2;

	public final static int NORTH = 2;
	public final static int EAST = 3;
	public final static int SOUTH = 4;
	public final static int WEST = 5;
	public final static int NORTH_EAST = 6;
	public final static int SOUTH_EAST = 7;
	public final static int SOUTH_WEST = 8;
	public final static int NORTH_WEST = 9;
	public final static int CENTER = 10;

	private Dimension lastDimension;

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
		this.lastDimension = this.getDimension();
		this.setLocation(0,0);
		this.setMinimumSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
		WindowDrag drag = new WindowDrag(this);
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);

		this.setUndecorated(true);
		MenuBar bar = new MenuBar();
		MenuBarUI ui = new MenuBarUI();
		boolean[] r = new boolean[4];
		r[MenuBarUI.BOTTOM_BORDER] = MenuBarUI.SHOWED_BORDER;
		ui.setShowedBorders(r);
		ui.setBorderSize(2);
		ui.setBorder(Color.RED);
		bar.setMenuBarUI(ui);

		Button quit = new Button("X");
		ButtonUI bui = new ButtonUI();
		bui.setAllBorders(null,null,null);
		bui.setAllBackgrounds(ButtonUI.ENIGMA_BUTTON_BACKGROUND,Color.RED,Color.RED);
		bui.setAllForegrounds(ButtonUI.ENIGMA_BUTTON_FOREGROUND,ButtonUI.ENIGMA_BUTTON_FOREGROUND,ButtonUI.ENIGMA_BUTTON_FOREGROUND);
		bui.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		quit.setButtonUI(bui);
		quit.addActionListener(new Exit(this));

		Button minimize = new Button("-");
		bui.setAllBackgrounds(ButtonUI.ENIGMA_BUTTON_BACKGROUND,ButtonUI.ENIGMA_BUTTON_PRESSED_BACKGROUND,ButtonUI.ENIGMA_BUTTON_PRESSED_BACKGROUND);
		minimize.setButtonUI(bui);
		minimize.addActionListener(new Minimize(this));

		Button smaller = new Button("[]");
		smaller.setButtonUI(bui);
		smaller.addActionListener(new Smaller(this));

		Menu m = new Menu("Fichier");
		Menu m2 = new Menu("Ouvrir récent");
		MenuUI mui = new MenuUI();
		mui.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		m.setMenuUI(mui);
		m2.setMenuUI(mui);

		MenuItem mi = new MenuItem("Ouvrir");
		MenuItem mi2 = new MenuItem("dickpick.png");
		MenuItemUI miui = new MenuItemUI();
		miui.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		mi.setMenuItemUI(miui);
		mi2.setMenuItemUI(miui);

		m2.add(mi2);
		m.add(mi);
		m.add(m2);

		bar.add(m);
		bar.add(Box.createHorizontalGlue());
		bar.add(minimize);
		bar.add(smaller);
		bar.add(quit);
		this.setJMenuBar(bar);
	}

	public Window(String title, int width, int height) {
		super(title);
		this.setSize(width,height);
		this.lastDimension = this.getDimension();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,0);
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setMinimumSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
	}

	public void setSize(int size){
		Dimension currentScreenSize = this.getSize();
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		if(size == FULL_SCREEN_SIZE) this.setSize(screenSize.width,screenSize.height);
		if(size == HALF_SCREEN_SIZE) this.setSize(screenSize.width / 2, screenSize.height / 2);
		if(size == LAST_SCREEN_SIZE) this.setSize(this.lastDimension);
		this.lastDimension = currentScreenSize;
		this.setLocation(CENTER);
	}

	public Dimension getDimensionOf(int size){
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		if(size == FULL_SCREEN_SIZE) return new Dimension(screenSize.width, screenSize.height);
		if(size == HALF_SCREEN_SIZE) return new Dimension(screenSize.width / 2, screenSize.height / 2);
		if(size == LAST_SCREEN_SIZE) return this.lastDimension;
		return null;
	}

	public Dimension getDimension(){
		return new Dimension(this.getWidth(), this.getHeight());
	}

	public void setLocation(int location){

		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		int screenPosX = screenSize.x;
		int screenPosY = screenSize.y;
		int sizeW = screenSize.width;
		int sizeH = screenSize.height;

		/*GraphicsDevice[] allScreens = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		int myScreenIndex = -1;
		for (int i = 0; i < allScreens.length; i++) {
			if (allScreens[i].equals(this))
			{
				myScreenIndex = i;
				break;
			}
		}
		System.out.println("window is on screen" + myScreenIndex);*/

		if(location == CENTER) this.setLocation(((sizeW - this.getWidth()) / 2) + screenPosX, (sizeH - this.getHeight()) / 2);
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

class WindowDrag extends MouseAdapter  {

	private final Window window;
	private Point pressedCords;


	public WindowDrag(Window window) {
		this.window = window;
	}

	public void mouseReleased(MouseEvent e) {
		if(this.window.getLocationOnScreen().y <= 0) this.window.setSize(Window.FULL_SCREEN_SIZE);
	}

	public void mousePressed(MouseEvent e) {
		this.pressedCords = e.getPoint();
		if(this.window.getLocationOnScreen().y == 0 && this.window.getDimensionOf(Window.LAST_SCREEN_SIZE).equals(this.window.getDimensionOf(Window.FULL_SCREEN_SIZE)))
			this.window.setSize(Window.HALF_SCREEN_SIZE);
		else if(this.window.getLocationOnScreen().y == 0) this.window.setSize(Window.LAST_SCREEN_SIZE);
	}

	public void mouseDragged(MouseEvent e) {
		Point currentCords = e.getLocationOnScreen();
		this.window.setLocation(currentCords.x - this.pressedCords.x, currentCords.y - this.pressedCords.y);
	}
}

class Exit implements ActionListener {

	public final static boolean CLOSE_WITHOUT_ASKING = false;
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

class Smaller implements ActionListener {

	private Window window;

	public Smaller(Window window){
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(this.window.getDimensionOf(Window.FULL_SCREEN_SIZE).equals(this.window.getDimension()) && !this.window.getDimensionOf(Window.FULL_SCREEN_SIZE).equals(this.window.getDimensionOf(Window.LAST_SCREEN_SIZE))) this.window.setSize(Window.LAST_SCREEN_SIZE);
		else if(this.window.getDimensionOf(Window.FULL_SCREEN_SIZE).equals(this.window.getDimension())) this.window.setSize(Window.HALF_SCREEN_SIZE);
		else this.window.setSize(Window.FULL_SCREEN_SIZE);
	}
}

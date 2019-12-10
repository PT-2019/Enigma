package editor.utils;

import editor.utils.managers.*;
import editor.utils.ui.ButtonUI;
import editor.utils.ui.MenuBarUI;
import editor.utils.ui.MenuItemUI;
import editor.utils.ui.MenuUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

//TODO: permettre le plein écran (avec une méthode)
//TODO: permettre l'ajout d'un fond d'écran

public class Window extends JFrame {

	public final static int FULL_SCREEN_SIZE = 0;
	public final static int HALF_SCREEN_SIZE = 1;
	public final static int LAST_SCREEN_SIZE = 2;

	public final static int NORTH = 0;
	public final static int EAST = 1;
	public final static int SOUTH = 3;
	public final static int WEST = 4;
	public final static int NORTH_EAST = 5;
	public final static int SOUTH_EAST = 6;
	public final static int SOUTH_WEST = 7;
	public final static int NORTH_WEST = 8;
	public final static int CENTER = 9;
	public final static int LAST_LOCATION = 10;

	private final static int CONTENT = 0;
	private final static int RIGHT_RESIZER = 1;
	private final static int LEFT_RESIZER = 2;
	private final static int BOTTOM_RESIZER = 3;
	private final static int TOP_RESIZER = 4;
	private final static int TOP_LEFT_RESIZER = 5;
	private final static int TOP_RIGHT_RESIZER = 6;
	private final static int BOTTOM_LEFT_RESIZER = 7;
	private final static int BOTTOM_RIGHT_RESIZER = 8;

	private Dimension lastDimension;
	private Point lastLocation;
	private JPanel[] composites = new JPanel[9];
	private Color alertResized;

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
		this.lastDimension = this.getSize();
		this.lastLocation = this.getLocation();
		this.alertResized = Color.WHITE;
		this.setLocation(0,0);
		this.setMinimumSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
		for(int i = 0; i < this.composites.length; i++){
			this.composites[i] = new JPanel();
			this.composites[i].setOpaque(true);
		}

		Resize resize = new ResizeRight(this,this.composites[RIGHT_RESIZER],new Cursor(Cursor.E_RESIZE_CURSOR));

		resize = new ResizeLeft(this,this.composites[LEFT_RESIZER],new Cursor(Cursor.W_RESIZE_CURSOR));

		resize = new ResizeBottom(this,this.composites[BOTTOM_RESIZER],new Cursor(Cursor.S_RESIZE_CURSOR));
		this.composites[BOTTOM_RESIZER].setLayout(new BorderLayout());

		resize = new ResizeBottom(this,this.composites[BOTTOM_LEFT_RESIZER],new Cursor(Cursor.SW_RESIZE_CURSOR));
		resize = new ResizeLeft(this,this.composites[BOTTOM_LEFT_RESIZER],new Cursor(Cursor.SW_RESIZE_CURSOR));
		resize = new ResizeBottom(this,this.composites[BOTTOM_RIGHT_RESIZER],new Cursor(Cursor.SE_RESIZE_CURSOR));
		resize = new ResizeRight(this,this.composites[BOTTOM_RIGHT_RESIZER],new Cursor(Cursor.SE_RESIZE_CURSOR));

		this.composites[BOTTOM_RESIZER].add(this.composites[BOTTOM_LEFT_RESIZER],BorderLayout.WEST);
		this.composites[BOTTOM_RESIZER].add(this.composites[BOTTOM_RIGHT_RESIZER],BorderLayout.EAST);

		resize = new ResizeTop(this,this.composites[TOP_RESIZER],new Cursor(Cursor.N_RESIZE_CURSOR));
		this.composites[TOP_RESIZER].setLayout(new BorderLayout());

		resize = new ResizeTop(this,this.composites[TOP_LEFT_RESIZER],new Cursor(Cursor.NW_RESIZE_CURSOR));
		resize = new ResizeLeft(this,this.composites[TOP_LEFT_RESIZER],new Cursor(Cursor.NW_RESIZE_CURSOR));
		resize = new ResizeTop(this,this.composites[TOP_RIGHT_RESIZER],new Cursor(Cursor.NE_RESIZE_CURSOR));
		resize = new ResizeRight(this,this.composites[TOP_RIGHT_RESIZER],new Cursor(Cursor.NE_RESIZE_CURSOR));

		this.composites[TOP_RESIZER].add(this.composites[TOP_LEFT_RESIZER],BorderLayout.WEST);
		this.composites[TOP_RESIZER].add(this.composites[TOP_RIGHT_RESIZER],BorderLayout.EAST);

		this.add(this.composites[RIGHT_RESIZER],BorderLayout.EAST);
		this.add(this.composites[LEFT_RESIZER],BorderLayout.WEST);
		this.add(this.composites[BOTTOM_RESIZER],BorderLayout.SOUTH);
		this.add(this.composites[TOP_RESIZER],BorderLayout.NORTH);
		this.add(this.composites[CONTENT], BorderLayout.CENTER);

		this.setUndecorated(true);

		MenuBar bar = new MenuBar();
		Drag drag = new Drag(this);
		bar.addMouseListener(drag);
		bar.addMouseMotionListener(drag);
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
		this.lastDimension = this.getSize();
		this.lastLocation = this.getLocation();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,0);
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setMinimumSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
	}

	public void setAlertWillBeResizedFullScreenColor(Color alertColor){
		if(alertColor == null) throw new IllegalArgumentException("La couleur ne peut pas être null");
		this.alertResized = alertColor;
	}

	public JPanel getContentSpace(){
		return this.composites[CONTENT];
	}

	public void setWindowBackground(Color bgColor){
		for(JPanel p: this.composites) p.setBackground(bgColor);
	}

	public boolean isFullScreen(){
		return (this.getExtendedState() == JFrame.MAXIMIZED_BOTH);
	}

	public void setPreviousValues(){
		this.setSize(LAST_SCREEN_SIZE);
		this.setLocation(LAST_LOCATION);
	}

	public void setSize(int size){
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.setExtendedState(JFrame.NORMAL);
		switch (size){
			default:
				return;
			case FULL_SCREEN_SIZE:
				this.lastDimension = this.getSize();
				this.lastLocation = this.getLocation();
				this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				break;
			case HALF_SCREEN_SIZE:
				this.lastDimension = this.getSize();
				this.lastLocation = this.getLocation();
				this.setSize(screenSize.width / 2, screenSize.height / 2);
				break;
			case LAST_SCREEN_SIZE:
				this.setSize(this.lastDimension);
				break;
		}
		this.setLocation(CENTER);
	}

	public boolean compareWindowSizeWith(int size){
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		switch (size){
			default: return false;
			case FULL_SCREEN_SIZE:
				return this.isFullScreen();
			case HALF_SCREEN_SIZE:
				Dimension half = new Dimension(screenSize.width / 2, screenSize.height / 2);
				return (half.equals(this.getSize()));
			case LAST_SCREEN_SIZE:
				return (this.lastDimension.equals(this.getSize()));
		}
	}

	public boolean compareWindowSizeWith(int width, int height){
		Dimension dim = new Dimension(width, height);
		return (dim.equals(this.getSize()));
	}

	public void setPreviousX(int x){
		this.lastLocation = new Point(x, this.lastLocation.y);
	}

	public void setPreviousY(int y){
		this.lastLocation = new Point(this.lastLocation.x, y);
	}

	public void setPreviousLocation(int x, int y){
		this.lastLocation = new Point(x, y);
	}

	public void setPreviousLocation(Point location){
		this.lastLocation = location;
	}

	public void setPreviousWidth(int width){
		this.lastDimension = new Dimension(width, this.lastDimension.height);
	}

	public void setPreviousHeight(int height){
		this.lastDimension = new Dimension(this.lastDimension.width, height);
	}

	public void setPreviousSize(int width, int height){
		this.lastDimension = new Dimension(width, height);
	}

	public void setPreviousSize(Dimension size){
		this.lastDimension = size;
	}

	public void willBeResizedFullScreen(){
		this.composites[BOTTOM_LEFT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 3, 3, 0, this.alertResized));
		this.composites[BOTTOM_RIGHT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, this.alertResized));
	}

	public void wontBeResizedFullScreen(){
		this.composites[BOTTOM_LEFT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.composites[BOTTOM_RIGHT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
	}

	public void setLocation(int location){

		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		int sizeW = screenSize.width;
		int sizeH = screenSize.height;
		int windowPosX;
		int windowPosY;

		/*GraphicsDevice[] allScreens = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		int myScreenIndex = -1;
		for (int i = 0; i < allScreens.length; i++) {
			if (allScreens[i].equals(this.getGraphicsConfiguration().getDevice()))
			{
				myScreenIndex = i;
				break;
			}
		}
		System.out.println("window is on screen" + myScreenIndex);*/
		switch (location){
			default: return;
			case LAST_LOCATION:
				windowPosX = this.lastLocation.x;
				windowPosY = this.lastLocation.y;
				break;
			case CENTER:
				windowPosX = (sizeW - this.getWidth()) / 2;
				windowPosY = (sizeH - this.getHeight()) / 2;
				break;
			case NORTH_WEST:
				windowPosX = 0;
				windowPosY = 0;
				break;
			case NORTH:
				windowPosX = (sizeW - this.getWidth()) / 2;
				windowPosY = 0;
				break;
			case NORTH_EAST:
				windowPosX = sizeW - this.getWidth();
				windowPosY = 0;
				break;
			case EAST:
				windowPosX = sizeW - this.getWidth();
				windowPosY = (sizeH - this.getHeight()) / 2;
				break;
			case SOUTH_EAST:
				windowPosX = sizeW - this.getWidth();
				windowPosY = sizeH - this.getHeight();
				break;
			case SOUTH:
				windowPosX = (sizeW - this.getWidth()) / 2;
				windowPosY = sizeH - this.getHeight();
				break;
			case SOUTH_WEST:
				windowPosX = 0;
				windowPosY = sizeH - this.getHeight();
				break;
			case WEST:
				windowPosX = 0;
				windowPosY = (sizeH - this.getHeight()) / 2;
				break;
		}
		this.setLocation(windowPosX + screenSize.x, windowPosY + screenSize.y);
	}

	public void setBackground(ImageIcon image){}
}


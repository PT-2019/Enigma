package editor.utils;

import editor.utils.managers.*;
import editor.utils.ui.ButtonUI;
import editor.utils.ui.MenuBarUI;
import editor.utils.ui.MenuItemUI;
import editor.utils.ui.MenuUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
	private JPanel content;

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
		this.setLocation(0,0);
		this.setMinimumSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
		WindowDrag drag = new WindowDrag(this);
		this.addMouseListener(drag);

		this.addMouseMotionListener(new WindowResize(this));
		JPanel content = new JPanel();
		content.setBackground(Color.CYAN);
		content.setOpaque(true);

		JPanel resizeRight = new JPanel();
		resizeRight.setBackground(Color.RED);
		resizeRight.setOpaque(true);
		Resize resizeListener = new ResizeRight(resizeRight,new Cursor(Cursor.E_RESIZE_CURSOR));
		resizeRight.addMouseListener(resizeListener);
		resizeRight.addMouseMotionListener(resizeListener);

		JPanel resizeLeft = new JPanel();
		resizeLeft.setBackground(Color.GREEN);
		resizeLeft.setOpaque(true);
		resizeListener = new ResizeLeft(resizeLeft,new Cursor(Cursor.W_RESIZE_CURSOR));
		resizeLeft.addMouseListener(resizeListener);
		resizeLeft.addMouseMotionListener(resizeListener);

		JPanel resizeBottom = new JPanel();
		resizeBottom.setBackground(Color.ORANGE);
		resizeBottom.setOpaque(true);
		resizeListener = new ResizeBottom(resizeBottom,new Cursor(Cursor.S_RESIZE_CURSOR));
		resizeBottom.addMouseListener(resizeListener);
		resizeBottom.addMouseMotionListener(resizeListener);

		JPanel resizeTop = new JPanel();
		resizeTop.setBackground(Color.MAGENTA);
		resizeTop.setOpaque(true);
		resizeListener = new ResizeTop(resizeTop,new Cursor(Cursor.N_RESIZE_CURSOR));

		resizeTop.addMouseListener(resizeListener);
		resizeTop.addMouseMotionListener(resizeListener);
		this.add(resizeRight,BorderLayout.EAST);
		this.add(resizeLeft,BorderLayout.WEST);
		this.add(resizeBottom,BorderLayout.SOUTH);
		this.add(resizeTop,BorderLayout.NORTH);
		this.add(content,BorderLayout.CENTER);
		this.content = content;

		this.setUndecorated(true);
		MenuBar bar = new MenuBar();
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
		Timer t = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println(getSize());
			}
		});
		//t.setRepeats(true); t.start();
	}

	public Window(String title, int width, int height) {
		super(title);
		this.setSize(width,height);
		this.lastDimension = this.getSize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,0);
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setMinimumSize(new Dimension((int) screen.getWidth() / 4, (int) screen.getHeight() / 3));
	}

	public JPanel getContentSpace(){
		return this.content;
	}

	public boolean isFullScreen(){
		System.out.println(this.getSize().equals(this.getDimensionOf(FULL_SCREEN_SIZE))+" "+this.getSize()+" "+this.getDimensionOf(FULL_SCREEN_SIZE));
		return this.getSize().equals(this.getDimensionOf(FULL_SCREEN_SIZE));
	}

	public void setSize(int size){
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		switch (size){
			default:
				return;
			case FULL_SCREEN_SIZE:
				this.lastDimension = this.getSize();
				this.setSize(screenSize.width,screenSize.height);
				System.out.println("full"+this.getSize());
				break;
			case HALF_SCREEN_SIZE:
				this.lastDimension = this.getSize();
				this.setSize(screenSize.width / 2, screenSize.height / 2);
				System.out.println("half"+this.getSize());
				break;
			case LAST_SCREEN_SIZE:
				this.setSize(this.lastDimension);
				System.out.println("last"+this.getSize());
				break;
		}
		this.setLocation(CENTER);
	}

	public Dimension getDimensionOf(int size){
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		switch (size){
			default: return null;
			case FULL_SCREEN_SIZE:
				return new Dimension(screenSize.width, screenSize.height);
			case HALF_SCREEN_SIZE:
				return new Dimension(screenSize.width / 2, screenSize.height / 2);
			case LAST_SCREEN_SIZE:
				return this.lastDimension;
		}
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


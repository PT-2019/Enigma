package editor.utils;

import editor.utils.managers.*;
import editor.utils.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	private final static int RIGHT_RESIZER = 0;
	private final static int LEFT_RESIZER = 1;
	private final static int BOTTOM_RESIZER = 2;
	private final static int TOP_RESIZER = 3;
	private final static int TOP_LEFT_RESIZER = 4;
	private final static int TOP_RIGHT_RESIZER = 5;
	private final static int BOTTOM_LEFT_RESIZER = 6;
	private final static int BOTTOM_RIGHT_RESIZER = 7;

	private ResizeComponent[] resizers = new ResizeComponent[8];
	private EnigmaPanel content;
	private boolean resizable;

	/**
	 * Initialise une fenêtre de la taille de l'écran
	 * @param title Titre de la fenêtre
	 */
	public Window(String title) {
		super(title);
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.setSize(screenSize.getSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FULL_SCREEN_SIZE);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.setLocation(0,0);
		this.setUndecorated(true);

		EnigmaMenuBar windowActionBar = new EnigmaMenuBar();
		EnigmaMenuBarUI barUI = new EnigmaMenuBarUI();
		boolean[] borderShowed = new boolean[4];
		borderShowed[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		barUI.setShowedBorders(borderShowed);
		barUI.setBorderSize(2);
		barUI.setBorder(Color.RED);
		windowActionBar.setMenuBarUI(barUI);

		Drag drag = new Drag(this);
		windowActionBar.addMouseListener(drag);
		windowActionBar.addMouseMotionListener(drag);

		EnigmaButton quit = new EnigmaButton("X");
		EnigmaButtonUI buttonUI = new EnigmaButtonUI();
		buttonUI.setAllBorders(null,null,null);
		buttonUI.setAllBackgrounds(barUI.getBackground(),Color.RED,Color.RED);
		buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		quit.setButtonUI(buttonUI);
		quit.addActionListener(new Exit(this));

		EnigmaButton minimize = new EnigmaButton("-");
		buttonUI.setAllBackgrounds(barUI.getBackground(), EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND, EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND);
		minimize.setButtonUI(buttonUI);
		minimize.addActionListener(new Minimize(this));

		EnigmaButton smaller = new EnigmaButton("[]");
		smaller.setButtonUI(buttonUI);
		smaller.addActionListener(new Smaller(this));

		windowActionBar.add(Box.createHorizontalGlue());
		windowActionBar.add(minimize);
		windowActionBar.add(smaller);
		windowActionBar.add(quit);
		this.setJMenuBar(windowActionBar);

		this.content = new EnigmaPanel();
		this.resizers[RIGHT_RESIZER] = new ResizeComponent(new Cursor(Cursor.E_RESIZE_CURSOR));
		this.resizers[LEFT_RESIZER] = new ResizeComponent(new Cursor(Cursor.W_RESIZE_CURSOR));
		this.resizers[BOTTOM_RESIZER] = new ResizeComponent(new Cursor(Cursor.S_RESIZE_CURSOR));
		this.resizers[BOTTOM_LEFT_RESIZER] = new ResizeComponent(new Cursor(Cursor.SW_RESIZE_CURSOR));
		this.resizers[BOTTOM_RIGHT_RESIZER] = new ResizeComponent(new Cursor(Cursor.SE_RESIZE_CURSOR));
		this.resizers[TOP_RESIZER] = new ResizeComponent(new Cursor(Cursor.N_RESIZE_CURSOR));
		this.resizers[TOP_LEFT_RESIZER] = new ResizeComponent(new Cursor(Cursor.NW_RESIZE_CURSOR));
		this.resizers[TOP_RIGHT_RESIZER] = new ResizeComponent(new Cursor(Cursor.NE_RESIZE_CURSOR));

		this.resizers[BOTTOM_RESIZER].setLayout(new BorderLayout());
		this.resizers[TOP_RESIZER].setLayout(new BorderLayout());

		this.resizers[RIGHT_RESIZER].addResizer(new ResizeRight(this,this.resizers[RIGHT_RESIZER]));
		this.resizers[LEFT_RESIZER].addResizer(new ResizeLeft(this,this.resizers[LEFT_RESIZER]));
		this.resizers[BOTTOM_RESIZER].addResizer(new ResizeBottom(this,this.resizers[BOTTOM_RESIZER]));
		this.resizers[BOTTOM_LEFT_RESIZER].addResizer(new ResizeBottom(this,this.resizers[BOTTOM_LEFT_RESIZER]));
		this.resizers[BOTTOM_RIGHT_RESIZER].addResizer(new ResizeBottom(this,this.resizers[BOTTOM_RIGHT_RESIZER]));
		this.resizers[BOTTOM_LEFT_RESIZER].addResizer(new ResizeLeft(this,this.resizers[BOTTOM_LEFT_RESIZER]));
		this.resizers[BOTTOM_RIGHT_RESIZER].addResizer(new ResizeRight(this,this.resizers[BOTTOM_RIGHT_RESIZER]));
		this.resizers[TOP_RESIZER].addResizer(new ResizeTop(this,this.resizers[TOP_RESIZER]));
		this.resizers[TOP_LEFT_RESIZER].addResizer(new ResizeTop(this,this.resizers[TOP_LEFT_RESIZER]));
		this.resizers[TOP_RIGHT_RESIZER].addResizer(new ResizeTop(this,this.resizers[TOP_RIGHT_RESIZER]));
		this.resizers[TOP_LEFT_RESIZER].addResizer(new ResizeLeft(this,this.resizers[TOP_LEFT_RESIZER]));
		this.resizers[TOP_RIGHT_RESIZER].addResizer(new ResizeRight(this,this.resizers[TOP_RIGHT_RESIZER]));

		this.resizers[BOTTOM_RESIZER].add(this.resizers[BOTTOM_LEFT_RESIZER],BorderLayout.WEST);
		this.resizers[BOTTOM_RESIZER].add(this.resizers[BOTTOM_RIGHT_RESIZER],BorderLayout.EAST);
		this.resizers[TOP_RESIZER].add(this.resizers[TOP_LEFT_RESIZER],BorderLayout.WEST);
		this.resizers[TOP_RESIZER].add(this.resizers[TOP_RIGHT_RESIZER],BorderLayout.EAST);


		this.add(this.resizers[RIGHT_RESIZER],BorderLayout.EAST);
		this.add(this.resizers[LEFT_RESIZER],BorderLayout.WEST);
		this.add(this.resizers[BOTTOM_RESIZER],BorderLayout.SOUTH);
		this.add(this.resizers[TOP_RESIZER],BorderLayout.NORTH);
		this.add(this.content, BorderLayout.CENTER);
	}

	public Window(String title, int width, int height) {
		super(title);
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width,height);
		this.setLocation(CENTER);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.setUndecorated(true);

		EnigmaMenuBar windowActionBar = new EnigmaMenuBar();
		EnigmaMenuBarUI barUI = new EnigmaMenuBarUI();
		boolean[] borderShowed = new boolean[4];
		borderShowed[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		barUI.setShowedBorders(borderShowed);
		barUI.setBorderSize(2);
		barUI.setBorder(Color.RED);
		windowActionBar.setMenuBarUI(barUI);

		Drag drag = new Drag(this);
		windowActionBar.addMouseListener(drag);
		windowActionBar.addMouseMotionListener(drag);

		EnigmaButton quit = new EnigmaButton("X");
		EnigmaButtonUI buttonUI = new EnigmaButtonUI();
		buttonUI.setAllBorders(null,null,null);
		buttonUI.setAllBackgrounds(barUI.getBackground(),Color.RED,Color.RED);
		buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		quit.setButtonUI(buttonUI);
		quit.addActionListener(new Exit(this));

		EnigmaButton minimize = new EnigmaButton("-");
		buttonUI.setAllBackgrounds(barUI.getBackground(), EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND, EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BACKGROUND);
		minimize.setButtonUI(buttonUI);
		minimize.addActionListener(new Minimize(this));

		EnigmaButton smaller = new EnigmaButton("[]");
		smaller.setButtonUI(buttonUI);
		smaller.addActionListener(new Smaller(this));

		windowActionBar.add(Box.createHorizontalGlue());
		windowActionBar.add(minimize);
		windowActionBar.add(smaller);
		windowActionBar.add(quit);
		this.setJMenuBar(windowActionBar);

		this.content = new EnigmaPanel();
		this.resizers[RIGHT_RESIZER] = new ResizeComponent(new Cursor(Cursor.E_RESIZE_CURSOR));
		this.resizers[LEFT_RESIZER] = new ResizeComponent(new Cursor(Cursor.W_RESIZE_CURSOR));
		this.resizers[BOTTOM_RESIZER] = new ResizeComponent(new Cursor(Cursor.S_RESIZE_CURSOR));
		this.resizers[BOTTOM_LEFT_RESIZER] = new ResizeComponent(new Cursor(Cursor.SW_RESIZE_CURSOR));
		this.resizers[BOTTOM_RIGHT_RESIZER] = new ResizeComponent(new Cursor(Cursor.SE_RESIZE_CURSOR));
		this.resizers[TOP_RESIZER] = new ResizeComponent(new Cursor(Cursor.N_RESIZE_CURSOR));
		this.resizers[TOP_LEFT_RESIZER] = new ResizeComponent(new Cursor(Cursor.NW_RESIZE_CURSOR));
		this.resizers[TOP_RIGHT_RESIZER] = new ResizeComponent(new Cursor(Cursor.NE_RESIZE_CURSOR));

		this.resizers[BOTTOM_RESIZER].setLayout(new BorderLayout());
		this.resizers[TOP_RESIZER].setLayout(new BorderLayout());

		this.resizers[RIGHT_RESIZER].addResizer(new ResizeRight(this,this.resizers[RIGHT_RESIZER]));
		this.resizers[LEFT_RESIZER].addResizer(new ResizeLeft(this,this.resizers[LEFT_RESIZER]));
		this.resizers[BOTTOM_RESIZER].addResizer(new ResizeBottom(this,this.resizers[BOTTOM_RESIZER]));
		this.resizers[BOTTOM_LEFT_RESIZER].addResizer(new ResizeBottom(this,this.resizers[BOTTOM_LEFT_RESIZER]));
		this.resizers[BOTTOM_RIGHT_RESIZER].addResizer(new ResizeBottom(this,this.resizers[BOTTOM_RIGHT_RESIZER]));
		this.resizers[BOTTOM_LEFT_RESIZER].addResizer(new ResizeLeft(this,this.resizers[BOTTOM_LEFT_RESIZER]));
		this.resizers[BOTTOM_RIGHT_RESIZER].addResizer(new ResizeRight(this,this.resizers[BOTTOM_RIGHT_RESIZER]));
		this.resizers[TOP_RESIZER].addResizer(new ResizeTop(this,this.resizers[TOP_RESIZER]));
		this.resizers[TOP_LEFT_RESIZER].addResizer(new ResizeTop(this,this.resizers[TOP_LEFT_RESIZER]));
		this.resizers[TOP_RIGHT_RESIZER].addResizer(new ResizeTop(this,this.resizers[TOP_RIGHT_RESIZER]));
		this.resizers[TOP_LEFT_RESIZER].addResizer(new ResizeLeft(this,this.resizers[TOP_LEFT_RESIZER]));
		this.resizers[TOP_RIGHT_RESIZER].addResizer(new ResizeRight(this,this.resizers[TOP_RIGHT_RESIZER]));

		this.resizers[BOTTOM_RESIZER].add(this.resizers[BOTTOM_LEFT_RESIZER],BorderLayout.WEST);
		this.resizers[BOTTOM_RESIZER].add(this.resizers[BOTTOM_RIGHT_RESIZER],BorderLayout.EAST);
		this.resizers[TOP_RESIZER].add(this.resizers[TOP_LEFT_RESIZER],BorderLayout.WEST);
		this.resizers[TOP_RESIZER].add(this.resizers[TOP_RIGHT_RESIZER],BorderLayout.EAST);


		this.add(this.resizers[RIGHT_RESIZER],BorderLayout.EAST);
		this.add(this.resizers[LEFT_RESIZER],BorderLayout.WEST);
		this.add(this.resizers[BOTTOM_RESIZER],BorderLayout.SOUTH);
		this.add(this.resizers[TOP_RESIZER],BorderLayout.NORTH);
		this.add(this.content, BorderLayout.CENTER);
	}

	public EnigmaPanel getContentSpace(){
		return this.content;
	}

	public void setWindowBackground(Color bgColor){
		for(ResizeComponent r: this.resizers) r.setBackground(bgColor);
		this.content.setBackground(bgColor);
	}

	public boolean isFullScreen(){
		return (this.getExtendedState() == JFrame.MAXIMIZED_BOTH);
	}

	public boolean isResizable(){
		return this.resizable;
	}

	@Override
	public void setResizable(boolean resizable) {
		if(resizable){
			for(ResizeComponent r: this.resizers) r.enableResize();
		}
		else for(ResizeComponent r: this.resizers) r.disableResize();
		this.resizable = resizable;
	}

	public void setPreviousValues(){
		this.setSize(LAST_SCREEN_SIZE);
		this.setLocation(LAST_LOCATION);
	}

	@Override
	public void setSize(int width, int height){
		if(this.resizable) super.setSize(width,height);
	}

	@Override
	public void setSize(Dimension dimension){
		if(this.resizable) super.setSize(dimension);
	}

	public void setSize(int size){
		if(this.resizable) {
			Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
			this.setExtendedState(JFrame.NORMAL);
			switch (size) {
				default:
					return;
				case FULL_SCREEN_SIZE:
					this.setExtendedState(JFrame.MAXIMIZED_BOTH);
					break;
				case HALF_SCREEN_SIZE:
					this.setSize(screenSize.width / 2, screenSize.height / 2);
					break;
			}
			this.setLocation(CENTER);
		}
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
		}
	}

	public boolean compareWindowSizeWith(int width, int height){
		Dimension dim = new Dimension(width, height);
		return (dim.equals(this.getSize()));
	}

	public boolean compareWindowSizes(Dimension dim1, Dimension dim2){
		return (dim1.equals(dim2));
	}

	public void setLocation(int location){

		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		int sizeW = screenSize.width;
		int sizeH = screenSize.height;
		int windowPosX;
		int windowPosY;

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


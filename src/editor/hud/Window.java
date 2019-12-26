package editor.hud;

import com.badlogic.gdx.utils.Array;
import editor.hud.managers.Drag;
import editor.hud.managers.Exit;
import editor.hud.managers.Minimize;
import editor.hud.managers.ResizeBottom;
import editor.hud.managers.ResizeLeft;
import editor.hud.managers.ResizeRight;
import editor.hud.managers.ResizeTop;
import editor.hud.managers.Smaller;
import editor.hud.ui.EnigmaButtonUI;
import editor.hud.ui.EnigmaMenuBarUI;
import editor.hud.ui.EnigmaUIValues;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * TODO: comment Window and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 1.0
 */
public class Window extends JFrame {

	public final static int FULL_SCREEN_SIZE = 0;
	public final static int HALF_SCREEN_SIZE = 1;

	public final static int NORTH = 0;
	public final static int EAST = 1;
	public final static int SOUTH = 3;
	public final static int WEST = 4;
	public final static int NORTH_EAST = 5;
	public final static int SOUTH_EAST = 6;
	public final static int SOUTH_WEST = 7;
	public final static int NORTH_WEST = 8;
	public final static int CENTER = 9;

	private final static int RIGHT_RESIZER = 0;
	private final static int LEFT_RESIZER = 1;
	private final static int BOTTOM_RESIZER = 2;
	private final static int TOP_RESIZER = 3;
	private final static int TOP_LEFT_RESIZER = 4;
	private final static int TOP_RIGHT_RESIZER = 5;
	private final static int BOTTOM_LEFT_RESIZER = 6;
	private final static int BOTTOM_RIGHT_RESIZER = 7;

	private EnigmaPanel windowContent;
	private ResizeComponent[] resizers = new ResizeComponent[8];
	private EnigmaPanel content;
	private boolean resizable;
	private EnigmaButton minimize;
	private EnigmaButton smaller;
	private EnigmaButton close;
	private Color menuBarBorderConfiguration;
	private int menuBarBorderSizeConfiguration;
	private boolean[] menuBarShowedBorderConfiguration;
	private boolean ask;
	private EnigmaMenuBar windowActionBar;
	private EnigmaLabel titleComponent;

	/**
	 * Initialise une fenêtre de la taille de l'écran
	 */
	public Window() {
		super();
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.ask = false;
		this.setSize(screenSize.getSize());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(FULL_SCREEN_SIZE);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.setLocation(0, 0);
		this.init("");
	}

	public Window(String title) {
		super();
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.ask = false;
		this.setSize(screenSize.getSize());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(FULL_SCREEN_SIZE);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.setLocation(0, 0);
		this.init(title);
	}

	public Window(int width, int height) {
		super();
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.ask = false;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(width, height);
		this.setLocation(CENTER);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.init("");
	}

	public Window(String title, int width, int height) {
		super();
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.ask = false;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(width, height);
		this.setLocation(CENTER);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.init(title);
	}

	private void init(String title) {
		//this.setIconImage(new ImageIcon(this.getClass().getResource("image/o.JPG")).getImage());
		this.setUndecorated(true);
		windowActionBar = new EnigmaMenuBar();
		EnigmaMenuBarUI barUI = new EnigmaMenuBarUI();
		boolean[] borderShowed = new boolean[4];
		borderShowed[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
		barUI.setShowedBorders(borderShowed);
		barUI.setBorderSize(2);
		barUI.setBorder(Color.RED);
		windowActionBar.setMenuBarUI(barUI);

		Color grey = new Color(100,100,100);

		this.titleComponent = new EnigmaLabel(title);
		this.titleComponent.getLabelUI().setAllForegrounds(grey,grey,grey);
		windowActionBar.add(this.titleComponent);

		Drag drag = new Drag(this);
		windowActionBar.addMouseListener(drag);
		windowActionBar.addMouseMotionListener(drag);

		this.close = new EnigmaButton("X");
		EnigmaButtonUI buttonUI = new EnigmaButtonUI();
		buttonUI.setAllBorders(null, null, null);
		buttonUI.setAllBackgrounds(barUI.getBackground(), Color.RED, Color.RED);
		buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.close.setButtonUI(buttonUI);
		this.close.addActionListener(new Exit(this));

		this.minimize = new EnigmaButton("-");
		buttonUI.setAllBackgrounds(barUI.getBackground(), grey, grey);
		this.minimize.setButtonUI(buttonUI);
		this.minimize.addActionListener(new Minimize(this));

		this.smaller = new EnigmaButton("[]");
		this.smaller.setButtonUI(buttonUI);
		this.smaller.addActionListener(new Smaller(this));

		windowActionBar.add(Box.createHorizontalGlue());
		windowActionBar.add(this.minimize);
		windowActionBar.add(this.smaller);
		windowActionBar.add(this.close);
		this.setJMenuBar(windowActionBar);
		this.menuBarBorderConfiguration = windowActionBar.getMenuBarUI().getBorder();
		this.menuBarBorderSizeConfiguration = windowActionBar.getMenuBarUI().getBorderSize();
		this.menuBarShowedBorderConfiguration = windowActionBar.getMenuBarUI().getShowedBorders();

		this.windowContent = new EnigmaPanel();
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

		this.resizers[RIGHT_RESIZER].addResizer(new ResizeRight(this, this.resizers[RIGHT_RESIZER]));
		this.resizers[LEFT_RESIZER].addResizer(new ResizeLeft(this, this.resizers[LEFT_RESIZER]));
		this.resizers[BOTTOM_RESIZER].addResizer(new ResizeBottom(this, this.resizers[BOTTOM_RESIZER]));
		this.resizers[BOTTOM_LEFT_RESIZER].addResizer(new ResizeBottom(this, this.resizers[BOTTOM_LEFT_RESIZER]));
		this.resizers[BOTTOM_RIGHT_RESIZER].addResizer(new ResizeBottom(this, this.resizers[BOTTOM_RIGHT_RESIZER]));
		this.resizers[BOTTOM_LEFT_RESIZER].addResizer(new ResizeLeft(this, this.resizers[BOTTOM_LEFT_RESIZER]));
		this.resizers[BOTTOM_RIGHT_RESIZER].addResizer(new ResizeRight(this, this.resizers[BOTTOM_RIGHT_RESIZER]));
		this.resizers[TOP_RESIZER].addResizer(new ResizeTop(this, this.resizers[TOP_RESIZER]));
		this.resizers[TOP_LEFT_RESIZER].addResizer(new ResizeTop(this, this.resizers[TOP_LEFT_RESIZER]));
		this.resizers[TOP_RIGHT_RESIZER].addResizer(new ResizeTop(this, this.resizers[TOP_RIGHT_RESIZER]));
		this.resizers[TOP_LEFT_RESIZER].addResizer(new ResizeLeft(this, this.resizers[TOP_LEFT_RESIZER]));
		this.resizers[TOP_RIGHT_RESIZER].addResizer(new ResizeRight(this, this.resizers[TOP_RIGHT_RESIZER]));

		this.resizers[BOTTOM_RESIZER].add(this.resizers[BOTTOM_LEFT_RESIZER], BorderLayout.WEST);
		this.resizers[BOTTOM_RESIZER].add(this.resizers[BOTTOM_RIGHT_RESIZER], BorderLayout.EAST);
		this.resizers[TOP_RESIZER].add(this.resizers[TOP_LEFT_RESIZER], BorderLayout.WEST);
		this.resizers[TOP_RESIZER].add(this.resizers[TOP_RIGHT_RESIZER], BorderLayout.EAST);

		this.windowContent.setLayout(new BorderLayout());
		this.windowContent.add(this.resizers[RIGHT_RESIZER], BorderLayout.EAST);
		this.windowContent.add(this.resizers[LEFT_RESIZER], BorderLayout.WEST);
		this.windowContent.add(this.resizers[BOTTOM_RESIZER], BorderLayout.SOUTH);
		this.windowContent.add(this.resizers[TOP_RESIZER], BorderLayout.NORTH);
		this.windowContent.add(this.content, BorderLayout.CENTER);
		this.windowContent.getPanelUI().setBackground(Color.RED);
		this.add(this.windowContent, BorderLayout.CENTER);
	}

	/**
	 * Ajout a la barre de menu une barre de menu
	 *
	 * @param bar une autre bar de menu
	 * @since 4.0
	 */
	public void addToMenuBar(EnigmaMenuBar bar) {
		this.windowActionBar.removeAll();
		for (Component c : new Array.ArrayIterator<>(bar.getMenus())) {
			this.windowActionBar.add(c);
		}
		this.windowActionBar.add(Box.createHorizontalGlue());
		this.windowActionBar.add(this.minimize);
		this.windowActionBar.add(this.smaller);
		this.windowActionBar.add(this.close);

	}

	public void setIfAskBeforeClosing(boolean askBeforeClosing) {
		this.ask = askBeforeClosing;
	}

	public boolean willAskBeforeClosing() {
		return ask;
	}

	public void showMinimizeButton(boolean show) {
		this.minimize.setVisible(show);
	}

	public void showSmallerButton(boolean show) {
		this.smaller.setVisible(show);
	}

	public void showCloseButton(boolean show) {
		this.close.setVisible(show);
	}

	public void hideAllButton() {
		this.showMinimizeButton(false);
		this.showSmallerButton(false);
		this.showCloseButton(false);
	}

	public void showAllButton() {
		this.showMinimizeButton(true);
		this.showSmallerButton(true);
		this.showCloseButton(true);
	}

	public void hideMenuBar() {
		this.getJMenuBar().setVisible(false);
	}

	public void showMenuBar() {
		this.getJMenuBar().setVisible(true);
	}

	public EnigmaPanel getContentSpace() {
		return this.content;
	}

	public boolean isFullScreen() {
		return (this.getExtendedState() == JFrame.MAXIMIZED_BOTH);
	}

	public boolean isResizable() {
		return this.resizable;
	}

	@Override
	public void setResizable(boolean resizable) {
		if (resizable) {
			for (ResizeComponent r : this.resizers) r.enableResize();
			this.showSmallerButton(true);
		} else {
			for (ResizeComponent r : this.resizers) r.disableResize();
			this.showSmallerButton(false);
		}
		this.resizable = resizable;
	}

	@Override
	public void setSize(int width, int height) {
		if (this.resizable) super.setSize(width, height);
	}

	@Override
	public void setSize(Dimension dimension) {
		if (this.resizable) super.setSize(dimension);
	}

	public void setSize(int size) {
		if (this.resizable) {
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

	public boolean compareWindowSizeWith(int size) {
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		switch (size) {
			default:
				return false;
			case FULL_SCREEN_SIZE:
				return this.isFullScreen();
			case HALF_SCREEN_SIZE:
				Dimension half = new Dimension(screenSize.width / 2, screenSize.height / 2);
				return (half.equals(this.getSize()));
		}
	}

	public boolean compareWindowSizeWith(int width, int height) {
		Dimension dim = new Dimension(width, height);
		return (dim.equals(this.getSize()));
	}

	public boolean compareWindowSizes(Dimension dim1, Dimension dim2) {
		return (dim1.equals(dim2));
	}

	public Rectangle getScreenBounds(){
		return this.getGraphicsConfiguration().getBounds();
	}

	public void setLocation(int location) {

		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		int sizeW = screenSize.width;
		int sizeH = screenSize.height;
		int windowPosX;
		int windowPosY;

		switch (location) {
			default:
				return;
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

	public void setMenuBar(EnigmaMenuBar menuBar) {
		this.setJMenuBar(menuBar);
		this.menuBarBorderConfiguration = menuBar.getMenuBarUI().getBorder();
		this.menuBarBorderSizeConfiguration = menuBar.getMenuBarUI().getBorderSize();
		this.menuBarShowedBorderConfiguration = menuBar.getMenuBarUI().getShowedBorders();
	}

	public void setWindowBackground(Color bgColor) {
		this.content.setOpaque(true);
		this.windowContent.getPanelUI().setAllBackgroundImage(null, null, null);
		this.content.setBackground(bgColor);
		for (ResizeComponent r : this.resizers) {
			r.setOpaque(true);
			r.setBackground(bgColor);
		}
	}

	public void setWindowBackground(ImageIcon image) {
		this.content.setOpaque(false);
		for (ResizeComponent r : this.resizers) r.setOpaque(false);
		this.windowContent.getPanelUI().setAllBackgroundImage(image, image, image);
	}

	public void hideBorder() {
		EnigmaMenuBar bar = (EnigmaMenuBar) this.getJMenuBar();
		bar.getMenuBarUI().setShowedBorders(this.menuBarShowedBorderConfiguration);
		bar.getMenuBarUI().setBorderSize(this.menuBarBorderSizeConfiguration);
		bar.getMenuBarUI().setBorder(this.menuBarBorderConfiguration);

		this.resizers[LEFT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[RIGHT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[BOTTOM_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[TOP_LEFT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[TOP_RIGHT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[BOTTOM_LEFT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[BOTTOM_RIGHT_RESIZER].setBorder(BorderFactory.createEmptyBorder());

		if (this.close.isVisible())
			this.close.getButtonUI().setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN);
		if (this.minimize.isVisible())
			this.minimize.getButtonUI().setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN);
		if (this.smaller.isVisible())
			this.smaller.getButtonUI().setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN, EnigmaUIValues.ALL_BORDER_HIDDEN);
	}

	public void showBorder(Color color, int borderSize) {
		EnigmaMenuBar bar = (EnigmaMenuBar) this.getJMenuBar();
		EnigmaMenuBarUI barUI = bar.getMenuBarUI();

		this.menuBarBorderConfiguration = barUI.getBorder();
		this.menuBarBorderSizeConfiguration = barUI.getBorderSize();
		this.menuBarShowedBorderConfiguration = barUI.getShowedBorders();

		barUI.setShowedBorders(EnigmaUIValues.ALL_BORDERS_SHOWED);
		barUI.setBorder(color);
		barUI.setBorderSize(borderSize);

		this.resizers[LEFT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, color));
		this.resizers[RIGHT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, color));
		this.resizers[BOTTOM_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, borderSize, 0, color));
		this.resizers[TOP_LEFT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, color));
		this.resizers[TOP_RIGHT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, color));
		this.resizers[BOTTOM_LEFT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, color));
		this.resizers[BOTTOM_RIGHT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, color));

		EnigmaButtonUI bUI;
		boolean[] borders = new boolean[4];
		borders[EnigmaUIValues.TOP_BORDER] = true;
		boolean[] bordersRight = new boolean[4];
		bordersRight[EnigmaUIValues.TOP_BORDER] = true;
		bordersRight[EnigmaUIValues.RIGHT_BORDER] = true;

		if (this.close.isVisible()) {
			bUI = this.close.getButtonUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(bordersRight, bordersRight, bordersRight);

			bUI = this.smaller.getButtonUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(borders, borders, borders);

			bUI = this.minimize.getButtonUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(borders, borders, borders);

		} else if (this.smaller.isVisible()) {
			bUI = this.smaller.getButtonUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(bordersRight, bordersRight, bordersRight);

			bUI = this.minimize.getButtonUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(borders, borders, borders);

		} else if (this.minimize.isVisible()) {
			bUI = this.minimize.getButtonUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(bordersRight, bordersRight, bordersRight);
		}
	}
}


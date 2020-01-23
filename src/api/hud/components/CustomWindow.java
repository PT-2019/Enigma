package api.hud.components;

import api.hud.AbstractWindow;
import api.hud.DefaultUIValues;
import api.hud.WindowSize;
import api.hud.manager.window.Drag;
import api.hud.manager.window.Exit;
import api.hud.manager.window.Minimize;
import api.hud.manager.window.ResizeBottom;
import api.hud.manager.window.ResizeComponent;
import api.hud.manager.window.ResizeLeft;
import api.hud.manager.window.ResizeRight;
import api.hud.manager.window.ResizeTop;
import api.hud.manager.window.Smaller;
import api.hud.ui.CustomButtonUI;
import api.hud.ui.CustomMenuBarUI;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.utils.Array;
import org.jetbrains.annotations.NotNull;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Une fenêtre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 1.0
 */
public class CustomWindow extends JFrame implements AbstractWindow {

	public static final boolean DECORATED = true;

	/**
	 * Boutons de la barre de menu
	 *
	 * @since 3.0
	 */
	protected CustomButton minimize, smaller, close;

	protected CustomPanel windowContent;
	protected CustomPanel content;
	protected CustomMenuBar windowActionBar;
	private ResizeComponent[] resizers = new ResizeComponent[8];
	private boolean resizable;
	private Color menuBarBorderConfiguration;
	private int menuBarBorderSizeConfiguration;
	private boolean[] menuBarShowedBorderConfiguration;
	private boolean ask;

	/**
	 * Initialise une fenêtre de la taille de l'écran
	 *
	 * @since 3.0
	 */
	public CustomWindow() {
		super();
		createWindow(WindowSize.FULL_SCREEN_SIZE, true);
	}

	/**
	 * Crée une fenêtre d'une taille voulue
	 *
	 * @param width  largeur
	 * @param height hauteur
	 * @since 1.0
	 */
	public CustomWindow(int width, int height) {
		this();
		createWindow(WindowSize.HALF_SCREEN_SIZE, false);
		this.setSize(width, height);
		this.setLocation(CENTER);
		this.init();
	}

	/**
	 * Crée une fenêtre dans un écran
	 *
	 * @param monitor l'écran
	 * @since 5.0
	 */
	public CustomWindow(GraphicsConfiguration monitor) {
		super(monitor);
		createWindow(WindowSize.FULL_SCREEN_SIZE, true);
	}

	/**
	 * Méthode pour factoriser le code de création d'une window
	 * et diminuer les risques de ne changer que dans un constructeur
	 *
	 * @param constant constante pour la taille de la fenêtre, -1 si aucune
	 * @param init     true si on doit initialiser la fenêtre
	 * @since 4.0
	 */
	@ConvenienceMethod
	private void createWindow(WindowSize constant, boolean init) {
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.ask = false;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(constant);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.setLocation(0, 0);
		this.setUndecorated(!DECORATED);
		this.windowContent = new CustomPanel();
		this.content = new CustomPanel();
		if (init)
			this.init();
	}

	/**
	 * Initialise la fenêtre
	 */
	protected void init() {
		//barre de menus
		createMenuBar(new CustomMenuBar(), new CustomMenuBarUI());
		initContent();
	}

	/**
	 * Renvoi le panneau qui contient le contenu a la fenêtre
	 *
	 * @return le panneau qui contient le contenu a la fenêtre
	 */
	@Override
	public CustomPanel getContentSpace() {
		return this.content;
	}

	/**
	 * Prépare le contenu de la fenêtre
	 *
	 * @since 4.3
	 */
	protected void initContent() {
		this.content.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

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

		if (!DECORATED) {
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
		} else {
			for (ResizeComponent c : resizers) {
				c.setVisible(false);
			}
		}


		this.windowContent.setLayout(new BorderLayout());
		this.windowContent.add(this.resizers[RIGHT_RESIZER], BorderLayout.EAST);
		this.windowContent.add(this.resizers[LEFT_RESIZER], BorderLayout.WEST);
		this.windowContent.add(this.resizers[BOTTOM_RESIZER], BorderLayout.SOUTH);
		this.windowContent.add(this.resizers[TOP_RESIZER], BorderLayout.NORTH);
		this.windowContent.add(this.content, BorderLayout.CENTER);
		this.windowContent.getComponentUI().setBackground(Color.RED);
		this.add(this.windowContent, BorderLayout.CENTER);
	}

	/**
	 * Méthode appelée par init pour créer la barre de menus
	 *
	 * @param bar   la barre de menu
	 * @param barUI son ui
	 * @since 4.0
	 */
	protected void createMenuBar(@NotNull CustomMenuBar bar, @NotNull CustomMenuBarUI barUI) {
		boolean[] borderShowed = new boolean[4];
		borderShowed[DefaultUIValues.BOTTOM_BORDER] = DefaultUIValues.SHOWED_BORDER;
		barUI.setShowedBorders(borderShowed);
		barUI.setBorderSize(2);
		barUI.setBorder(Color.RED);
		bar.setComponentUI(barUI);

		this.windowActionBar = bar;

		if (!DECORATED) {
			Drag drag = new Drag(this);
			bar.addMouseListener(drag);
			bar.addMouseMotionListener(drag);

			CustomButtonUI buttonUI = new CustomButtonUI();
			buttonUI.setAllBorders(null, null, null);
			buttonUI.setAllBackgrounds(barUI.getBackground(), Color.RED, Color.RED);
			buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
			buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));


			if (close == null) {
				this.close = new CustomButton("X");
				this.close.setComponentUI(buttonUI);
				this.close.addActionListener(new Exit(this));
			} else {
				buttonUI.setAllBackgrounds(barUI.getBackground(),
						DefaultUIValues.DEFAULT_BUTTON_PRESSED_BACKGROUND, DefaultUIValues.DEFAULT_BUTTON_PRESSED_BACKGROUND);
			}

			if (minimize == null) {
				this.minimize = new CustomButton("-");
				this.minimize.setComponentUI(buttonUI);
				this.minimize.addActionListener(new Minimize(this));
			}

			if (smaller == null) {
				this.smaller = new CustomButton("[]");
				this.smaller.setComponentUI(buttonUI);
				this.smaller.addActionListener(new Smaller(this));
			}

			bar.add(Box.createHorizontalGlue());
			bar.add(this.minimize);
			bar.add(this.smaller);
			bar.add(this.close);
		}

		this.setJMenuBar(bar);
		this.menuBarBorderConfiguration = bar.getComponentUI().getBorder();
		this.menuBarBorderSizeConfiguration = bar.getComponentUI().getBorderSize();
		this.menuBarShowedBorderConfiguration = bar.getComponentUI().getShowedBorders();
	}

	public void close() {
		if (!DECORATED) {
			for (ActionListener e : this.close.getActionListeners()) {
				if (e instanceof Exit) {
					System.out.println("close!!!");
					this.close.getActionListeners()[0].actionPerformed(
							new ActionEvent(this, -1, "")
					);
				}
			}
		}
	}

	@Override
	public void setExitListener(Exit listener) {
		if (!DECORATED) {
			for (ActionListener e : this.close.getActionListeners()) {
				if (e instanceof Exit) {
					this.close.removeActionListener(e);
				}
			}

			this.close.addActionListener(listener);
		} else {
			//ou la la la
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			//ou la la la on devrait créer une classe mais j'ai trop de javadoc déjà
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					listener.actionPerformed(new ActionEvent(e.getWindow(),
							ActionEvent.ACTION_LAST, ""));
				}
			});
		}
	}

	/**
	 * Ajout a la barre de menu une barre de menu
	 *
	 * @param bar une autre bar de menu
	 * @since 4.0
	 */
	public void addToMenuBar(CustomMenuBar bar) {
		this.windowActionBar.removeAll();
		for (Component c : new Array.ArrayIterator<>(bar.getMenus())) {
			this.windowActionBar.add(c);
		}
		this.windowActionBar.add(Box.createHorizontalGlue());
		if (!DECORATED) {
			this.windowActionBar.add(this.minimize);
			this.windowActionBar.add(this.smaller);
			this.windowActionBar.add(this.close);
		}
	}

	@Override
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

	@Override
	public boolean compareWindowSizeWith(int width, int height) {
		Dimension dim = new Dimension(width, height);
		return (dim.equals(this.getSize()));
	}

	@Override
	public boolean compareWindowSizes(Dimension dim1, Dimension dim2) {
		return (dim1.equals(dim2));
	}

	// ----------------------- GETTERS AND SETTERS ------------------- \\

	@Override
	@Deprecated
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

	@Override
	public void setSize(WindowSize size) {
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

	@Override
	public void setMinimumSize(WindowSize size) {
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		switch (size) {
			default:
				return;
			case FULL_SCREEN_SIZE:
				this.setMinimumSize(new Dimension(screenSize.width, screenSize.height));
				break;
			case HALF_SCREEN_SIZE:
				this.setMinimumSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
				break;
		}
	}

	@Override
	public void setIfAskBeforeClosing(boolean askBeforeClosing) {
		this.ask = askBeforeClosing;
	}

	@Override
	public boolean willAskBeforeClosing() {
		return ask;
	}

	@Override
	public void showMinimizeButton(boolean show) {
		if (!DECORATED)
			this.minimize.setVisible(show);
	}

	@Override
	public void showSmallerButton(boolean show) {
		if (!DECORATED)
			this.smaller.setVisible(show);
	}

	@Override
	public void showCloseButton(boolean show) {
		if (!DECORATED)
			this.close.setVisible(show);
	}

	@Override
	public void setMenuButtonsVisible(boolean show) {
		this.showMinimizeButton(show);
		this.showSmallerButton(show);
		this.showCloseButton(show);
	}

	@Override
	public void setMenuBarVisible(boolean show) {
		this.getJMenuBar().setVisible(show);
	}

	@Override
	public boolean isFullScreen() {
		return (this.getExtendedState() == JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public boolean isResizable() {
		return this.resizable;
	}

	@Override
	public void setResizable(boolean resizable) {
		if (resizable) {
			for (ResizeComponent r : this.resizers) r.enableResize();
			this.showSmallerButton(true);
			//this.windowActionBar.setResizable(true);
		} else {
			for (ResizeComponent r : this.resizers) r.disableResize();
			this.showSmallerButton(false);
			//this.windowActionBar.setResizable(false);
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

	@Override
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

	@Override
	public void setWindowMenuBar(CustomMenuBar menuBar) {
		this.setJMenuBar(menuBar);
		this.windowActionBar = menuBar;
		this.menuBarBorderConfiguration = menuBar.getComponentUI().getBorder();
		this.menuBarBorderSizeConfiguration = menuBar.getComponentUI().getBorderSize();
		this.menuBarShowedBorderConfiguration = menuBar.getComponentUI().getShowedBorders();
	}

	@Override
	public void setWindowBackground(Color bgColor) {
		this.content.setOpaque(true);
		this.windowContent.getComponentUI().setAllBackgroundImage(null, null, null);
		this.content.setBackground(bgColor);

		for (ResizeComponent r : this.resizers) {
			r.setOpaque(true);
			r.setBackground(bgColor);
		}

	}

	@Override
	public void setBackground(ImageIcon image) {
		this.content.setOpaque(false);
		for (ResizeComponent r : this.resizers) r.setOpaque(false);
		this.windowContent.getComponentUI().setAllBackgroundImage(image, image, image);
	}

	@Override
	public void hideBorder() {
		CustomMenuBar bar = (CustomMenuBar) this.getJMenuBar();
		bar.getComponentUI().setShowedBorders(this.menuBarShowedBorderConfiguration);
		bar.getComponentUI().setBorderSize(this.menuBarBorderSizeConfiguration);
		bar.getComponentUI().setBorder(this.menuBarBorderConfiguration);

		this.resizers[LEFT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[RIGHT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[BOTTOM_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[TOP_LEFT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[TOP_RIGHT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[BOTTOM_LEFT_RESIZER].setBorder(BorderFactory.createEmptyBorder());
		this.resizers[BOTTOM_RIGHT_RESIZER].setBorder(BorderFactory.createEmptyBorder());


		if (this.close.isVisible())
			this.close.getComponentUI().setAllShowedBorders(DefaultUIValues.ALL_BORDER_HIDDEN,
					DefaultUIValues.ALL_BORDER_HIDDEN, DefaultUIValues.ALL_BORDER_HIDDEN);
		if (this.minimize.isVisible())
			this.minimize.getComponentUI().setAllShowedBorders(DefaultUIValues.ALL_BORDER_HIDDEN,
					DefaultUIValues.ALL_BORDER_HIDDEN, DefaultUIValues.ALL_BORDER_HIDDEN);
		if (this.smaller.isVisible())
			this.smaller.getComponentUI().setAllShowedBorders(DefaultUIValues.ALL_BORDER_HIDDEN,
					DefaultUIValues.ALL_BORDER_HIDDEN, DefaultUIValues.ALL_BORDER_HIDDEN);
	}

	@Override
	public void showBorder(Color color, int borderSize) {
		CustomMenuBar bar = (CustomMenuBar) this.getJMenuBar();
		CustomMenuBarUI barUI = bar.getComponentUI();

		this.menuBarBorderConfiguration = barUI.getBorder();
		this.menuBarBorderSizeConfiguration = barUI.getBorderSize();
		this.menuBarShowedBorderConfiguration = barUI.getShowedBorders();

		barUI.setShowedBorders(DefaultUIValues.ALL_BORDERS_SHOWED);
		barUI.setBorder(color);
		barUI.setBorderSize(borderSize);


		this.resizers[LEFT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, color));
		this.resizers[RIGHT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, color));
		this.resizers[BOTTOM_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, borderSize, 0, color));
		this.resizers[TOP_LEFT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, color));
		this.resizers[TOP_RIGHT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, color));
		this.resizers[BOTTOM_LEFT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, color));
		this.resizers[BOTTOM_RIGHT_RESIZER].setBorder(BorderFactory.createMatteBorder(0, 0, 0, borderSize, color));


		CustomButtonUI bUI;
		boolean[] borders = new boolean[4];
		borders[DefaultUIValues.TOP_BORDER] = true;
		boolean[] bordersRight = new boolean[4];
		bordersRight[DefaultUIValues.TOP_BORDER] = true;
		bordersRight[DefaultUIValues.RIGHT_BORDER] = true;

		if (this.close.isVisible()) {
			bUI = this.close.getComponentUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(bordersRight, bordersRight, bordersRight);

			bUI = this.smaller.getComponentUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(borders, borders, borders);

			bUI = this.minimize.getComponentUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(borders, borders, borders);

		} else if (this.smaller.isVisible()) {
			bUI = this.smaller.getComponentUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(bordersRight, bordersRight, bordersRight);

			bUI = this.minimize.getComponentUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(borders, borders, borders);

		} else if (this.minimize.isVisible()) {
			bUI = this.minimize.getComponentUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(bordersRight, bordersRight, bordersRight);
		}
	}
}


package api.ui;

import api.ui.base.AbstractWindow;
import api.ui.base.DefaultUIValues;
import api.ui.base.WindowSize;
import api.ui.manager.alert.Drag;
import api.ui.manager.alert.Exit;
import api.ui.manager.alert.ResizeBottom;
import api.ui.manager.alert.ResizeComponent;
import api.ui.manager.alert.ResizeLeft;
import api.ui.manager.alert.ResizeRight;
import api.ui.manager.alert.ResizeTop;
import api.ui.skin.CustomButtonUI;
import api.ui.skin.CustomMenuBarUI;
import api.utils.annotations.DoNothing;
import org.jetbrains.annotations.NotNull;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

/**
 * Un popup d'alert
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1 27/12/2019
 * @since 4.0 27/12/2019
 */
public class CustomAlert extends JDialog implements AbstractWindow {

	protected CustomPanel windowContent;
	protected CustomPanel content;
	protected boolean resizable;
	protected CustomButton close;
	private ResizeComponent[] resizers = new ResizeComponent[8];
	private Color menuBarBorderConfiguration;
	private int menuBarBorderSizeConfiguration;
	private boolean[] menuBarShowedBorderConfiguration;

	/**
	 * Initialise un popup de la taille de l'écran
	 */
	public CustomAlert() {
		super();
		this.create(true);
	}

	/**
	 * Crée un popup de taille voulue
	 *
	 * @param width  largeur
	 * @param height hauteur
	 */
	public CustomAlert(int width, int height) {
		super();
		this.create(false);
		this.setSize(width, height);
		this.setLocation(CENTER);
		this.init();
	}

	private void create(boolean init) {
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.setSize(screenSize.getSize());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.setLocation(0, 0);

		this.windowContent = new CustomPanel();
		this.content = new CustomPanel();

		this.setUndecorated(true);

		if (init) {
			this.init();
		}
	}


	@Override
	public CustomPanel getContentSpace() {
		return this.content;
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
	 * Méthode appelée par init pour créer la barre de menus
	 *
	 * @param bar   la barre de menu
	 * @param barUI son ui
	 * @since 4.1
	 */
	protected void createMenuBar(@NotNull CustomMenuBar bar, @NotNull CustomMenuBarUI barUI) {
		boolean[] borderShowed = new boolean[4];
		borderShowed[DefaultUIValues.BOTTOM_BORDER] = DefaultUIValues.SHOWED_BORDER;
		barUI.setShowedBorders(borderShowed);
		barUI.setBorderSize(2);
		barUI.setBorder(Color.RED);
		bar.setComponentUI(barUI);

		Drag drag = new Drag(this);
		bar.addMouseListener(drag);
		bar.addMouseMotionListener(drag);

		this.close = new CustomButton("X");
		CustomButtonUI buttonUI = new CustomButtonUI();
		buttonUI.setAllBorders(null, null, null);
		buttonUI.setAllBackgrounds(barUI.getBackground(), Color.RED, Color.RED);
		buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.close.setComponentUI(buttonUI);
		this.close.addActionListener(new Exit(this));

		bar.add(Box.createHorizontalGlue());
		bar.add(this.close);
		this.setJMenuBar(bar);
		this.menuBarBorderConfiguration = bar.getComponentUI().getBorder();
		this.menuBarBorderSizeConfiguration = bar.getComponentUI().getBorderSize();
		this.menuBarShowedBorderConfiguration = bar.getComponentUI().getShowedBorders();
	}

	/**
	 * Prépare le contenu de la fenêtre
	 *
	 * @since 4.1
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
		this.windowContent.getComponentUI().setBackground(Color.RED);
		this.getContentPane().add(this.windowContent, BorderLayout.CENTER);
	}

	//others

	public void setHalfScreenSize() {
		if (this.resizable) {
			Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
			this.setSize(screenSize.width / 2, screenSize.height / 2);
			this.setLocation(CENTER);
		}
	}

	public boolean isHalfScreenSize() {
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		return (this.getSize().equals(new Dimension(screenSize.width / 2, screenSize.height / 2)));
	}


	//api

	@Override
	@DoNothing
	public void showMinimizeButton(boolean show) {
	}

	@Override
	@DoNothing
	public void showSmallerButton(boolean show) {
	}

	@Override
	public void showCloseButton(boolean show) {
		this.close.setVisible(show);
	}

	@Override
	public void setMenuButtonsVisible(boolean show) {
		this.showMinimizeButton(show);
		this.showSmallerButton(show);
		this.showCloseButton(show);
	}

	/**
	 * Change le listener de fermeture de la fenêtre
	 *
	 * @param listener le nouveau listener de fermeture de la fenêtre
	 */
	public void setExitListener(api.ui.manager.window.Exit listener) {
		for (ActionListener e : this.close.getActionListeners()) {
			if (e instanceof api.ui.manager.window.Exit) {
				this.close.removeActionListener(e);
			}
		}
		this.close.addActionListener(listener);
	}

	@Override
	public void setMenuBarVisible(boolean show) {
		this.getJMenuBar().setVisible(show);
	}

	@Override
	public boolean isResizable() {
		return this.resizable;
	}

	@Override
	public void setResizable(boolean resizable) {
		if (resizable) {
			for (ResizeComponent r : this.resizers) r.enableResize();
		} else {
			for (ResizeComponent r : this.resizers) r.disableResize();
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
	public void setSize(WindowSize size) {
		throw new UnsupportedOperationException("unsupported");
	}

	@Override
	public void setMinimumSize(WindowSize size) {
		throw new UnsupportedOperationException("unsupported");
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
	public void setIfAskBeforeClosing(boolean askBeforeClosing) {
		if (askBeforeClosing)
			throw new IllegalArgumentException("will never ask before closing");
	}

	@Override
	public boolean willAskBeforeClosing() {
		return false;
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
		boolean[] bordersRight = new boolean[4];
		bordersRight[DefaultUIValues.TOP_BORDER] = true;
		bordersRight[DefaultUIValues.RIGHT_BORDER] = true;

		if (this.close.isVisible()) {
			bUI = this.close.getComponentUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(bordersRight, bordersRight, bordersRight);

		}
	}

	@Override
	public boolean isFullScreen() {
		return false;
	}
}
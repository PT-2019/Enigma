package editor.hud;

import editor.hud.ui.EnigmaButtonUI;
import editor.hud.ui.EnigmaMenuBarUI;
import editor.hud.ui.EnigmaUIValues;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * TODO: comment Alert and write Readme.md in editor.hud.alert
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class Alert extends JDialog {

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
	private EnigmaButton close;
	private Color menuBarBorderConfiguration;
	private int menuBarBorderSizeConfiguration;
	private boolean[] menuBarShowedBorderConfiguration;

	/**
	 * Initialise une fenêtre de la taille de l'écran
	 */
	public Alert() {
		super();
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.setSize(screenSize.getSize());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.setLocation(0, 0);
		this.init();
	}
	public Alert(int width, int height) {
		super();
		Rectangle screenSize = this.getGraphicsConfiguration().getBounds();
		this.resizable = true;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(width, height);
		this.setLocation(CENTER);
		this.setMinimumSize(new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 3));
		this.init();
	}

	private void init() {
		//this.setIconImage(new ImageIcon(this.getClass().getResource("image/o.JPG")).getImage());
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

		this.close = new EnigmaButton("X");
		EnigmaButtonUI buttonUI = new EnigmaButtonUI();
		buttonUI.setAllBorders(null, null, null);
		buttonUI.setAllBackgrounds(barUI.getBackground(), Color.RED, Color.RED);
		buttonUI.setAllForegrounds(Color.WHITE, Color.WHITE, Color.WHITE);
		buttonUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.close.setButtonUI(buttonUI);
		this.close.addActionListener(new Exit(this));

		windowActionBar.add(Box.createHorizontalGlue());
		windowActionBar.add(this.close);
		this.setJMenuBar(windowActionBar);
		this.menuBarBorderConfiguration = windowActionBar.getMenuBarUI().getBorder();
		this.menuBarBorderSizeConfiguration = windowActionBar.getMenuBarUI().getBorderSize();
		this.menuBarShowedBorderConfiguration = windowActionBar.getMenuBarUI().getShowedBorders();

		this.windowContent = new EnigmaPanel();
		this.content = new EnigmaPanel();
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
		this.windowContent.getPanelUI().setBackground(Color.RED);
		this.getContentPane().add(this.windowContent, BorderLayout.CENTER);
	}

	public void showCloseButton(boolean show) {
		this.close.setVisible(show);
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

	public boolean compareWindowSizeWith(int width, int height) {
		Dimension dim = new Dimension(width, height);
		return (dim.equals(this.getSize()));
	}

	public boolean compareWindowSizes(Dimension dim1, Dimension dim2) {
		return (dim1.equals(dim2));
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

	public void setWindowMenuBar(EnigmaMenuBar menuBar) {
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

	public void setBackground(ImageIcon image) {
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
		boolean[] bordersRight = new boolean[4];
		bordersRight[EnigmaUIValues.TOP_BORDER] = true;
		bordersRight[EnigmaUIValues.RIGHT_BORDER] = true;

		if (this.close.isVisible()) {
			bUI = this.close.getButtonUI();
			bUI.setAllBorders(color, color, color);
			bUI.setAllBordersSize(borderSize, borderSize, borderSize);
			bUI.setAllShowedBorders(bordersRight, bordersRight, bordersRight);

		}
	}

	/**
	 * TODO: comment Drag
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0
	 * @since 3.0
	 */
	private static final class Drag extends MouseAdapter {

		private final Alert window;
		private Point pressedCords;

		Drag(Alert window) {
			this.window = window;
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			this.pressedCords = e.getPoint();
		}

		public void mouseDragged(MouseEvent e) {
			Point currentCords = e.getLocationOnScreen();
			this.window.setLocation(currentCords.x - this.pressedCords.x, currentCords.y - this.pressedCords.y);
		}
	}

	/**
	 * TODO: comment Exit
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0
	 * @since 3.0
	 */
	private static final class Exit implements ActionListener {

		private Alert window;

		Exit(Alert window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			this.window.dispose();
		}
	}

	/**
	 * TODO: comment Resize
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0
	 * @since 3.0
	 */
	private static abstract class Resize implements MouseMotionListener {

		protected ResizeComponent resizeComponent;
		protected java.awt.Window window;
		protected boolean resizable;

		public Resize(java.awt.Window window, ResizeComponent resizeComponent) {
			super();
			this.window = window;
			this.resizeComponent = resizeComponent;
			this.resizeComponent.addMouseMotionListener(this);
			this.resizable = true;
		}

		public void disable() {
			this.resizable = false;
		}

		public void enable() {
			this.resizable = true;
		}
	}

	/**
	 * TODO: comment ResizeComponent
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0
	 * @since 3.0
	 */
	private static final class ResizeComponent extends JPanel {

		private Cursor cursor;
		private ArrayList<Resize> resizeManagers;

		public ResizeComponent(Cursor cursor) {
			super();
			this.cursor = cursor;
			this.setCursor(cursor);
			this.resizeManagers = new ArrayList<>();
		}

		public void addResizer(Resize r) {
			this.resizeManagers.add(r);
			super.addMouseMotionListener(r);
		}

		public void disableResize() {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			for (Resize r : this.resizeManagers) r.disable();
		}

		public void enableResize() {
			this.setCursor(this.cursor);
			for (Resize r : this.resizeManagers) r.enable();
		}
	}

	/**
	 * TODO: comment ResizeBottom
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0
	 * @since 3.0
	 */
	private static final class ResizeBottom extends Resize {

		public ResizeBottom(Dialog window, ResizeComponent resizeComponent) {
			super(window, resizeComponent);
		}

		@Override
		public void mouseDragged(MouseEvent mouseEvent) {
			Point mouseCords = mouseEvent.getPoint();
			Rectangle window = this.window.getBounds();
			if (this.resizable) {
				if (this.window.getMaximumSize().height > window.height) {
					this.window.setSize(window.width, window.height + mouseCords.y);
				} else if (mouseCords.x < this.resizeComponent.getHeight()) {
					this.window.setSize(window.width, window.height + mouseCords.y);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent mouseEvent) {
		}
	}

	/**
	 * TODO: comment ResizeLeft
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0
	 * @since 3.0
	 */
	private static final class ResizeLeft extends Resize {

		public ResizeLeft(Dialog window, ResizeComponent resizeComponent) {
			super(window, resizeComponent);
		}

		@Override
		public void mouseDragged(MouseEvent mouseEvent) {
			Point mouseCords = mouseEvent.getPoint();
			Rectangle window = this.window.getBounds();
			if (this.resizable) {
				if (this.window.getMinimumSize().width < window.width) {
					this.window.setSize(window.width - mouseCords.x, window.height);
					this.window.setLocation(window.x + mouseCords.x, window.y);
				} else if (mouseCords.x < this.resizeComponent.getX()) {
					this.window.setSize(window.width - mouseCords.x, window.height);
					this.window.setLocation(window.x + mouseCords.x, window.y);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent mouseEvent) {
		}
	}

	/**
	 * TODO: comment ResizeRight
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0
	 * @since 3.0
	 */
	private static final class ResizeRight extends Resize {

		public ResizeRight(Dialog window, ResizeComponent resizeComponent) {
			super(window, resizeComponent);
		}

		@Override
		public void mouseDragged(MouseEvent mouseEvent) {
			Point mouseCords = mouseEvent.getPoint();
			Rectangle window = this.window.getBounds();
			if (this.resizable) {
				if (this.window.getMaximumSize().width > window.width) {
					this.window.setSize(window.width + mouseCords.x, window.height);
				} else if (mouseCords.x > this.resizeComponent.getWidth()) {
					this.window.setSize(window.width + mouseCords.x, window.height);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent mouseEvent) {
		}
	}

	/**
	 * TODO: comment ResizeTop
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 4.0
	 * @since 3.0
	 */
	private static final class ResizeTop extends Resize {

		public ResizeTop(Window window, ResizeComponent resizeComponent) {
			super(window, resizeComponent);
		}

		@Override
		public void mouseDragged(MouseEvent mouseEvent) {
			Point mouseCords = mouseEvent.getPoint();
			Rectangle window = this.window.getBounds();
			if (this.resizable) {
				if (this.window.getMinimumSize().height < window.height) {
					this.window.setSize(window.width, window.height - mouseCords.y);
					this.window.setLocation(window.x, window.y + mouseCords.y);
				} else if (mouseCords.y < this.resizeComponent.getY()) {
					this.window.setSize(window.width, window.height - mouseCords.y);
					this.window.setLocation(window.x, window.y + mouseCords.y);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent mouseEvent) {
		}
	}
}

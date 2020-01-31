package editor.menus;

import com.badlogic.gdx.Gdx;
import editor.EditorLauncher;
import editor.popup.cases.CasePopUp;
import game.dnd.DragAndDropBuilder;
import general.hud.EnigmaPanel;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * La classe de base des vues de la popup
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 27/01/2020
 * @since 5.0 27/01/2020
 */
public abstract class AbstractPopUpView extends JDialog {

	public static final int WIDTH = 700, HEIGHT = 300;

	/**
	 * Composant principal de la fenêtre
	 */
	protected EnigmaPanel panel;

	/**
	 * Layout de la fenêtre
	 */
	protected CardLayout layout;

	/**
	 * Parent
	 */
	protected CasePopUp popUp;

	/**
	 * La classe de base des vues de la popup
	 *
	 * @param title titre fenêtre
	 * @param popUp parent
	 */
	protected AbstractPopUpView(String title, CasePopUp popUp) {
		super(EditorLauncher.getInstance().getWindow(), title, false);
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(Gdx.graphics.getWidth() / 2 - WIDTH / 2, Gdx.graphics.getHeight() / 2 - HEIGHT / 2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new Close(this));
		this.layout = new CardLayout();
		this.panel = new EnigmaPanel();
		this.panel.setLayout(this.layout);
		this.add(this.panel);
		this.popUp = popUp;
	}

	/**
	 * Retourne le Layout de la fenêtre
	 *
	 * @return Layout de la fenêtre
	 */
	public CardLayout getCardLayout() {
		return this.layout;
	}

	/**
	 * Composant principal de la fenêtre
	 *
	 * @return Composant principal de la fenêtre
	 */
	public EnigmaPanel getPanel() {
		return this.panel;
	}

	/**
	 * Parent
	 *
	 * @return Parent
	 */
	public CasePopUp getPopUp() {
		return popUp;
	}

	public abstract void clean();


	/**
	 * Reset l'éditor dans son état avant ouverture
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 5.0 26/01/2020
	 * @since 5.0 26/01/2020
	 */
	private static final class Close extends WindowAdapter {

		private final AbstractPopUpView parent;

		public Close(AbstractPopUpView parent) {
			this.parent = parent;
		}

		@Override
		public void windowClosing(WindowEvent e) {
			this.parent.clean();
			DragAndDropBuilder.setForPopup(null);
		}
	}
}


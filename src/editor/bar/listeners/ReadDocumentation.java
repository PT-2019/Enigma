package editor.bar.listeners;

import api.ui.CustomColors;
import api.ui.base.DefaultUIValues;
import api.ui.manager.RadioButtonManager;
import api.utils.Utility;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import common.hud.EnigmaWindow;
import common.hud.ui.EnigmaButtonUI;
import common.language.GameLanguage;
import common.language.HUDFields;
import data.config.Config;
import data.config.EnigmaUIValues;
import editor.bar.doc.DocumentationFile;
import editor.bar.doc.DocumentationTypes;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Ouvre le popup pour lire la documentation
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 11/02/2020
 * @since 6.0 11/02/2020
 */
public class ReadDocumentation extends MenuListener {

	/**
	 * Size
	 */
	private static final int WIDTH = 850, HEIGHT = 600;
	private static final int WIDTH_CATEGORIES = 200, HEIGHT_CATEGORIES = 500;

	/**
	 * Titre
	 */
	private static final String TITLE = GameLanguage.gl.get(HUDFields.DOC);

	/**
	 * Nombre de lignes
	 */
	private static final int ROWS = 10;

	/**
	 * Couleur de fond de base d'un JPanel
	 */
	private static final Color BASE_PANEL_COLOR = new JPanel().getBackground();
	private static final Color BASE_PRESSED_COLOR = Color.BLACK;
	//private static final Color TEXT_COLOR = CustomColors.STEEL_BLUE;

	/**
	 * La fenêtre de documentation
	 */
	private final JDialog dialog;

	/**
	 * Fichier de documentation
	 */
	private ArrayList<DocumentationFile> doc;

	/**
	 * Ouvre le popup pour lire la documentation
	 *
	 * @param window fenêtre
	 * @param parent parent
	 */
	public ReadDocumentation(EnigmaWindow window, Component parent) {
		super(window, parent);
		//préparation de la fenêtre
		this.dialog = new JDialog(window);
		//taille
		this.dialog.setSize(WIDTH, HEIGHT);
		//titre
		this.dialog.setTitle(TITLE);
		//icône enigma
		this.dialog.setIconImage(new ImageIcon(Config.LOGO).getImage());
		//centré X et Y
		this.dialog.setLocation(Utility.getAlignCenterPosition(this.dialog));
		//cachée tant qu'aucun clic
		this.dialog.setVisible(false);
		//si on la ferme, alors on la cache
		this.dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		//si changement, alors on recalcule sa position pour la centrer
		this.dialog.addWindowStateListener(e -> {
			Window win = e.getWindow();
			win.setLocation(Utility.getAlignCenterPosition(win));
		});
		//charge la documentation
		this.doc = DocumentationFile.loadDocumentation(GameLanguage.gl.getLanguage().doc);
		//rempli la fenêtre avec les valeurs lues
		this.initDialog();
	}

	/**
	 * Rempli
	 */
	private void initDialog() {
		//init
		EnigmaPanel categories = new EnigmaPanel();
		EnigmaPanel content = new EnigmaPanel();

		//layout
		content.setLayout(new CardLayout());
		categories.setLayout(new GridLayout(ROWS, 1));

		//rempli
		this.fillCategories(categories, content);

		//Scroll
		JScrollPane categoriesScroll = new JScrollPane(categories);
		categoriesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		categoriesScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		//settings
		categoriesScroll.setPreferredSize(new Dimension(WIDTH_CATEGORIES, HEIGHT_CATEGORIES));
		categoriesScroll.setBackground(EnigmaUIValues.ENIGMA_PANEL_BACKGROUND);
		content.getComponentUI().setAllBackgrounds(BASE_PANEL_COLOR);
		categoriesScroll.setOpaque(true);

		//add
		this.dialog.setLayout(new BorderLayout());
		this.dialog.add(categoriesScroll, BorderLayout.WEST);
		this.dialog.add(content, BorderLayout.CENTER);
	}

	/**
	 * Crée le menu des catégories
	 *
	 * @param categories panneau des catégories
	 * @param content    panneau du contenu
	 */
	private void fillCategories(EnigmaPanel categories, EnigmaPanel content) {
		final boolean[] SHOWED = new boolean[4];
		//bordure bas uniquement affichée
		SHOWED[EnigmaUIValues.BOTTOM_BORDER] = true;
		final boolean[] NONE = DefaultUIValues.ALL_BORDER_HIDDEN;

		//style des boutons de catégorie
		EnigmaButtonUI buttonUI = new EnigmaButtonUI();
		buttonUI.setAllShowedBorders(SHOWED, SHOWED, NONE);
		buttonUI.setAllBorders(EnigmaUIValues.ENIGMA_BUTTON_BORDER);
		buttonUI.setHoveredBackground(EnigmaUIValues.ENIGMA_PANEL_BACKGROUND);
		buttonUI.setHoveredForeground(EnigmaUIValues.ENIGMA_BUTTON_HOVERED_FOREGROUND);
		buttonUI.setPressedBackground(BASE_PANEL_COLOR);
		buttonUI.setPressedForeground(BASE_PRESSED_COLOR);
		buttonUI.setSelectedBackground(BASE_PANEL_COLOR);
		buttonUI.setSelectedForeground(BASE_PRESSED_COLOR);
		buttonUI.setSelectedHoveredBackground(BASE_PANEL_COLOR);
		buttonUI.setSelectedHoveredForeground(BASE_PRESSED_COLOR);
		buttonUI.setSelectedPressedBackground(BASE_PANEL_COLOR);
		buttonUI.setSelectedPressedForeground(BASE_PRESSED_COLOR);

		//un seul bouton sélectionné
		RadioButtonManager manager = new RadioButtonManager(true);

		for (DocumentationFile documentationCategory : this.doc) {
			EnigmaButton category = new EnigmaButton(documentationCategory.getTitle());
			//ui
			category.setComponentUI(buttonUI);
			//survol
			categories.setToolTipText(documentationCategory.getToolTip());
			//listener
			category.addActionListener(new ShowCategory(content));
			//load
			loadContent(documentationCategory, content);
			//manager
			manager.add(category);
			//ajout
			categories.add(category);
		}

		manager.getSelected()[0].doClick();
	}

	/**
	 * Charge la documentation d'une catégorie
	 *
	 * @param documentationCategory documentation d'une catégorie
	 * @param content               panneau dans lequel charger
	 */
	private void loadContent(DocumentationFile documentationCategory, EnigmaPanel content) {
		final Color BACKGROUND = CustomColors.TILED_GRAY_DARK, FOREGROUND = Color.WHITE;
		final Font TXT = new Font("Calibri", Font.BOLD, 15);
		final Font TITLE_F = new Font("Calibri", Font.BOLD, 40);
		final Font TITLE_SMALL_F = new Font("Calibri", Font.BOLD, 25);

		EnigmaPanel panel = new EnigmaPanel(new GridBagLayout());
		panel.getComponentUI().setAllBackgrounds(BACKGROUND);

		//contraintes générales
		GridBagConstraints gdc = new GridBagConstraints();
		gdc.anchor = GridBagConstraints.WEST;
		gdc.gridwidth = 1;
		gdc.weightx = 1;
		gdc.gridx = 0;
		gdc.insets = new Insets(10, 20, 10, 20);
		gdc.fill = GridBagConstraints.HORIZONTAL;

		//ajoute le contenu
		ArrayList<DocumentationFile.DocItems> docItems = documentationCategory.getDocItems();
		for (int i = 0; i < docItems.size(); i++) {
			DocumentationFile.DocItems items = docItems.get(i);
			DocumentationTypes documentationTypes = DocumentationTypes.valueOf(items.getType());
			switch (documentationTypes) {
				case TITLE:
				case TITLE_SMALL:
					EnigmaLabel title = new EnigmaLabel(items.getContent()[0]);
					title.setHorizontalAlignment(EnigmaLabel.LEFT);
					//style
					title.getComponentUI().setAllBackgrounds(BACKGROUND);
					if (documentationTypes.equals(DocumentationTypes.TITLE_SMALL))
						title.getComponentUI().setFont(TITLE_SMALL_F);
					else title.getComponentUI().setFont(TITLE_F);
					title.getComponentUI().setAllForegrounds(BASE_PRESSED_COLOR);
					//add
					gdc.gridy = i;
					panel.add(title, gdc);
					break;
				case MESSAGE:
					EnigmaTextArea area = new EnigmaTextArea();
					StringBuilder sb = new StringBuilder();
					for (String s : items.getContent()) {
						sb.append(s);
					}
					area.setText(sb.toString());
					//Style
					area.setEditable(false);
					area.setWrapStyleWord(true);
					area.getComponentUI().setFont(TXT);
					area.getComponentUI().setAllBackgrounds(BACKGROUND);
					area.getComponentUI().setAllForegrounds(FOREGROUND);
					area.setMargin(new Insets(10, 10, 10, 10));
					//add
					gdc.gridy = i;
					panel.add(area, gdc);
					break;
				case IMAGE:
					EnigmaLabel icon = new EnigmaLabel(new ImageIcon(items.getContent()[0]));
					icon.setHorizontalAlignment(EnigmaLabel.CENTER);
					//style
					icon.getComponentUI().setAllBackgrounds(BACKGROUND);
					icon.getComponentUI().setAllForegrounds(FOREGROUND);
					icon.getComponentUI().setFont(EnigmaUIValues.ENIGMA_TITLE_FONT);
					//add
					gdc.gridy = i;
					panel.add(icon, gdc);
					break;
			}
		}

		//la clef du card layout est le contenu du bouton
		JScrollPane contentScroll = new JScrollPane(panel);
		contentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		content.add(contentScroll, documentationCategory.getTitle());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dialog.setVisible(true);
	}

	/**
	 * Affiche un catégorie
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 11/02/2020
	 * @since 6.0 11/02/2020
	 */
	private static final class ShowCategory implements ActionListener {

		/**
		 * Valeur minimale du scroll (pour le mettre tout en haut)
		 */
		private static final int MIN_VALUE = 0;

		private final EnigmaPanel content;

		ShowCategory(EnigmaPanel content) {
			this.content = content;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			((CardLayout) this.content.getLayout()).show(this.content, e.getActionCommand());

			//reset du scroll
			for (Component c : this.content.getComponents()) {
				if (c instanceof JScrollPane) {
					((JScrollPane) c).getVerticalScrollBar().setValue(MIN_VALUE);
				}
			}
		}
	}
}

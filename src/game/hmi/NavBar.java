package game.hmi;

import common.hud.EnigmaButton;
import common.hud.EnigmaPanel;
import common.hud.ui.EnigmaButtonUI;
import data.NeedToBeTranslated;
import data.config.EnigmaUIValues;
import game.hmi.listener.redirect.CreateRedirectListener;
import game.hmi.listener.redirect.MultiRedirectListener;
import game.hmi.listener.redirect.SoloRedirectListener;

import java.awt.*;

/**
 * Barre de navigation
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class NavBar extends Content {
    /**
     * Instance
     */
    private final static NavBar instance = new NavBar();
    /**
     * Bouton pour les parties solo
     */
    private EnigmaButton solo;
    /**
     * Bouron pour les parties multijoueurs
     */
    private EnigmaButton multi;
    /**
     * Bouton pour créer une partie
     */
    private EnigmaButton create;
    /**
     * Style normal d'un bouton
     */
    private EnigmaButtonUI normalUI;
    /**
     * Style d'in bouton sélectionné
     */
    private EnigmaButtonUI selectedUI;

    /**
     * Etats
     */
    public final static int SOLO_STATE = 0;
    public final static int MULTI_STATE = 1;
    public final static int CREATE_STATE = 2;

    /**
     * Textes
     */
    private final static String SOLO = NeedToBeTranslated.SOLO_TITLE;
    private final static String MULTI = NeedToBeTranslated.MULTI_TITLE;
    private final static String CREATE = NeedToBeTranslated.CREATE_TITLE;

    private NavBar() {
        super(new EnigmaPanel());
        Color red = new Color(200, 0, 0);

        this.solo = new EnigmaButton(SOLO);
        this.multi = new EnigmaButton(MULTI);
        this.create = new EnigmaButton(CREATE);

        this.normalUI = new EnigmaButtonUI();
        this.normalUI.setAllBackgrounds(Color.DARK_GRAY,Color.GRAY,Color.GRAY);
        this.normalUI.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN);
        this.normalUI.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.selectedUI = new EnigmaButtonUI();
        this.selectedUI.setAllBackgrounds(red,red,red);
        this.selectedUI.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN);
        this.selectedUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.selectedUI.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.initContent();
        this.refresh(NO_PRECISED_STATE);
    }

    /**
     * Initialise le contenu
     * Doit être normalement appelé qu'une fois, dans le constructeur
     */
    @Override
    public void initContent() {
        this.content.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.content.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);

        this.solo.setComponentUI(this.normalUI);
        this.multi.setComponentUI(this.normalUI);
        this.create.setComponentUI(this.normalUI);

        this.solo.addActionListener(new SoloRedirectListener());
        this.multi.addActionListener(new MultiRedirectListener());
        this.create.addActionListener(new CreateRedirectListener());

        this.content.add(this.solo);
        this.content.add(this.multi);
        this.content.add(this.create);
    }

    /**
     * Rafraichi l'affichage
     * @param state Etat
     */
    @Override
    public void refresh(int state) {
        this.solo.setComponentUI(this.normalUI);
        this.multi.setComponentUI(this.normalUI);
        this.create.setComponentUI(this.normalUI);

        switch (state){
            case SOLO_STATE:
            default:
                this.solo.setComponentUI(this.selectedUI);
                break;
            case MULTI_STATE:
                this.multi.setComponentUI(this.selectedUI);
                break;
            case CREATE_STATE:
                this.create.setComponentUI(this.selectedUI);
                break;
        }

        this.content.revalidate();
    }

    /**
     * Obtenir l'instance
     * @return Instance
     */
    public static NavBar getInstance(){
        return instance;
    }
}
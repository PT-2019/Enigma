package game.hmi;

import common.hud.EnigmaButton;
import common.hud.EnigmaPanel;
import common.hud.ui.EnigmaButtonUI;
import data.NeedToBeTranslated;
import data.config.EnigmaUIValues;
import game.hmi.listener.action.CreateListener;
import game.hmi.listener.action.ExportListener;
import game.hmi.listener.action.ImportListener;
import game.hmi.listener.action.LaunchListener;
import game.hmi.listener.redirect.MultiRedirectListener;

import java.awt.*;

/**
 * Barre d'actions
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class ActionBar extends Content {
    /**
     * Instance
     */
    private final static ActionBar instance = new ActionBar();
    /**
     * Affichages
     */
    private CardLayout layout;

    /**
     * Etats
     */
    public final static int QUIT_AND_LAUNCH_STATE = 0;
    public final static int QUIT_STATE = 2;
    public final static int CREATE_STATE = 3;

    /**
     * Textes
     */
    private final static String LAUNCH = NeedToBeTranslated.LAUNCH;
    private final static String QUIT = NeedToBeTranslated.QUIT;
    private final static String CREATE = NeedToBeTranslated.CREATE;
    private final static String IMPORT = NeedToBeTranslated.IMPORT;

    private ActionBar() {
        super(new EnigmaPanel());

        this.layout = new CardLayout();

        this.initContent();
        this.refresh(NO_PRECISED_STATE);
    }

    /**
     * Initialise le contenu
     * Doit être normalement appelé qu'une fois, dans le constructeur
     */
    @Override
    public void initContent() {
        EnigmaButtonUI bui = new EnigmaButtonUI();
        bui.setAllBackgrounds(Color.DARK_GRAY,Color.GRAY,Color.GRAY);
        bui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN);
        bui.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.content.setLayout(this.layout);

        EnigmaButton import1 = new EnigmaButton(IMPORT);
        EnigmaButton launch = new EnigmaButton(LAUNCH);
        EnigmaButton quit1 = new EnigmaButton(QUIT);
        EnigmaButton quit2 = new EnigmaButton(QUIT);
        EnigmaButton create = new EnigmaButton(CREATE);
        EnigmaPanel voidP = new EnigmaPanel();
        EnigmaPanel quitP = new EnigmaPanel();
        EnigmaPanel createP = new EnigmaPanel();
        EnigmaPanel launchQuitP = new EnigmaPanel();

        voidP.setLayout(new FlowLayout(FlowLayout.TRAILING));
        quitP.setLayout(new FlowLayout(FlowLayout.TRAILING));
        createP.setLayout(new FlowLayout(FlowLayout.TRAILING));
        launchQuitP.setLayout(new FlowLayout(FlowLayout.TRAILING));

        import1.setComponentUI(bui);
        launch.setComponentUI(bui);
        quit1.setComponentUI(bui);
        quit2.setComponentUI(bui);
        create.setComponentUI(bui);

        import1.addActionListener(new ImportListener());
        quit1.addActionListener(new MultiRedirectListener());
        quit2.addActionListener(new MultiRedirectListener());
        create.addActionListener(new CreateListener());
        launch.addActionListener(new LaunchListener());

        voidP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        quitP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        createP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        launchQuitP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);

        voidP.add(import1);
        quitP.add(quit1);
        createP.add(create);
        launchQuitP.add(launch);
        launchQuitP.add(quit2);

        this.content.add(voidP,String.valueOf(NO_PRECISED_STATE));
        this.content.add(quitP,String.valueOf(QUIT_STATE));
        this.content.add(createP,String.valueOf(CREATE_STATE));
        this.content.add(launchQuitP,String.valueOf(QUIT_AND_LAUNCH_STATE));
    }

    /**
     * Rafraichi l'affichage
     * @param state Etat
     */
    @Override
    public void refresh(int state) {
        this.layout.show(this.content,String.valueOf(state));

        this.content.revalidate();
    }

    /**
     * Obtenir l'instance
     * @return Instance
     */
    public static ActionBar getInstance(){
        return instance;
    }
}

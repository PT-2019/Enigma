package game.hmi;

import common.hud.EnigmaButton;
import common.hud.EnigmaPanel;
import common.hud.ui.EnigmaButtonUI;

import java.awt.*;

public class ActionBar extends Content {
    private final static ActionBar instance = new ActionBar();
    private CardLayout layout;

    /**
     * Etats
     */
    public final static int DELETE_AND_LAUNCH_STATE = 0;
    public final static int QUIT_AND_LAUNCH_STATE = 1;
    public final static int DELETE_STATE = 2;
    public final static int LAUNCH_STATE = 3;
    public final static int QUIT_STATE = 4;

    /**
     * Textes
     */
    private final static String DELETE = "Supprimer";
    private final static String LAUNCH = "Commencer";
    private final static String QUIT = "Quitter";

    private ActionBar() {
        super(new EnigmaPanel());

        this.layout = new CardLayout();

        this.initContent();
        this.refresh(NO_PRECISED_STATE);
    }

    @Override
    public void initContent() {
        EnigmaButtonUI bui = new EnigmaButtonUI();
        bui.setAllBackgrounds(Color.DARK_GRAY,Color.GRAY,Color.GRAY);
        bui.setAllBorders(Color.DARK_GRAY,Color.WHITE,Color.WHITE);
        bui.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.content.setLayout(this.layout);

        EnigmaButton delete = new EnigmaButton(DELETE);
        EnigmaButton launch = new EnigmaButton(LAUNCH);
        EnigmaButton quit = new EnigmaButton(QUIT);
        EnigmaPanel deleteP = new EnigmaPanel();
        EnigmaPanel launchP = new EnigmaPanel();
        EnigmaPanel quitP = new EnigmaPanel();
        EnigmaPanel launchQuitP = new EnigmaPanel();
        EnigmaPanel deleteLaunchP = new EnigmaPanel();

        deleteP.setLayout(new FlowLayout(FlowLayout.RIGHT));
        launchP.setLayout(new FlowLayout(FlowLayout.RIGHT));
        quitP.setLayout(new FlowLayout(FlowLayout.RIGHT));
        launchQuitP.setLayout(new FlowLayout(FlowLayout.RIGHT));
        deleteLaunchP.setLayout(new FlowLayout(FlowLayout.RIGHT));

        delete.setComponentUI(bui);
        launch.setComponentUI(bui);
        quit.setComponentUI(bui);

        deleteP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        launchP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        quitP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        launchQuitP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        deleteLaunchP.getComponentUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);

        deleteP.add(delete);
        launchP.add(launch);
        quitP.add(quit);
        launchQuitP.add(launch);
        launchQuitP.add(quit);
        deleteLaunchP.add(launch);
        deleteLaunchP.add(delete);

        this.content.add(deleteP,String.valueOf(DELETE_STATE));
        this.content.add(launchP,String.valueOf(LAUNCH_STATE));
        this.content.add(quitP,String.valueOf(QUIT_STATE));
        this.content.add(launchQuitP,String.valueOf(QUIT_AND_LAUNCH_STATE));
        this.content.add(deleteLaunchP,String.valueOf(DELETE_AND_LAUNCH_STATE));
    }

    @Override
    public void refresh(int state) {
        if(state > NO_PRECISED_STATE)
            this.layout.show(this.content,String.valueOf(state));

        this.content.revalidate();
    }

    public static ActionBar getInstance(){
        return instance;
    }
}

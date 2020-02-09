package game.hmi.content;

import api.ui.CustomWindow;
import common.data.GameData;
import common.hud.EnigmaAlert;
import common.hud.EnigmaTextArea;

import java.awt.*;

public class SeeMore {
    private EnigmaAlert window;

    /**
     * Textes
     */
    private final static String NAME = "Nom";
    private final static String AUTHOR = "Auteur";
    private final static String MAP = "Map";
    private final static String DESCRIPTION = "Description";
    private final static String NB_PLAYERS = "Nombre de joueurs";
    private final static String DURATION = "Durée";
    private final static String MULTI_PLAYERS = "Multijoueurs";
    private final static String YES = "Oui";
    private final static String NO = "Non";

    public SeeMore(GameData data) {
        this.window = new EnigmaAlert();

        String isMulti = NO;
        if(data.getMaxPlayers() > 1){
            isMulti = YES;
        }

        EnigmaTextArea area = new EnigmaTextArea();
        area.setEditable(false);
        area.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        area.setText(NAME + ": " + data.getName() + "\n\n"
                + DESCRIPTION + ": " + data.getDescription() + "\n\n"
                + AUTHOR + ": " + data.getAuthor() + "\n\n"
                + MAP + ": " + data.getMapName() + "\n\n"
                + MULTI_PLAYERS + ": " + isMulti + "\n\n"
                + NB_PLAYERS + ": " + data.getMaxPlayers() + "\n\n"
                + DURATION + ": " + data.getDuration() + " min"
        );

        this.window.getContentSpace().setLayout(new BorderLayout());
        this.window.getContentSpace().add(area.setScrollBar());
        this.window.setWindowBackground(Color.DARK_GRAY);
        this.window.setHalfScreenSize();
        this.window.showBorder(Color.WHITE,1);
        this.window.setLocation(CustomWindow.CENTER);
        this.window.setResizable(false);
        this.window.setModal(true);
    }

    public void show(boolean show){
        this.window.setVisible(show);
    }
}

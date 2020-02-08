package game.hmi.content;

import api.utils.Utility;
import common.data.GameData;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.ui.EnigmaButtonUI;
import common.save.DataSave;
import common.utils.Logger;
import data.config.Config;
import data.config.EnigmaUIValues;
import game.hmi.Content;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Solo extends Content {
    private final static Solo instance = new Solo();
    private EnigmaPanel listComponent;
    private EnigmaButtonUI normalUI;

    /**
     * Textes
     */
    private final static String DELETE = "Supprimer";
    private final static String PLAY = "Jouer";
    private final static String NAME = "Nom";
    private final static String AUTHOR = "Auteur";
    private final static String MAP = "Map";
    private final static String DESCRIPTION = "Description";
    private final static String NB_PLAYERS = "Nombre de joueurs";
    private final static String DURATION = "Durée";

    private Solo() {
        super(new EnigmaPanel());
        int borderSize = 20;

        this.content.setLayout(new BorderLayout());
        this.listComponent = new EnigmaPanel();
        JScrollPane scroll = new JScrollPane(this.listComponent);

        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.content.add(scroll);
        this.content.setBorder(BorderFactory.createEmptyBorder(borderSize,borderSize,borderSize,borderSize));

        this.normalUI = new EnigmaButtonUI();
        this.normalUI.setAllBackgrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);
        this.normalUI.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN);
        this.normalUI.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.initContent();
        this.refresh(NO_PRECISED_STATE);
    }

    @Override
    public void initContent(){}

    @Override
    public void refresh(int state) {
        ArrayList<String> games = Utility.getAllGameName();
        int size = 5;
        int borderSize = 5;
        int borderSize2 = 2;

        if(games.size() > size)
            size = games.size();

        this.listComponent.removeAll();
        this.listComponent.setLayout(new GridLayout(size,1));

        EnigmaButtonUI bui = new EnigmaButtonUI();
        bui.setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        bui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN);
        bui.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        for(String game : games){
            GameData data;
            try {
                data = DataSave.readGameData(Config.GAME_DATA_FOLDER + game + Config.DATA_EXTENSION);
            } catch (IOException e) {
                Logger.printError("Solo.java","read game data error : " + e.getMessage());
                continue;
            }

            if(!data.isMultiPlayer()) {

                EnigmaPanel element = new EnigmaPanel();
                EnigmaPanel eTexts = new EnigmaPanel();
                EnigmaPanel eValues = new EnigmaPanel();
                EnigmaPanel eButtons = new EnigmaPanel();
                EnigmaButton play = new EnigmaButton(PLAY);
                EnigmaButton delete = new EnigmaButton(DELETE);
                EnigmaLabel name = new EnigmaLabel(NAME + ": " + data.getName());
                EnigmaLabel description = new EnigmaLabel(DESCRIPTION + ": " + data.getDescription());
                EnigmaLabel author = new EnigmaLabel(AUTHOR + ": " + data.getAuthor());
                EnigmaLabel map = new EnigmaLabel(MAP + ": " + data.getMapName());
                EnigmaLabel nbPlayers = new EnigmaLabel(NB_PLAYERS + ": " + data.getMaxPlayers());
                EnigmaLabel duration = new EnigmaLabel(DURATION + ": " + data.getDuration() + " min");

                element.setPreferredSize(new Dimension(element.getPreferredSize().width, 130));

                element.setLayout(new GridLayout(1, 3));
                eTexts.setLayout(new GridLayout(3, 1));
                eValues.setLayout(new GridLayout(3, 1));
                eButtons.setLayout(new GridLayout(2, 1));

                eValues.setBorder(BorderFactory.createMatteBorder(0, borderSize2, 0, 0, Color.DARK_GRAY));
                eButtons.setBorder(BorderFactory.createMatteBorder(0, borderSize2, 0, 0, Color.DARK_GRAY));

                eTexts.add(name);
                eTexts.add(description);
                eTexts.add(author);
                eValues.add(map);
                eValues.add(nbPlayers);
                eValues.add(duration);
                eButtons.add(play);
                eButtons.add(delete);
                element.add(eTexts);
                element.add(eValues);
                element.add(eButtons);

                name.setHorizontalAlignment(SwingConstants.LEFT);
                description.setHorizontalAlignment(SwingConstants.LEFT);
                author.setHorizontalAlignment(SwingConstants.LEFT);
                map.setHorizontalAlignment(SwingConstants.LEFT);
                duration.setHorizontalAlignment(SwingConstants.LEFT);
                nbPlayers.setHorizontalAlignment(SwingConstants.LEFT);

                element.setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.DARK_GRAY));
                element.getComponentUI().setAllBackgrounds(Color.GRAY);
                name.getComponentUI().setAllBackgrounds(Color.GRAY);
                description.getComponentUI().setAllBackgrounds(Color.GRAY);
                author.getComponentUI().setAllBackgrounds(Color.GRAY);
                map.getComponentUI().setAllBackgrounds(Color.GRAY);
                duration.getComponentUI().setAllBackgrounds(Color.GRAY);
                nbPlayers.getComponentUI().setAllBackgrounds(Color.GRAY);
                play.setComponentUI(bui);
                play.setBorderPainted(true);
                play.getComponentUI().setHoveredBackground(new Color(100, 214, 20));
                delete.setComponentUI(bui);
                delete.setBorderPainted(true);
                delete.getComponentUI().setHoveredBackground(new Color(193, 7, 0));

                name.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
                description.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
                author.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
                map.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
                nbPlayers.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
                duration.setBorder(BorderFactory.createMatteBorder(0, borderSize, 0, 0, Color.GRAY));
                play.setBorder(BorderFactory.createMatteBorder(0, 0, borderSize2 / 2, 0, Color.DARK_GRAY));
                delete.setBorder(BorderFactory.createMatteBorder(borderSize2 / 2, 0, 0, 0, Color.DARK_GRAY));

                this.listComponent.add(element);
            }
        }
    }

    public static Solo getInstance(){
        return instance;
    }
}

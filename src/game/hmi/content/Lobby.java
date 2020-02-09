package game.hmi.content;

import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaTextArea;
import common.hud.ui.EnigmaLabelUI;
import data.config.GameConfiguration;
import data.config.UserConfiguration;
import game.hmi.Content;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Lobby extends Content {
    private final static Lobby instance = new Lobby();
    private EnigmaTextArea game;
    private EnigmaLabel players;
    private EnigmaPanel playersList;
    private EnigmaLabelUI nameNormalUI;
    private EnigmaLabelUI leadNormalUI;

    /**
     * Textes
     */
    private final static String NAME = "Nom";
    private final static String MAP = "Map";
    private final static String DESCRIPTION = "Description";
    private final static String DURATION = "Durée";
    private final static String PLAYER_WAIT = "En attente de joueurs";
    private final static String LEADER_WAIT = "En attente du chef de la partie";
    private final static String LEADER = "Chef";

    private Lobby() {
        super(new EnigmaPanel());
        int borderSize = 6;

        this.game = new EnigmaTextArea();
        this.players = new EnigmaLabel();
        this.playersList = new EnigmaPanel();

        this.content.setLayout(new GridLayout(1,2));
        this.playersList.setLayout(new GridLayout(GameConfiguration.MAX_PLAYERS,1));

        this.nameNormalUI = new EnigmaLabelUI();
        this.nameNormalUI.setAllBackgrounds(Color.GRAY);

        this.leadNormalUI = new EnigmaLabelUI();
        this.leadNormalUI.setAllBackgrounds(Color.GRAY);
        this.leadNormalUI.setAllForegrounds(Color.GRAY);

        this.players.setComponentUI(this.nameNormalUI);
        this.players.setBorder(BorderFactory.createMatteBorder(0,0,0,borderSize,Color.DARK_GRAY));

        this.initContent();
        this.refresh(NO_PRECISED_STATE);
    }

    @Override
    public void initContent() {
        EnigmaPanel playersComponent = new EnigmaPanel();
        EnigmaPanel gameComponent = new EnigmaPanel();
        int borderSize = 6;

        gameComponent.setLayout(new BorderLayout());
        gameComponent.setBorder(BorderFactory.createMatteBorder(borderSize,borderSize,borderSize,borderSize,Color.GRAY));
        this.game.setEditable(false);
        this.game.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        this.game.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.content.setBorder(BorderFactory.createMatteBorder(borderSize,borderSize,borderSize,borderSize,Color.DARK_GRAY));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;

        playersComponent.setLayout(new GridBagLayout());

        gbc.gridy = 1;
        gbc.weighty = 1;
        playersComponent.add(this.players,gbc);
        gbc.gridy = 2;
        gbc.weighty = 10;
        playersComponent.add(this.playersList,gbc);

        gameComponent.add(this.game,BorderLayout.CENTER);
        this.content.add(playersComponent);
        this.content.add(gameComponent);
    }

    @Override
    public void refresh(int state) {
        Color red = new Color(200, 0, 0);
        Color blue = new Color(0, 136, 193);
        int borderSize = 6;
        int totalPlayers = GameConfiguration.getInstance().getTotalPlayers();
        int maxPlayers = GameConfiguration.getInstance().getMaxGamePlayers();
        GameConfiguration gameConfig = GameConfiguration.getInstance();
        String waitWhat = PLAYER_WAIT;
        if(totalPlayers == maxPlayers)
            waitWhat = LEADER_WAIT;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;

        this.playersList.removeAll();

        this.game.setText(NAME + ": " + gameConfig.getName() + "\n\n"
                + DESCRIPTION + ": " + gameConfig.getDescription() + "\n\n"
                + MAP + ": " + gameConfig.getMap() + "\n\n"
                + DURATION + ": " + gameConfig.getDuration() + " min"
        );

        this.players.setText(waitWhat + ": " + totalPlayers + "/" + maxPlayers);

        ArrayList<String> players = gameConfig.getAllPlayers();
        for(int i = 0; i < GameConfiguration.MAX_PLAYERS; i++){
            EnigmaPanel playerContent = new EnigmaPanel();
            EnigmaLabel lead = new EnigmaLabel(LEADER);
            EnigmaLabel player = new EnigmaLabel();

            player.setComponentUI(this.nameNormalUI);
            lead.setComponentUI(this.leadNormalUI);
            lead.setBorder(BorderFactory.createEmptyBorder(borderSize,borderSize,borderSize,borderSize));
            player.setBorder(BorderFactory.createMatteBorder(0,borderSize,0,0,Color.DARK_GRAY));
            playerContent.setBorder(BorderFactory.createMatteBorder(borderSize,0,0,borderSize,Color.DARK_GRAY));
            playerContent.setLayout(new GridBagLayout());

            if(i < players.size()){
                String name = players.get(i);
                player.setText(name);

                if(UserConfiguration.getInstance().getData().getName().equals(name)) {
                    player.getComponentUI().setAllBackgrounds(blue);
                    lead.getComponentUI().setAllBackgrounds(blue);
                    lead.getComponentUI().setAllForegrounds(blue);
                }

                if(gameConfig.getOwner().equals(name)) {
                    lead.getComponentUI().setAllForegrounds(Color.WHITE);
                    lead.getComponentUI().setAllBackgrounds(red);
                }
            }

            gbc.gridx = 1;
            gbc.weightx = 0;
            playerContent.add(lead,gbc);
            gbc.gridx = 2;
            gbc.weightx = 1;
            playerContent.add(player,gbc);
            this.playersList.add(playerContent);
        }

        this.content.revalidate();
    }

    public static Lobby getInstance(){
        return instance;
    }
}

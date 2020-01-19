package game.display;

import editor.enigma.Enigma;
import editor.entity.player.Player;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.hud.EnigmaTextArea;
import game.GameConfiguration;

import javax.swing.*;
import java.awt.*;

public class ContentDisplayManager {

    private final static ContentDisplayManager instance = new ContentDisplayManager();
    private EnigmaPanel panel;
    private CardLayout layout;

    private ContentDisplayManager(){
        GameConfiguration.getInstance().setMultiPlayer(true);
        GameConfiguration.getInstance().setMaxGamePlayers(GameConfiguration.MAX_PLAYERS);
        GameConfiguration.getInstance().playerJoined(new Player("aszdf"));
        GameConfiguration.getInstance().playerJoined(new Player("zerstrhtgjyh"));
        //GameConfiguration.getInstance().playerJoined(new Player("bite non binaire"));
        this.layout = new CardLayout();
        this.panel = new EnigmaPanel();
        this.panel.setLayout(this.layout);
        this.panel.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.panel.add(this.initSelectGame(),LaunchGameDisplay.SELECT_GAME);
        this.panel.add(this.initJoinGame(),LaunchGameDisplay.JOIN_GAME);
        this.panel.add(this.initWaitPlayers(),LaunchGameDisplay.WAIT_PLAYERS);
        this.panel.add(this.initWaitPlayersLeader(),LaunchGameDisplay.WAIT_PLAYERS_LEADER);
    }

    private EnigmaPanel initSelectGame(){
        EnigmaPanel displayContent = new EnigmaPanel();
        EnigmaPanel navBar = new EnigmaPanel();
        EnigmaPanel content = new EnigmaPanel();
        JScrollPane scrollContent = new JScrollPane(content);
        scrollContent.setBorder(BorderFactory.createEmptyBorder());
        GridBagConstraints gbc = new GridBagConstraints();

        displayContent.setLayout(new GridBagLayout());
        int inset = 10;

        navBar.setLayout(new GridLayout(1,2));
        navBar.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        EnigmaButton launchGame = new EnigmaButton("Lancer une partie");
        launchGame.getButtonUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        launchGame.getButtonUI().setAllBorders(null,null,null);
        launchGame.getButtonUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        navBar.add(launchGame);
        EnigmaButton joinGame = new EnigmaButton("Rejoindre une partie");
        joinGame.getButtonUI().setAllBackgrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);
        joinGame.getButtonUI().setAllBorders(null,null,null);
        joinGame.getButtonUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        navBar.add(joinGame);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        displayContent.add(navBar,gbc);

        content.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        content.setLayout(new GridBagLayout());
        EnigmaPanel gameList = new EnigmaPanel();
        // TODO: 01/01/2020 afficher dans gameList, une liste des maps en local
        gbc.insets = new Insets(inset,inset,inset,inset);
        content.add(gameList,gbc);

        gbc.gridy = 2;
        gbc.weighty = 15;
        gbc.insets = new Insets(0,0,0,0);
        displayContent.add(scrollContent,gbc);

        return displayContent;
    }

    public EnigmaPanel initJoinGame(){
        Color grey = new Color(100,100,100);
        Color lighterGrey = new Color(150,150,150);

        EnigmaPanel displayContent = new EnigmaPanel();
        EnigmaPanel navBar = new EnigmaPanel();
        EnigmaPanel content = new EnigmaPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        displayContent.setLayout(new GridBagLayout());
        int inset = 100;

        navBar.setLayout(new GridLayout(1,2));
        navBar.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        EnigmaButton launchGame = new EnigmaButton("Lancer une partie");
        launchGame.getButtonUI().setAllBackgrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);
        launchGame.getButtonUI().setAllBorders(null,null,null);
        launchGame.getButtonUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        navBar.add(launchGame);
        EnigmaButton joinGame = new EnigmaButton("Rejoindre une partie");
        joinGame.getButtonUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinGame.getButtonUI().setAllBorders(null,null,null);
        joinGame.getButtonUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        navBar.add(joinGame);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        displayContent.add(navBar,gbc);

        content.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        content.setLayout(new GridBagLayout());
        EnigmaPanel joinComponent = new EnigmaPanel();
        joinComponent.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinComponent.setLayout(new GridLayout(4,1));
        EnigmaPanel joinPanel = new EnigmaPanel();
        joinComponent.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinPanel.setLayout(new BorderLayout());
        EnigmaLabel label = new EnigmaLabel("Entrez l'adresse IP :");
        EnigmaTextArea input = new EnigmaTextArea();

        label.setVerticalAlignment(SwingConstants.BOTTOM);
        label.getLabelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        label.getLabelUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        input.setRows(1);
        input.getTextAreaUI().setAllBackgrounds(grey,grey,grey);

        joinPanel.add(label,BorderLayout.CENTER);
        joinPanel.add(input,BorderLayout.SOUTH);

        EnigmaPanel voidPanel = new EnigmaPanel();
        voidPanel.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinComponent.add(voidPanel);

        joinComponent.add(joinPanel);

        EnigmaPanel confirmComponent = new EnigmaPanel();
        confirmComponent.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        confirmComponent.setLayout(new FlowLayout());
        EnigmaButton confirm = new EnigmaButton("Rejoindre");
        confirm.getButtonUI().setAllBackgrounds(grey,lighterGrey,lighterGrey);
        confirm.getButtonUI().setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        confirm.getButtonUI().setAllBorders(null,null,null);
        confirmComponent.add(confirm);
        joinComponent.add(confirmComponent);

        voidPanel = new EnigmaPanel();
        voidPanel.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinComponent.add(voidPanel);

        gbc.insets = new Insets(inset,inset,inset,inset);
        content.add(joinComponent,gbc);

        gbc.gridy = 2;
        gbc.weighty = 15;
        gbc.insets = new Insets(0,0,0,0);
        displayContent.add(content,gbc);

        return displayContent;
    }

    private EnigmaPanel initWaitPlayers() {
        Color grey = new Color(100,100,100);
        Color lighterGrey = new Color(150,150,150);

        EnigmaPanel displayContent = new EnigmaPanel();
        EnigmaPanel content = new EnigmaPanel();
        EnigmaPanel players = new EnigmaPanel();
        EnigmaPanel playersJoin = new EnigmaPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        GameConfiguration gameConfig = GameConfiguration.getInstance();

        content.setLayout(new GridBagLayout());
        displayContent.setLayout(new GridBagLayout());
        playersJoin.setLayout(new GridBagLayout());
        int inset = 50;
        int inset2 = 5;

        content.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        displayContent.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        playersJoin.getPanelUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset2,inset2,inset2,inset2);

        GridBagConstraints gbcP = new GridBagConstraints();
        gbcP.gridy = 1;
        gbcP.gridwidth = 1;
        gbcP.gridheight = 1;
        gbcP.fill = GridBagConstraints.BOTH;
        gbcP.weighty = 1;

        players.setLayout(new GridLayout(GameConfiguration.MAX_PLAYERS,1));
        int i = 1;
        for(Player p: gameConfig.getAllPlayers()){
            gbc.gridx = i;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            EnigmaPanel joined = new EnigmaPanel();
            joined.getPanelUI().setAllBackgrounds(Color.BLACK,Color.BLACK,Color.BLACK);
            playersJoin.add(joined,gbc);

            EnigmaPanel player = new EnigmaPanel();
            EnigmaPanel isLeader = new EnigmaPanel();
            EnigmaLabel name = new EnigmaLabel(p.getName());

            if(p.equals(gameConfig.getOwner()))
                isLeader.getPanelUI().setAllBackgrounds(Color.GREEN,Color.GREEN,Color.GREEN);
            player.setLayout(new GridBagLayout());

            gbcP.gridx = 1;
            gbcP.weightx = 1;
            player.add(isLeader,gbcP);

            gbcP.gridx = 2;
            gbcP.weightx = 5;
            player.add(name,gbcP);

            players.add(player,gbc);
            i++;
        }

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        for(i = gameConfig.getTotalPlayers(); i < gameConfig.getMaxGamePlayers(); i++){
            gbc.gridx = i;
            EnigmaPanel joined = new EnigmaPanel();
            joined.getPanelUI().setAllBackgrounds(Color.LIGHT_GRAY,Color.LIGHT_GRAY,Color.LIGHT_GRAY);
            playersJoin.add(joined,gbc);
        }

        content.add(playersJoin,gbc);

        gbc.gridy = 2;
        gbc.weighty = 2;
        content.add(players,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(inset,inset,inset,inset);
        displayContent.add(content,gbc);

        return displayContent;
    }

    private EnigmaPanel initWaitPlayersLeader() {

        return new EnigmaPanel();
    }

    public void showDisplay(String displayName){
        this.layout.show(this.panel,displayName);
    }

    public static ContentDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}

package game.display;

import editor.entity.Player;
import editor.hud.*;
import game.GameConfiguration;
import game.UserConfiguration;

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
        GameConfiguration.getInstance().setMaxGamePlayers(3);
        this.layout = new CardLayout();
        this.panel = new EnigmaPanel();
        this.panel.setLayout(this.layout);
        this.panel.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.panel.add(this.initSelectGame(),LaunchGameDisplay.SELECT_GAME);
        this.panel.add(JoinGameDisplayManager.getInstance().getContent(),LaunchGameDisplay.JOIN_GAME);
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
        navBar.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        EnigmaButton launchGame = new EnigmaButton("Lancer une partie");
        launchGame.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        launchGame.getComponentUI().setAllBorders(null,null,null);
        launchGame.getComponentUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        navBar.add(launchGame);
        EnigmaButton joinGame = new EnigmaButton("Rejoindre une partie");
        joinGame.getComponentUI().setAllBackgrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);
        joinGame.getComponentUI().setAllBorders(null,null,null);
        joinGame.getComponentUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        navBar.add(joinGame);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        displayContent.add(navBar,gbc);

        content.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
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

    private EnigmaPanel initWaitPlayers() {
        Color grey = new Color(100,100,100);
        Color blue = new Color(50,100,200);
        ImageIcon group = new ImageIcon("assets/icon/player/group.png");
        ImageIcon single_black = new ImageIcon("assets/icon/player/single_black.png");
        ImageIcon single_green = new ImageIcon("assets/icon/player/single_green.png");

        EnigmaPanel displayContent = new EnigmaPanel();
        EnigmaPanel content = new EnigmaPanel();
        EnigmaPanel playersJoin = new EnigmaPanel();
        EnigmaPanel players = new EnigmaPanel();
        EnigmaPanel playersLead = new EnigmaPanel();
        EnigmaPanel playersName = new EnigmaPanel();
        GameConfiguration gameConfig = GameConfiguration.getInstance();

        GridBagConstraints gbcContent = new GridBagConstraints();
        GridBagConstraints gbcPlayersList = new GridBagConstraints();

        content.setLayout(new GridBagLayout());
        displayContent.setLayout(new GridBagLayout());
        playersJoin.setLayout(new GridLayout(1,GameConfiguration.MAX_PLAYERS));
        players.setLayout(new GridBagLayout());
        playersLead.setLayout(new GridLayout(GameConfiguration.MAX_PLAYERS,1));
        playersName.setLayout(new GridLayout(GameConfiguration.MAX_PLAYERS,1));
        int inset = 50;
        int inset2 = 5;
        int borderSize = 4;

        boolean[] bordersB = new boolean[4];
        bordersB[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
        boolean[] bordersR = new boolean[4];
        bordersR[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
        boolean[] bordersBR = new boolean[4];
        bordersBR[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
        bordersBR[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

        content.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        displayContent.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        playersJoin.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        gbcContent.gridy = 1;
        gbcContent.gridwidth = 1;
        gbcContent.gridheight = 1;
        gbcContent.fill = GridBagConstraints.BOTH;
        gbcContent.weightx = 1;
        gbcContent.weighty = 1;
        gbcContent.insets = new Insets(inset2,inset2,inset2,inset2);

        gbcPlayersList.gridx = 1;
        gbcPlayersList.gridy = 1;
        gbcPlayersList.gridwidth = 1;
        gbcPlayersList.gridheight = 1;
        gbcPlayersList.fill = GridBagConstraints.BOTH;
        gbcPlayersList.weightx = 1;
        gbcPlayersList.weighty = 1;
        players.add(playersLead,gbcPlayersList);

        gbcPlayersList.gridx = 2;
        gbcPlayersList.weightx = 5;
        players.add(playersName,gbcPlayersList);

        for(int i = 1; i <= GameConfiguration.MAX_PLAYERS; i++){
            EnigmaPanel joined = new EnigmaPanel();
            joined.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
            if(i > gameConfig.getTotalPlayers())
                joined.getComponentUI().setAllBackgroundImage(single_black, single_black, single_black);
            else
                joined.getComponentUI().setAllBackgroundImage(single_green, single_green, single_green);

            if(i > gameConfig.getMaxGamePlayers())
                joined.getComponentUI().setAllBackgroundImage(null, null, null);

            playersJoin.add(joined);
        }

        for(int i = 0; i < GameConfiguration.MAX_PLAYERS; i++) {

            EnigmaPanel lead = new EnigmaPanel();
            lead.setLayout(new GridBagLayout());
            EnigmaLabel name = new EnigmaLabel();
            if(i < gameConfig.getTotalPlayers()) {
                Player p = gameConfig.getAllPlayers().get(i);
                name.setText(p.getName());

                if(p.equals(UserConfiguration.getInstance().getUser())) {
                    lead.getComponentUI().setAllBackgrounds(blue,blue,blue);
                    name.getComponentUI().setAllBackgrounds(blue,blue,blue);
                }

                if(p.equals(gameConfig.getOwner()))
                    lead.getComponentUI().setAllBackgroundImage(group, group, group);
            }

            if (i < GameConfiguration.MAX_PLAYERS - 1){
                lead.getComponentUI().setAllBorders(grey,grey,grey);
                lead.getComponentUI().setAllShowedBorders(bordersBR,bordersBR,bordersBR);
                lead.getComponentUI().setAllBordersSize(borderSize,borderSize,borderSize);

                name.getComponentUI().setAllBorders(grey,grey,grey);
                name.getComponentUI().setAllShowedBorders(bordersB,bordersB,bordersB);
                name.getComponentUI().setAllBordersSize(borderSize,borderSize,borderSize);
            }else{
                lead.getComponentUI().setAllBorders(grey,grey,grey);
                lead.getComponentUI().setAllShowedBorders(bordersR,bordersR,bordersR);
                lead.getComponentUI().setAllBordersSize(borderSize,borderSize,borderSize);
            }

            playersLead.add(lead);
            playersName.add(name);
        }

        content.add(playersJoin,gbcContent);

        gbcContent.gridy = 2;
        gbcContent.weighty = 2;
        content.add(players,gbcContent);

        gbcContent.gridx = 1;
        gbcContent.gridy = 1;
        gbcContent.insets = new Insets(inset,inset,inset,inset);
        displayContent.add(content,gbcContent);

        return displayContent;
    }

    private EnigmaPanel initWaitPlayersLeader() {
        Color grey = new Color(100,100,100);
        Color blue = new Color(50,100,200);
        ImageIcon group = new ImageIcon("assets/icon/player/group.png");
        ImageIcon single_black = new ImageIcon("assets/icon/player/single_black.png");
        ImageIcon single_green = new ImageIcon("assets/icon/player/single_green.png");

        EnigmaPanel displayContent = new EnigmaPanel();
        EnigmaPanel content = new EnigmaPanel();
        EnigmaPanel playersJoin = new EnigmaPanel();
        EnigmaPanel players = new EnigmaPanel();
        EnigmaPanel playersLead = new EnigmaPanel();
        EnigmaPanel playersName = new EnigmaPanel();
        GameConfiguration gameConfig = GameConfiguration.getInstance();

        GridBagConstraints gbcContent = new GridBagConstraints();
        GridBagConstraints gbcPlayersList = new GridBagConstraints();

        content.setLayout(new GridBagLayout());
        displayContent.setLayout(new GridBagLayout());
        playersJoin.setLayout(new GridLayout(1,GameConfiguration.MAX_PLAYERS));
        players.setLayout(new GridBagLayout());
        playersLead.setLayout(new GridLayout(GameConfiguration.MAX_PLAYERS,1));
        playersName.setLayout(new GridLayout(GameConfiguration.MAX_PLAYERS,1));
        int inset = 50;
        int inset2 = 5;
        int borderSize = 4;

        boolean[] bordersB = new boolean[4];
        bordersB[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
        boolean[] bordersR = new boolean[4];
        bordersR[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
        boolean[] bordersBR = new boolean[4];
        bordersBR[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
        bordersBR[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

        content.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        displayContent.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        playersJoin.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        gbcContent.gridy = 1;
        gbcContent.gridwidth = 1;
        gbcContent.gridheight = 1;
        gbcContent.fill = GridBagConstraints.BOTH;
        gbcContent.weightx = 1;
        gbcContent.weighty = 1;
        gbcContent.insets = new Insets(inset2,inset2,inset2,inset2);

        gbcPlayersList.gridx = 1;
        gbcPlayersList.gridy = 1;
        gbcPlayersList.gridwidth = 1;
        gbcPlayersList.gridheight = 1;
        gbcPlayersList.fill = GridBagConstraints.BOTH;
        gbcPlayersList.weightx = 1;
        gbcPlayersList.weighty = 1;
        players.add(playersLead,gbcPlayersList);

        gbcPlayersList.gridx = 2;
        gbcPlayersList.weightx = 5;
        players.add(playersName,gbcPlayersList);

        for(int i = 1; i <= GameConfiguration.MAX_PLAYERS; i++){
            EnigmaPanel joined = new EnigmaPanel();
            joined.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
            if(i > gameConfig.getTotalPlayers())
                joined.getComponentUI().setAllBackgroundImage(single_black, single_black, single_black);
            else
                joined.getComponentUI().setAllBackgroundImage(single_green, single_green, single_green);

            if(i > gameConfig.getMaxGamePlayers())
                joined.getComponentUI().setAllBackgroundImage(null, null, null);

            playersJoin.add(joined);
        }

        for(int i = 0; i < GameConfiguration.MAX_PLAYERS; i++) {

            EnigmaPanel lead = new EnigmaPanel();
            lead.setLayout(new GridBagLayout());
            EnigmaLabel name = new EnigmaLabel();
            if(i < gameConfig.getTotalPlayers()) {
                Player p = gameConfig.getAllPlayers().get(i);
                name.setText(p.getName());

                if(p.equals(UserConfiguration.getInstance().getUser())) {
                    lead.getComponentUI().setAllBackgrounds(blue,blue,blue);
                    name.getComponentUI().setAllBackgrounds(blue,blue,blue);
                }

                if(p.equals(gameConfig.getOwner()))
                    lead.getComponentUI().setAllBackgroundImage(group, group, group);
            }

            if (i < GameConfiguration.MAX_PLAYERS - 1){
                lead.getComponentUI().setAllBorders(grey,grey,grey);
                lead.getComponentUI().setAllShowedBorders(bordersBR,bordersBR,bordersBR);
                lead.getComponentUI().setAllBordersSize(borderSize,borderSize,borderSize);

                name.getComponentUI().setAllBorders(grey,grey,grey);
                name.getComponentUI().setAllShowedBorders(bordersB,bordersB,bordersB);
                name.getComponentUI().setAllBordersSize(borderSize,borderSize,borderSize);
            }else{
                lead.getComponentUI().setAllBorders(grey,grey,grey);
                lead.getComponentUI().setAllShowedBorders(bordersR,bordersR,bordersR);
                lead.getComponentUI().setAllBordersSize(borderSize,borderSize,borderSize);
            }

            playersLead.add(lead);
            playersName.add(name);
        }

        content.add(playersJoin,gbcContent);

        gbcContent.gridy = 2;
        gbcContent.weighty = 2;
        content.add(players,gbcContent);

        gbcContent.gridx = 1;
        gbcContent.gridy = 1;
        gbcContent.insets = new Insets(inset,inset,inset,inset);
        displayContent.add(content,gbcContent);

        return displayContent;
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

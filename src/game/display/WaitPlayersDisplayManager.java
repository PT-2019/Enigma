package game.display;

import editor.entity.Player;
import editor.hud.*;
import editor.hud.ui.EnigmaButtonUI;
import game.GameConfiguration;
import game.UserConfiguration;

import javax.swing.*;
import java.awt.*;

class WaitPlayersDisplayManager implements DisplayManager {

    private final static WaitPlayersDisplayManager instance = new WaitPlayersDisplayManager();
    private EnigmaPanel content;
    private EnigmaPanel rightBar;

    private WaitPlayersDisplayManager(){

        this.initContent();
        this.initRightBar();
        this.refreshAll();
    }

    private void initRightBar(){
        Color darkRed = new Color(150,0,0);
        Color lighterDarkRed = new Color(200,0,0);
        boolean[] borders = new boolean[4];
        borders[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

        EnigmaButtonUI bui = new EnigmaButtonUI();
        bui.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        bui.setAllBorders(null,null,null);
        bui.setAllBackgrounds(darkRed,lighterDarkRed,lighterDarkRed);
        EnigmaButtonUI voidBUI = new EnigmaButtonUI();
        voidBUI.setAllBorders(null,null,null);
        voidBUI.setAllBackgrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);
        voidBUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        voidBUI.setAllForegrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);

        this.rightBar = new EnigmaPanel();
        this.rightBar.setLayout(new GridLayout(2,1));
        this.rightBar.getComponentUI().setAllBorders(Color.RED,Color.RED,Color.RED);
        this.rightBar.getComponentUI().setAllBordersSize(3,3,3);
        this.rightBar.getComponentUI().setAllShowedBorders(borders,borders,borders);

        EnigmaPanel buttonsComponent = new EnigmaPanel();
        EnigmaPanel filtersComponent = new EnigmaPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        int inset = 10;

        buttonsComponent.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset,inset,inset,inset);
        EnigmaButton b = new EnigmaButton("Quitter");
        b.setComponentUI(bui);
        buttonsComponent.add(b,gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0,inset,inset,inset);
        EnigmaButton voidButton = new EnigmaButton("nothing");
        voidButton.setComponentUI(voidBUI);
        buttonsComponent.add(voidButton,gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(inset * 2,inset,inset,inset);
        voidButton = new EnigmaButton("nothing");
        voidButton.setComponentUI(voidBUI);
        buttonsComponent.add(voidButton,gbc);

        this.rightBar.add(buttonsComponent);
        this.rightBar.add(filtersComponent);
    }

    private void initContent(){
        Color grey = new Color(100,100,100);
        Color blue = new Color(50,100,200);
        ImageIcon group = new ImageIcon("assets/icon/player/group.png");
        ImageIcon single_black = new ImageIcon("assets/icon/player/single_black.png");
        ImageIcon single_green = new ImageIcon("assets/icon/player/single_green.png");

        this.content = new EnigmaPanel();
        EnigmaPanel content = new EnigmaPanel();
        EnigmaPanel playersJoin = new EnigmaPanel();
        EnigmaPanel players = new EnigmaPanel();
        EnigmaPanel playersLead = new EnigmaPanel();
        EnigmaPanel playersName = new EnigmaPanel();
        GameConfiguration gameConfig = GameConfiguration.getInstance();

        GridBagConstraints gbcContent = new GridBagConstraints();
        GridBagConstraints gbcPlayersList = new GridBagConstraints();

        content.setLayout(new GridBagLayout());
        this.content.setLayout(new GridBagLayout());
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
        this.content.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
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
        this.content.add(content,gbcContent);
    }

    @Override
    public void refreshContent(){}

    @Override
    public void refreshRightBar(){}

    @Override
    public void refreshAll(){
        this.refreshContent();
        this.refreshRightBar();
    }

    public static WaitPlayersDisplayManager getInstance(){
        return instance;
    }

    @Override
    public EnigmaPanel getContent(){
        return this.content;
    }

    @Override
    public EnigmaPanel getRightBar(){
        return this.rightBar;
    }
}

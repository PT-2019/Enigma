package game.display;

import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.hud.EnigmaTextArea;
import editor.hud.managers.CheckBoxManager;
import editor.hud.ui.EnigmaButtonUI;
import editor.hud.ui.EnigmaUIValues;

import javax.swing.*;
import java.awt.*;

public class RightBarDisplayManager {

    private final static RightBarDisplayManager instance = new RightBarDisplayManager();
    private EnigmaPanel panel;
    private CardLayout layout;

    private RightBarDisplayManager(){
        this.layout = new CardLayout();
        this.panel = new EnigmaPanel();
        this.panel.setLayout(this.layout);

        this.panel.add(this.initSelectGame(),LaunchGameDisplay.SELECT_GAME);
        this.panel.add(this.initJoinGame(),LaunchGameDisplay.JOIN_GAME);
        this.panel.add(this.initWaitPlayers(),LaunchGameDisplay.WAIT_PLAYERS);
        this.panel.add(this.initWaitPlayersLeader(),LaunchGameDisplay.WAIT_PLAYERS_LEADER);
    }

    private EnigmaPanel initSelectGame(){
        Color grey = new Color(100,100,100);
        Color lighterGrey = new Color(150,150,150);
        Color darkRed = new Color(150,0,0);
        Color lighterDarkRed = new Color(200,0,0);
        Color blue = new Color(50,100,200);
        Color lighterBlue = new Color(100,150,250);
        boolean[] borders = new boolean[4];
        borders[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

        EnigmaButtonUI bui = new EnigmaButtonUI();
        bui.setAllBackgrounds(grey,lighterGrey,lighterGrey);
        bui.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        bui.setAllBorders(null,null,null);
        EnigmaButtonUI bui2 = bui.duplicate();
        bui2.setAllBackgrounds(darkRed,lighterDarkRed,lighterDarkRed);

        EnigmaPanel content = new EnigmaPanel();
        content.setLayout(new GridLayout(2,1));
        content.getPanelUI().setAllBorders(Color.RED,Color.RED,Color.RED);
        content.getPanelUI().setAllBordersSize(3,3,3);
        content.getPanelUI().setAllShowedBorders(borders,borders,borders);

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
        EnigmaButton b = new EnigmaButton("Jouer");
        b.setButtonUI(bui);
        buttonsComponent.add(b,gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0,inset,inset,inset);
        b = new EnigmaButton("Supprimer");
        b.setButtonUI(bui2);
        buttonsComponent.add(b,gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(inset * 2,inset,inset,inset);
        b = new EnigmaButton("Nouvelle partie");
        b.setButtonUI(bui);
        buttonsComponent.add(b,gbc);

        content.add(buttonsComponent);

        filtersComponent.setLayout(new GridBagLayout());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset,inset,inset,inset);
        EnigmaTextArea ta = new EnigmaTextArea();
        ta.setPreferredSize(new Dimension());
        ta.getTextAreaUI().setAllBackgrounds(grey,grey,grey);
        EnigmaPanel search = new EnigmaPanel();
        search.setLayout(new GridLayout(2,1));
        EnigmaLabel l = new EnigmaLabel("Rechercher :");
        l.setVerticalAlignment(SwingConstants.BOTTOM);
        ta.setRows(1);
        search.add(l);
        search.add(ta);
        filtersComponent.add(search,gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(inset,inset,inset,inset);
        bui.setAllSelectedBackgrounds(blue,lighterBlue,lighterBlue);
        bui.setAllSelectedBorders(null,null,null);
        bui.setSelectedCursor(new Cursor(Cursor.HAND_CURSOR));
        EnigmaButton filter1 = new EnigmaButton("Solo");
        EnigmaButton filter2 = new EnigmaButton("Multijoueurs");
        filter1.setButtonUI(bui);
        filter2.setButtonUI(bui);
        CheckBoxManager cbm = new CheckBoxManager();
        cbm.add(filter1);
        cbm.add(filter2);
        cbm.setSelected(filter1);
        cbm.setSelected(filter2);
        EnigmaPanel p = new EnigmaPanel();
        p.setLayout(new FlowLayout());
        p.add(filter1);
        p.add(filter2);
        filtersComponent.add(p,gbc);

        content.add(filtersComponent);

        return content;
    }

    public EnigmaPanel initJoinGame(){
        boolean[] borders = new boolean[4];
        borders[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

        EnigmaPanel content = new EnigmaPanel();
        content.getPanelUI().setAllBorders(Color.RED,Color.RED,Color.RED);
        content.getPanelUI().setAllBordersSize(3,3,3);
        content.getPanelUI().setAllShowedBorders(borders,borders,borders);

        return content;
    }

    private EnigmaPanel initWaitPlayers() {
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

        EnigmaPanel content = new EnigmaPanel();
        content.setLayout(new GridLayout(2,1));
        content.getPanelUI().setAllBorders(Color.RED,Color.RED,Color.RED);
        content.getPanelUI().setAllBordersSize(3,3,3);
        content.getPanelUI().setAllShowedBorders(borders,borders,borders);

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
        b.setButtonUI(bui);
        buttonsComponent.add(b,gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0,inset,inset,inset);
        EnigmaButton voidButton = new EnigmaButton("nothing");
        voidButton.setButtonUI(voidBUI);
        buttonsComponent.add(voidButton,gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(inset * 2,inset,inset,inset);
        voidButton = new EnigmaButton("nothing");
        voidButton.setButtonUI(voidBUI);
        buttonsComponent.add(voidButton,gbc);

        content.add(buttonsComponent);
        content.add(filtersComponent);

        return content;
    }

    private EnigmaPanel initWaitPlayersLeader() {
        Color grey = new Color(100,100,100);
        Color lighterGrey = new Color(150,150,150);
        Color darkRed = new Color(150,0,0);
        Color lighterDarkRed = new Color(200,0,0);
        boolean[] borders = new boolean[4];
        borders[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

        EnigmaButtonUI bui = new EnigmaButtonUI();
        bui.setAllBackgrounds(grey,lighterGrey,lighterGrey);
        bui.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        bui.setAllBorders(null,null,null);
        EnigmaButtonUI bui2 = bui.duplicate();
        bui2.setAllBackgrounds(darkRed,lighterDarkRed,lighterDarkRed);
        EnigmaButtonUI voidBUI = new EnigmaButtonUI();
        voidBUI.setAllBorders(null,null,null);
        voidBUI.setAllBackgrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);
        voidBUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        voidBUI.setAllForegrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);

        EnigmaPanel content = new EnigmaPanel();
        content.setLayout(new GridLayout(2,1));
        content.getPanelUI().setAllBorders(Color.RED,Color.RED,Color.RED);
        content.getPanelUI().setAllBordersSize(3,3,3);
        content.getPanelUI().setAllShowedBorders(borders,borders,borders);

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
        EnigmaButton b = new EnigmaButton("Lancer la partie");
        b.setButtonUI(bui);
        buttonsComponent.add(b,gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0,inset,inset,inset);
        b = new EnigmaButton("Annuler la partie");
        b.setButtonUI(bui2);
        buttonsComponent.add(b,gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(inset * 2,inset,inset,inset);
        EnigmaButton voidButton = new EnigmaButton("nothing");
        voidButton.setButtonUI(voidBUI);
        buttonsComponent.add(voidButton,gbc);

        content.add(buttonsComponent);
        content.add(filtersComponent);

        return content;
    }

    public void showDisplay(String displayName){
        this.layout.show(this.panel,displayName);
    }

    public static RightBarDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}

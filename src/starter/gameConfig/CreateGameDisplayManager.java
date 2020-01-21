package starter.gameConfig;

import editor.hud.*;
import editor.hud.ui.EnigmaButtonUI;
import editor.hud.ui.EnigmaLabelUI;
import game.GameConfiguration;
import starter.gameConfig.managers.ChangeConfiguration;
import starter.gameConfig.managers.Redirect;

import javax.swing.*;
import java.awt.*;

class CreateGameDisplayManager implements DisplayManager {

    private final static CreateGameDisplayManager instance = new CreateGameDisplayManager();
    private EnigmaPanel content;
    private EnigmaPanel rightBar;
    private EnigmaPanel gameConfig;

    private CreateGameDisplayManager(){

        this.initContent();
        this.initRightBar();
        this.refreshAll();
    }

    private void initRightBar(){
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
        EnigmaButtonUI bui2 = (EnigmaButtonUI) bui.duplicate();
        bui2.setAllBackgrounds(darkRed,lighterDarkRed,lighterDarkRed);
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
        EnigmaButton b = new EnigmaButton("Créer");
        b.setComponentUI(bui);
        buttonsComponent.add(b,gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0,inset,inset,inset);
        b = new EnigmaButton("Annuler");
        b.setComponentUI(bui2);
        b.addActionListener(new Redirect(LaunchGameDisplay.SELECT_GAME));
        buttonsComponent.add(b,gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(inset * 2,inset,inset,inset);
        EnigmaButton voidButton = new EnigmaButton("nothing");
        voidButton.setComponentUI(voidBUI);
        buttonsComponent.add(voidButton,gbc);

        this.rightBar.add(buttonsComponent);
        this.rightBar.add(filtersComponent);
    }

    private void initContent(){
        int infoCount = 6;
        int inset = 50;

        this.content = new EnigmaPanel();
        this.gameConfig = new EnigmaPanel();
        EnigmaPanel content = new EnigmaPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();

        this.gameConfig.setLayout(new GridLayout(infoCount,1));
        this.content.setLayout(new GridBagLayout());
        content.setLayout(new GridBagLayout());

        this.gameConfig.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        this.content.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx = 1;
        gbc2.weighty = 1;

        EnigmaLabel help = new EnigmaLabel("Cliquez pour modifier quelque chose");
        help.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        help.getComponentUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        content.add(help,gbc2);

        gbc2.gridy = 2;
        gbc2.weighty = 15;
        content.add(this.gameConfig,gbc2);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset,inset,inset,inset);

        this.content.add(content,gbc);
    }

    @Override
    public void refreshContent() {
        this.gameConfig.removeAll();

        Color grey = new Color(100, 100, 100);
        int infoCount = 6;
        int borderSize = 4;
        GameConfiguration gameConfig = GameConfiguration.getInstance();
        boolean[] bordersB = new boolean[4];
        bordersB[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;

        String[] change = new String[infoCount - 1];
        change[0] = ChangeConfiguration.CHANGE_NAME;
        change[1] = ChangeConfiguration.CHANGE_DESCRIPTION;
        change[2] = ChangeConfiguration.CHANGE_MAP;
        change[3] = ChangeConfiguration.CHANGE_DURATION;
        change[4] = ChangeConfiguration.CHANGE_MAX_PLAYERS;

        String type = "multijoueurs";
        if(gameConfig.getMaxGamePlayers() == 1)
            type = "solo";

        String[] infos = new String[infoCount - 1];
        infos[0] = "Partie : " + gameConfig.getName();
        infos[1] = "Description : " + gameConfig.getDescription();
        infos[2] = "Map : " + gameConfig.getMap();
        infos[3] = "Durée : " + gameConfig.getDuration() + " min";
        infos[4] = "Nombre de joueurs : " + gameConfig.getMaxGamePlayers() + " (" + type + ")";
        EnigmaLabelUI lui = new EnigmaLabelUI();
        lui.setAllBackgrounds(Color.WHITE, Color.WHITE, Color.WHITE);
        lui.setAllForegrounds(Color.BLACK, Color.BLACK, Color.BLACK);
        lui.setFont(lui.getFont().deriveFont((float) 18.0));

        for (int i = 0; i < infoCount - 1; i++) {
            EnigmaLabel l = new EnigmaLabel(infos[i]);
            l.setComponentUI(lui);
            l.setHorizontalAlignment(SwingConstants.LEFT);
            l.addMouseListener(new ChangeConfiguration(change[i]));
            this.gameConfig.add(l);

            if (i < infoCount - 2) {
                l.getComponentUI().setAllBorders(grey, grey, grey);
                l.getComponentUI().setAllShowedBorders(bordersB, bordersB, bordersB);
                l.getComponentUI().setAllBordersSize(borderSize, borderSize, borderSize);
            }
        }
    }

    @Override
    public void refreshRightBar(){}

    @Override
    public void refreshAll(){
        this.refreshContent();
        this.refreshRightBar();
        this.content.revalidate();
        this.rightBar.revalidate();
    }

    public static CreateGameDisplayManager getInstance(){
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

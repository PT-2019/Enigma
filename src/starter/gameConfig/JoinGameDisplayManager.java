package starter.gameConfig;

import editor.hud.*;
import starter.gameConfig.managers.Redirect;

import javax.swing.*;
import java.awt.*;

class JoinGameDisplayManager implements DisplayManager {

    private final static JoinGameDisplayManager instance = new JoinGameDisplayManager();
    private EnigmaPanel content;
    private EnigmaPanel rightBar;

    private JoinGameDisplayManager(){

        this.initContent();
        this.initRightBar();
        this.refreshAll();
    }

    private void initRightBar(){
        boolean[] borders = new boolean[4];
        borders[EnigmaUIValues.LEFT_BORDER] = EnigmaUIValues.SHOWED_BORDER;

        this.rightBar = new EnigmaPanel();
        this.rightBar.getComponentUI().setAllBorders(Color.RED,Color.RED,Color.RED);
        this.rightBar.getComponentUI().setAllBordersSize(3,3,3);
        this.rightBar.getComponentUI().setAllShowedBorders(borders,borders,borders);
    }

    private void initContent(){
        Color grey = new Color(100,100,100);
        Color lighterGrey = new Color(150,150,150);

        this.content = new EnigmaPanel();
        EnigmaPanel navBar = new EnigmaPanel();
        EnigmaPanel content = new EnigmaPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        this.content.setLayout(new GridBagLayout());
        int inset = 100;

        navBar.setLayout(new GridLayout(1,2));
        navBar.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        EnigmaButton launchGame = new EnigmaButton("Lancer une partie");
        launchGame.getComponentUI().setAllBackgrounds(Color.DARK_GRAY,Color.DARK_GRAY,Color.DARK_GRAY);
        launchGame.getComponentUI().setAllBorders(null,null,null);
        launchGame.getComponentUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        launchGame.addActionListener(new Redirect(LaunchGameDisplay.SELECT_GAME));
        navBar.add(launchGame);
        EnigmaButton joinGame = new EnigmaButton("Rejoindre une partie");
        joinGame.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
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
        this.content.add(navBar,gbc);

        content.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        content.setLayout(new GridBagLayout());
        EnigmaPanel joinComponent = new EnigmaPanel();
        joinComponent.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinComponent.setLayout(new GridLayout(4,1));
        EnigmaPanel joinPanel = new EnigmaPanel();
        joinComponent.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinPanel.setLayout(new BorderLayout());
        EnigmaLabel label = new EnigmaLabel("Entrez l'adresse IP :");
        EnigmaTextArea input = new EnigmaTextArea();

        label.setVerticalAlignment(SwingConstants.BOTTOM);
        label.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        label.getComponentUI().setAllForegrounds(Color.BLACK,Color.BLACK,Color.BLACK);
        input.setRows(1);
        input.getComponentUI().setAllBackgrounds(grey,grey,grey);

        joinPanel.add(label,BorderLayout.CENTER);
        joinPanel.add(input,BorderLayout.SOUTH);

        EnigmaPanel voidPanel = new EnigmaPanel();
        voidPanel.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinComponent.add(voidPanel);

        joinComponent.add(joinPanel);

        EnigmaPanel confirmComponent = new EnigmaPanel();
        confirmComponent.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        confirmComponent.setLayout(new FlowLayout());
        EnigmaButton confirm = new EnigmaButton("Rejoindre");
        confirm.getComponentUI().setAllBackgrounds(grey,lighterGrey,lighterGrey);
        confirm.getComponentUI().setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        confirm.getComponentUI().setAllBorders(null,null,null);
        confirmComponent.add(confirm);
        joinComponent.add(confirmComponent);

        voidPanel = new EnigmaPanel();
        voidPanel.getComponentUI().setAllBackgrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        joinComponent.add(voidPanel);

        gbc.insets = new Insets(inset,inset,inset,inset);
        content.add(joinComponent,gbc);

        gbc.gridy = 2;
        gbc.weighty = 15;
        gbc.insets = new Insets(0,0,0,0);
        this.content.add(content,gbc);
    }

    @Override
    public void refreshContent(){}

    @Override
    public void refreshRightBar(){}

    @Override
    public void refreshAll(){
        this.refreshContent();
        this.refreshRightBar();
        this.content.revalidate();
        this.rightBar.revalidate();
    }

    public static JoinGameDisplayManager getInstance(){
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

package game.hmi.content;

import api.ui.manager.RadioButtonManager;
import api.utils.Utility;
import common.data.GameData;
import common.hud.*;
import common.hud.ui.EnigmaButtonUI;
import common.utils.Logger;
import data.config.EnigmaUIValues;
import data.config.UserConfiguration;
import game.EnigmaGameLauncher;
import game.hmi.Content;
import game.hmi.listener.action.MapSelectionListener;
import game.hmi.listener.action.MultiOrSoloListener;

import javax.swing.*;
import java.awt.*;

public class Create extends Content {
    private final static Create instance = new Create();
    private EnigmaTextField name;
    private EnigmaTextArea description;
    private RadioButtonManager multi;
    private RadioButtonManager players;
    private EnigmaTextField duration;
    private EnigmaLabel map;
    private EnigmaPanel playersComponent;
    private EnigmaButtonUI bui;
    private EnigmaButton p1;
    private EnigmaButton p2;
    private EnigmaButton p3;
    private EnigmaButton p4;
    private EnigmaButton yes;
    private EnigmaButton no;

    /**
     * Etats
     */
    public final static int SOLO_STATE = 0;
    public final static int MULTI_STATE = 1;

    /**
     * Textes
     */
    private final static String NAME = "Nom";
    private final static String MAP = "Map";
    private final static String DESCRIPTION = "Description";
    private final static String NB_PLAYERS = "Nombre de joueurs";
    private final static String DURATION = "Durée";
    private final static String MULTI_PLAYERS = "Multijoueurs";
    private final static String YES = "Oui";
    private final static String NO = "Non";
    private final static String NO_NAME = "La partie n'a pas de nom";
    private final static String NO_DURATION = "La partie n'a pas de durée";
    private final static String NO_MAP = "Aucune map séléctionnée";
    private final static String WRONG_DURATION = "La durée n'est pas valide";
    private int state;

    private Create() {
        super(new EnigmaPanel());
        this.content.setLayout(new GridLayout(6,2));
        Color red = new Color(200, 0, 0);
        Color blue = new Color(0, 136, 193);

        this.name = new EnigmaTextField();
        this.description = new EnigmaTextArea();
        this.multi = new RadioButtonManager();
        this.players = new RadioButtonManager();
        this.duration = new EnigmaTextField();
        this.map = new EnigmaLabel();
        this.playersComponent = new EnigmaPanel();
        this.p1 = new EnigmaButton("1");
        this.p2 = new EnigmaButton("2");
        this.p3 = new EnigmaButton("3");
        this.p4 = new EnigmaButton("4");
        this.yes = new EnigmaButton(YES);
        this.no = new EnigmaButton(NO);

        this.bui = new EnigmaButtonUI();
        this.bui.setAllBackgrounds(Color.GRAY,blue,blue);
        this.bui.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN);
        this.bui.setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        this.bui.setAllSelectedBackgrounds(red,red,red);
        this.bui.setAllSelectedShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN,EnigmaUIValues.ALL_BORDER_HIDDEN);
        this.bui.setSelectedCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.bui.setAllSelectedForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);

        this.name.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.description.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.map.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.duration.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.initContent();
        this.refresh(SOLO_STATE);
    }

    @Override
    public void initContent() {
        int borderSize = 6;
        EnigmaLabel name = new EnigmaLabel(NAME + ":");
        EnigmaLabel description = new EnigmaLabel(DESCRIPTION + ":");
        EnigmaLabel multi = new EnigmaLabel(MULTI_PLAYERS + ":");
        EnigmaLabel players = new EnigmaLabel(NB_PLAYERS + ":");
        EnigmaLabel duration = new EnigmaLabel(DURATION + "(min) :");
        EnigmaLabel map = new EnigmaLabel(MAP + ":");
        EnigmaPanel multiComponent = new EnigmaPanel();
        EnigmaPanel nameComponent = new EnigmaPanel();
        EnigmaPanel descriptionComponent = new EnigmaPanel();
        EnigmaPanel durationComponent = new EnigmaPanel();

        MultiOrSoloListener mosl = new MultiOrSoloListener();
        this.yes.addActionListener(mosl);
        this.no.addActionListener(mosl);
        this.map.addMouseListener(new MapSelectionListener(this.map));

        nameComponent.setLayout(new BorderLayout());
        descriptionComponent.setLayout(new BorderLayout());
        durationComponent.setLayout(new BorderLayout());

        this.yes.setComponentUI(this.bui);
        this.no.setComponentUI(this.bui);
        this.p1.setComponentUI(this.bui);
        this.p2.setComponentUI(this.bui);
        this.p3.setComponentUI(this.bui);
        this.p4.setComponentUI(this.bui);

        name.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        description.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        multi.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        players.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        duration.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        map.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        this.name.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        this.description.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        this.map.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        multiComponent.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        this.playersComponent.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);
        this.duration.getComponentUI().setAllBackgrounds(Color.GRAY,Color.GRAY,Color.GRAY);

        name.setBorder(BorderFactory.createMatteBorder(borderSize,borderSize,borderSize / 2,borderSize / 2,Color.DARK_GRAY));
        description.setBorder(BorderFactory.createMatteBorder(borderSize / 2,borderSize,borderSize / 2,borderSize / 2,Color.DARK_GRAY));
        map.setBorder(BorderFactory.createMatteBorder(borderSize / 2,borderSize,borderSize / 2,borderSize / 2,Color.DARK_GRAY));
        multi.setBorder(BorderFactory.createMatteBorder(borderSize / 2,borderSize,borderSize / 2,borderSize / 2,Color.DARK_GRAY));
        players.setBorder(BorderFactory.createMatteBorder(borderSize / 2,borderSize,borderSize / 2,borderSize / 2,Color.DARK_GRAY));
        duration.setBorder(BorderFactory.createMatteBorder(borderSize / 2,borderSize,borderSize,borderSize / 2,Color.DARK_GRAY));
        nameComponent.setBorder(BorderFactory.createMatteBorder(borderSize ,borderSize / 2,borderSize / 2,borderSize,Color.DARK_GRAY));
        descriptionComponent.setBorder(BorderFactory.createMatteBorder(borderSize / 2 ,borderSize / 2,borderSize / 2,borderSize,Color.DARK_GRAY));
        this.map.setBorder(BorderFactory.createMatteBorder(borderSize / 2 ,borderSize / 2,borderSize / 2,borderSize,Color.DARK_GRAY));
        multiComponent.setBorder(BorderFactory.createMatteBorder(borderSize / 2 ,borderSize / 2,borderSize / 2,borderSize,Color.DARK_GRAY));
        this.playersComponent.setBorder(BorderFactory.createMatteBorder(borderSize / 2 ,borderSize / 2,borderSize / 2,borderSize,Color.DARK_GRAY));
        durationComponent.setBorder(BorderFactory.createMatteBorder(borderSize / 2 ,borderSize / 2,borderSize,borderSize,Color.DARK_GRAY));

        this.p1.setBorderPainted(true);
        this.p1.setBorder(BorderFactory.createMatteBorder(0 ,0,0,0,Color.DARK_GRAY));
        this.p3.setBorderPainted(true);
        this.p3.setBorder(BorderFactory.createMatteBorder(0 ,borderSize,0,borderSize,Color.DARK_GRAY));
        this.no.setBorderPainted(true);
        this.no.setBorder(BorderFactory.createMatteBorder(0 ,0,0, borderSize,Color.DARK_GRAY));

        multiComponent.setLayout(new GridLayout(1,2));

        multiComponent.add(this.no);
        multiComponent.add(this.yes);

        this.multi.add(this.yes);
        this.multi.add(this.no);
        this.multi.setSelected(this.no);
        this.players.add(this.p1);
        this.players.add(this.p2);
        this.players.add(this.p3);
        this.players.add(this.p4);
        this.players.setSelected(this.p1);

        nameComponent.add(this.name,BorderLayout.CENTER);
        descriptionComponent.add(this.description,BorderLayout.CENTER);
        durationComponent.add(this.duration,BorderLayout.CENTER);

        this.content.add(name);
        this.content.add(nameComponent);
        this.content.add(description);
        this.content.add(descriptionComponent);
        this.content.add(map);
        this.content.add(this.map);
        this.content.add(multi);
        this.content.add(multiComponent);
        this.content.add(players);
        this.content.add(this.playersComponent);
        this.content.add(duration);
        this.content.add(durationComponent);
    }

    @Override
    public void refresh(int state) {
        this.playersComponent.removeAll();

        switch (state){
            default:
                state = SOLO_STATE;
            case SOLO_STATE:
                this.playersComponent.setLayout(new GridLayout(1,1));
                this.playersComponent.add(this.p1);
                this.players.setSelected(this.p1);
                this.multi.setSelected(this.no);
                break;
            case MULTI_STATE:
                this.playersComponent.setLayout(new GridLayout(1,4));
                this.players.setSelected(this.p2);
                this.multi.setSelected(this.yes);
                this.playersComponent.add(this.p2);
                this.playersComponent.add(this.p3);
                this.playersComponent.add(this.p4);
                break;
        }

        this.state = state;
        this.content.revalidate();
        this.playersComponent.repaint();
    }

    public int getState() {
        return this.state;
    }

    public boolean verify(){

        if(this.name.getText().equals("")){
            EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),NO_NAME);
            return false;
        }

        if(this.map.getText().equals("")){
            EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),NO_MAP);
            return false;
        }

        if(this.duration.getText().equals("")){
            EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),NO_DURATION);
            return false;
        }else{
            try{
                int i = Integer.parseInt(this.duration.getText());
            }catch (NumberFormatException e){
                EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),WRONG_DURATION);
                return false;
            }
        }

        return true;
    }

    public GameData getData(){
        return new GameData(Utility.normalize(this.name.getText()),
                UserConfiguration.getInstance().getData().getName(),
                this.map.getText(),
                this.description.getText(),
                Integer.parseInt(this.duration.getText()),
                Integer.parseInt(this.players.getSelectedButton().getText())
                );
    }

    public static Create getInstance(){
        return instance;
    }
}

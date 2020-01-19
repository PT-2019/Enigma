package game.display;

import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.hud.EnigmaTextArea;
import editor.hud.managers.RadioButtonManager;
import editor.hud.ui.EnigmaLabelUI;
import editor.hud.ui.EnigmaPanelUI;
import editor.hud.ui.EnigmaUIValues;
import game.GameConfiguration;

import java.awt.*;

class CreateGameDisplayManager {

    private final static CreateGameDisplayManager instance = new CreateGameDisplayManager();
    private EnigmaButton[] numberPlayerOptions;
    private EnigmaButton[] multiOptions;
    private EnigmaPanel panel;
    private EnigmaTextArea nameAnswer;
    private EnigmaTextArea durationAnswer;
    private EnigmaLabel mapAnswer;
    private RadioButtonManager numberPlayerRBM;
    private RadioButtonManager multiRBM;

    private CreateGameDisplayManager(){
        this.panel = new EnigmaPanel();
        this.panel.setLayout(new GridLayout(6,1));
        GridLayout optionLayout = new GridLayout(1,2);

        boolean[] optionBorders = new boolean[4];
        optionBorders[EnigmaUIValues.BOTTOM_BORDER] = EnigmaUIValues.SHOWED_BORDER;
        EnigmaPanelUI optionUI = new EnigmaPanelUI();
        optionUI.setAllBorders(Color.WHITE,Color.WHITE,Color.WHITE);
        optionUI.setAllShowedBorders(optionBorders,optionBorders,optionBorders);

        boolean[] titleBorders = new boolean[4];
        titleBorders[EnigmaUIValues.RIGHT_BORDER] = EnigmaUIValues.SHOWED_BORDER;
        EnigmaLabelUI optionTitleUI = new EnigmaLabelUI();
        optionTitleUI.setAllBorders(Color.WHITE,Color.WHITE,Color.WHITE);
        optionTitleUI.setAllShowedBorders(titleBorders,titleBorders,titleBorders);

        EnigmaPanel gameName = new EnigmaPanel();
        gameName.setPanelUI(optionUI);
        gameName.setLayout(optionLayout);
        EnigmaLabel nameTitle = new EnigmaLabel("Nom de la partie (max 50 charactères) :");
        nameTitle.setLabelUI(optionTitleUI);
        gameName.add(nameTitle);
        this.nameAnswer = new EnigmaTextArea();
        gameName.add(this.nameAnswer.setScrollBar());
        this.panel.add(gameName);

        EnigmaPanel multi = new EnigmaPanel();
        multi.setPanelUI(optionUI);
        multi.setLayout(optionLayout);
        EnigmaLabel multiTitle = new EnigmaLabel("Multijoueurs :");
        multiTitle.setLabelUI(optionTitleUI);
        multi.add(multiTitle);
        EnigmaPanel multiAnswer = new EnigmaPanel();
        multiAnswer.setLayout(new FlowLayout());
        this.multiRBM = new RadioButtonManager();
        this.multiOptions = new EnigmaButton[2];
        this.multiOptions[0] = new EnigmaButton("Oui");
        this.multiOptions[1] = new EnigmaButton("Non");
        for(EnigmaButton b: this.multiOptions){
            this.multiRBM.add(b);
            multiAnswer.add(b);
        }
        multi.add(multiAnswer);
        this.panel.add(multi);

        EnigmaPanel nbPlayers = new EnigmaPanel();
        nbPlayers.setPanelUI(optionUI);
        nbPlayers.setLayout(optionLayout);
        EnigmaLabel nbTitle = new EnigmaLabel("Nombre de joueurs :");
        nbTitle.setLabelUI(optionTitleUI);
        nbPlayers.add(nbTitle);
        EnigmaPanel nbAnswer = new EnigmaPanel();
        nbAnswer.setLayout(new FlowLayout());
        this.numberPlayerOptions = new EnigmaButton[3];
        this.numberPlayerRBM = new RadioButtonManager();
        for(int i = 0; i < this.numberPlayerOptions.length; i++){
            this.numberPlayerOptions[i] = new EnigmaButton(Integer.toString(i + 2));
            this.numberPlayerRBM.add(this.numberPlayerOptions[i]);
            nbAnswer.add(this.numberPlayerOptions[i]);
        }
        nbPlayers.add(nbAnswer);
        this.panel.add(nbPlayers);

        EnigmaPanel duration = new EnigmaPanel();
        duration.setPanelUI(optionUI);
        duration.setLayout(optionLayout);
        EnigmaLabel durationTitle = new EnigmaLabel("Durée de la partie (en minutes) :");
        durationTitle.setLabelUI(optionTitleUI);
        duration.add(durationTitle);
        this.durationAnswer = new EnigmaTextArea();
        duration.add(this.durationAnswer.setScrollBar());
        this.panel.add(duration);

        EnigmaPanel map = new EnigmaPanel();
        map.setPanelUI(optionUI);
        map.setLayout(optionLayout);
        EnigmaLabel mapTitle = new EnigmaLabel("Choisir une map :");
        mapTitle.setLabelUI(optionTitleUI);
        map.add(mapTitle);
        this.mapAnswer = new EnigmaLabel();
        map.add(this.mapAnswer);
        this.panel.add(map);

        EnigmaButton play = new EnigmaButton("Commencer");
        play.getButtonUI().setAllBackgrounds(new Color(0,150,0),new Color(0,150,0),new Color(0,100,0));
        play.getButtonUI().setAllBorders(null,null,null);
        play.getButtonUI().setAllForegrounds(Color.WHITE,Color.WHITE,Color.WHITE);
        this.panel.add(play);

        this.refresh();
    }

    public void refresh(){
        GameConfiguration gameConfig = GameConfiguration.getInstance();
        this.nameAnswer.setText(gameConfig.getGameName());
        this.mapAnswer.setText(gameConfig.getMap());
        this.durationAnswer.setText(Double.toString(gameConfig.getDuration()).replace(".",":"));
        if(gameConfig.getMaxGamePlayers() > 1) this.numberPlayerRBM.setSelected(this.numberPlayerOptions[gameConfig.getMaxGamePlayers() - 2]);
        if(gameConfig.isMultiPlayer()) this.multiRBM.setSelected(this.multiOptions[0]);
        else {
            this.multiRBM.setSelected(this.multiOptions[1]);
        }
    }

    public static CreateGameDisplayManager getInstance(){
        return instance;
    }

    public EnigmaPanel getPanel(){
        return this.panel;
    }
}

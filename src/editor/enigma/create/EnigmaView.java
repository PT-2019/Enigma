package editor.enigma.create;

import com.badlogic.gdx.Gdx;
import editor.enigma.Enigma;
import editor.view.cases.CasePopUp;
import editor.view.cases.listeners.EntityChoseListener;
import game.entity.map.MapTestScreenCell;

import javax.swing.*;
import java.awt.*;

/**
 * Element principal dans la création de l'enigme, contient toute les informations nécessaire
 * @see CasePopUp
 */
public class EnigmaView extends JDialog {
    /**
     * Enigme que l'utilisateur va créer
     */
    private Enigma enigma;

    private CardLayout layout;

    /**
     * Composant principal de la fenêtre
     */
    private JPanel panel;

    private CasePopUp popUp;

    private MapTestScreenCell cell;

    public EnigmaView(CasePopUp popUp, MapTestScreenCell cell, EntityChoseListener observer){
        super((JFrame)popUp.getComponent().getRootPane().getParent(),"Créer une enigme",false);
        this.setSize(300,700);
        this.setLocation(0,0);
        this.setLocation(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.layout = new CardLayout();
        panel = new JPanel();
        panel.setLayout(this.layout);
        this.add(panel);
        this.popUp = popUp;

        ConditionPanel condition = new ConditionPanel(this);
        observer.addObserveur(condition);
        OperationPanel operation = new OperationPanel(this);
        observer.addObserveur(operation);
        CluePanel clue = new CluePanel(this);
        panel.add(new EnigmaMenu(this),"menu");
        panel.add(clue,"clue");
        panel.add(condition,"condition");
        panel.add(operation,"operation");
        enigma = new Enigma();
        this.cell = cell;
    }

    public CardLayout getCardLayout(){
        return this.layout;
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public Enigma getEnigma() {
        return enigma;
    }

    public CasePopUp getPopUp() {
        return popUp;
    }

    public MapTestScreenCell getCell() {
        return cell;
    }
}

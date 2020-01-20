package editor.utils.save.view.cases.enigma;

import com.badlogic.gdx.Gdx;
import editor.enigma.Enigma;
import editor.utils.save.view.cases.CasePopUp;
import game.entity.MapLibgdxCell;

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

    private MapLibgdxCell cell;

    public EnigmaView(CasePopUp popUp,MapLibgdxCell cell){
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
        panel.add(new EnigmaMenu(this),"menu");
        panel.add(new CluePanel(this),"clue");
        panel.add(new ConditionPanel(this),"condition");
        panel.add(new OperationPanel(this),"operation");
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

    public MapLibgdxCell getCell() {
        return cell;
    }
}

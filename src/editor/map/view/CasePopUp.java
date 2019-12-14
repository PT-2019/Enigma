package editor.map.view;

import com.badlogic.gdx.Gdx;
import editor.entity.interfaces.Entity;
import editor.map.Case;

import javax.swing.*;
import java.awt.*;

public class CasePopUp extends JDialog {

    private Case cell;

    private Entity currentEntity;

    public CasePopUp(JComponent component){
        super((JFrame)component.getRootPane().getParent(),"Case Information",true);
        this.setSize(300,300);
        this.setLocation(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        this.setResizable(false);

        this.setLayout(new GridLayout(3,2));

        this.add(new JButton("Suivant"));
        this.add(new JLabel("Actuel"));
        this.add(new JButton("Précédent"));
        this.add(new JLabel("Gérer les énigmes"));
        this.add(new JButton("Supprimer"));
    }

    public void display(){

        this.setVisible(true);
    }

    public void setCell(Case cell) {
        this.cell = cell;
    }
}

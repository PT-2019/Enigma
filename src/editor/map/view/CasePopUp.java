package editor.map.view;

import com.badlogic.gdx.Gdx;
import editor.entity.interfaces.Entity;
import editor.map.Case;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CasePopUp extends JDialog implements WindowListener {

    private Case cell;

    private Entity currentEntity;

    JLabel label =new JLabel("Actuel");

    JButton b1 = new JButton("Suivant");

    JButton b2 = new JButton("Précédent");

    JLabel l = new JLabel("Gérer les énigmes");

    JButton b3 = new JButton("Supprimer");

    public CasePopUp(JComponent component){
        super((JFrame)component.getRootPane().getParent(),"Case Information",true);
        this.setSize(300,300);
        this.setLocation(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);

        this.addWindowListener(this);
    }

    public void display(){
        this.setLayout(new GridLayout(2,3));
        this.add(b1);
        this.add(label);
        this.add(b2);
        this.add(l);
        this.add(b3);

        this.setVisible(true);
    }



    public void setCell(Case cell) {
        this.cell = cell;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        this.remove(label);
        this.remove(b3);
        this.remove(b2);
        this.remove(l);
        this.remove(b1);
        this.setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}

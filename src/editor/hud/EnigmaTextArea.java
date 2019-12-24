package editor.hud;

import editor.hud.managers.EnigmaTextAreaManager;
import editor.hud.ui.EnigmaTextAreaUI;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: comment EnigmaTextArea and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
public class EnigmaTextArea extends JTextArea {

    private EnigmaTextAreaUI ui;

    public EnigmaTextArea(){
        super();
        EnigmaTextAreaManager manager = new EnigmaTextAreaManager(this);
        this.addMouseListener(manager);
        this.addFocusListener(manager);
        this.setOpaque(true);
        this.setTextAreaUI(new EnigmaTextAreaUI());
        this.setLineWrap(true);
    }

    public JScrollPane setScrollBar(){
        JScrollPane scroll = new JScrollPane(this);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        return scroll;
    }

    public void setTextAreaUI(EnigmaTextAreaUI ui){
        this.ui = ui.duplicate();
        this.setCursor(this.ui.getCursor());
        this.setFont(this.ui.getFont());
        super.setUI(this.ui);
    }

    public EnigmaTextAreaUI getTextAreaUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        this.ui.paintTextArea(g,this);
    }
}

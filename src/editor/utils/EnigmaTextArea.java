package editor.utils;

import editor.utils.managers.EnigmaTextAreaManager;
import editor.utils.ui.EnigmaTextAreaUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;

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

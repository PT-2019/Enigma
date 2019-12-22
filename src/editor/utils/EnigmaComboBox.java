package editor.utils;

import editor.utils.managers.ComboBoxItemManager;
import editor.utils.managers.EnigmaComboBoxManager;
import editor.utils.ui.EnigmaComboBoxUI;

import javax.swing.*;
import java.awt.*;

public class EnigmaComboBox extends JPanel {

    private EnigmaPopupMenu popup;
    private EnigmaLabel label;
    private EnigmaButton button;
    private EnigmaMenuItem selected;
    private EnigmaComboBoxUI ui;

    public EnigmaComboBox(){
        super();

        this.popup = new EnigmaPopupMenu();
        this.label = new EnigmaLabel();
        this.button = new EnigmaButton("v");
        this.selected = null;

        this.addMouseListener(new EnigmaComboBoxManager(this));
        this.setOpaque(true);
        this.setComboBoxUI(new EnigmaComboBoxUI());

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 10;
        gbc.weighty = 1;
        this.add(this.label,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(this.button,gbc);

        EnigmaComboBoxManager manager = new EnigmaComboBoxManager(this);
        this.addMouseListener(manager);
        this.button.addMouseListener(manager);
        this.label.addMouseListener(manager);
    }

    public EnigmaComboBox(EnigmaPopupMenu popup){
        super();

        this.setPopup(popup);
        this.label = new EnigmaLabel();
        this.button = new EnigmaButton("v");
        this.selected = null;

        this.addMouseListener(new EnigmaComboBoxManager(this));
        this.setOpaque(true);
        this.setComboBoxUI(new EnigmaComboBoxUI());

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 10;
        gbc.weighty = 1;
        this.add(this.label,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(this.button,gbc);

        EnigmaComboBoxManager manager = new EnigmaComboBoxManager(this);
        this.addMouseListener(manager);
        this.button.addMouseListener(manager);
        this.label.addMouseListener(manager);

        this.setFirstElement();
    }

    public void setFirstElement(){
        if(this.popup.getComponentCount() > 0){
            EnigmaMenuItem item = (EnigmaMenuItem) this.popup.getComponent(0);
            this.label.setText(item.getText());
            this.selected = item;
        }
    }

    public void setElementAtIndex(int index){
        if(this.popup.getComponentCount() >= index && index >= 0){
            EnigmaMenuItem item = (EnigmaMenuItem) this.popup.getComponent(index);
            this.label.setText(item.getText());
            this.selected = item;
        }
    }

    public EnigmaPopupMenu getPopup(){
        return this.popup;
    }

    public void setPopup(EnigmaPopupMenu popup){
        for(MenuElement element: this.popup.getSubElements()){
            if(!(element instanceof EnigmaMenuItem))
                throw new IllegalArgumentException("Les éléments du EnigmaPopupMenu doivent impérativement être des EnigmaMenuItems");
            this.setListenerToItem((EnigmaMenuItem) element);
        }
        this.popup = popup;
        this.setFirstElement();
    }

    public EnigmaLabel getLabel(){
        return this.label;
    }

    public void setLabel(EnigmaLabel label){
        this.label = label;
    }

    public EnigmaButton getButton(){
        return this.button;
    }

    public void setButton(EnigmaButton button){
        this.button = button;
    }

    public void addItem(EnigmaMenuItem item){
        this.setListenerToItem(item);
        this.popup.add(item);
        if(this.popup.getComponentCount() == 1) this.setFirstElement();
    }

    private void setListenerToItem(EnigmaMenuItem item){
        item.addActionListener(new ComboBoxItemManager(this));
    }

    public void setSelected(EnigmaMenuItem item){
        this.setElementAtIndex(this.popup.getComponentIndex(item));
    }

    public EnigmaMenuItem getSelected(){
        return this.selected;
    }

    public void setComboBoxUI(EnigmaComboBoxUI ui){
        this.ui = ui.duplicate();
        this.button.setButtonUI(this.ui.getButtonUI());
        this.label.setLabelUI(this.ui.getLabelUI());
        this.label.setFont(this.ui.getFont());
        this.popup.setPopupMenuUI(this.ui.getPopupUI());
        this.setCursor(this.ui.getCursor());
    }

    public EnigmaComboBoxUI getComboBoxUI(){
        return this.ui;
    }

    @Override
    public void paintComponent(Graphics g){
        this.ui.paint(g,this);
    }
}

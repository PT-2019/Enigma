package editor.utils.managers;

import editor.utils.EnigmaTextArea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnigmaTextAreaManager implements MouseListener {

    private EnigmaTextArea textArea;

    public EnigmaTextAreaManager(EnigmaTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){}

    @Override
    public void mousePressed(MouseEvent mouseEvent){
        if(this.textArea.getTextAreaUI() != null){
            this.textArea.getTextAreaUI().setIsPressed(true);
            this.textArea.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent){
        if(this.textArea.getTextAreaUI() != null){
            this.textArea.getTextAreaUI().setIsPressed(false);
            this.textArea.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent){
        if(this.textArea.getTextAreaUI() != null){
            this.textArea.getTextAreaUI().setIsHovered(true);
            this.textArea.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent){
        if(this.textArea.getTextAreaUI() != null){
            this.textArea.getTextAreaUI().setIsHovered(false);
            this.textArea.repaint();
        }
    }
}

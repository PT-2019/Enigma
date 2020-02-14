package editor.menus.enimas.create.listeners;

import api.utils.Utility;
import common.hud.EnigmaLabel;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaTextArea;
import common.hud.EnigmaWindow;
import common.utils.Question;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import editor.menus.enimas.create.ChoicePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controlleur qui va afficher le formulaire pour créer la question et réponse
 */
public class AnswerListener implements MouseListener {

    private static final String TITLE = NeedToBeTranslated.ADD_QUESTION;
    private static final String QUESTION = NeedToBeTranslated.INPUT_QUESTION;
    private static final String ANSWER = NeedToBeTranslated.INPUT_ANSWER;

    /**
     * Contenu du panel
     */
    private final Object[] content;

    /**
     * Champs de saisie de la réponse
     */
    private final EnigmaTextArea questionF, answerF;

    /**
     * Fenêtre de l'éditeur
     */
    private final EnigmaWindow window;

    public AnswerListener(){
        this.questionF = new EnigmaTextArea();
        this.answerF = new EnigmaTextArea();

        EnigmaLabel question = new EnigmaLabel(QUESTION);
        EnigmaLabel answer = new EnigmaLabel(ANSWER);

        this.content = new Object[]{
                question, this.questionF,
                answer, this.answerF,
        };

        this.window = (EnigmaWindow) EditorLauncher.getInstance().getWindow();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       /* String answer;
        Object tmp;
        answer = EnigmaOptionPane.showInputDialog(new EnigmaWindow(), ConditionPanel.ANSWER_CHOICE);

        if (!answer.equals(CANCEL) && !answer.isEmpty() && !answer.isBlank()){
            tmp = e.getSource();
            if (tmp instanceof JLabel){
                ChoicePanel panel = (ChoicePanel) ((JLabel) tmp).getParent();
                panel.getParent().update(new Question("",answer));
            }
        }*/

        String[] choices = {EnigmaOptionPane.CONFIRM};
        int choice = EnigmaOptionPane.showOptionDialog(this.window, this.content, TITLE, choices);

        if(choice == 0){//indice de confirmer
            String question = this.questionF.getText();
            String answer = this.answerF.getText();

            if(Utility.isStringValid(question) && Utility.isStringValid(answer)){
                Object tmp = e.getSource();
                if (tmp instanceof JLabel){
                    ChoicePanel panel = (ChoicePanel) ((JLabel) tmp).getParent();
                    panel.getParent().update(new Question(question, answer));
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel){
            JLabel label = (JLabel) e.getSource();
            label.setForeground(new Color(203, 64, 249 ));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel){
            JLabel label = (JLabel) e.getSource();
            label.setForeground(Color.BLACK);
        }
    }
}

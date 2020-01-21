package starter.gameConfig.managers;

import editor.hud.EnigmaOptionPane;
import editor.hud.EnigmaWindow;
import game.GameConfiguration;
import starter.EnigmaGameLauncher;
import starter.gameConfig.LaunchGameDisplay;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

public class ChangeConfiguration implements MouseListener {
    public final static String CHANGE_NAME = "name";
    public final static String CHANGE_DESCRIPTION = "description";
    public final static String CHANGE_DURATION = "duration";
    public final static String CHANGE_MAP = "map";
    public final static String CHANGE_MAX_PLAYERS = "max players";

    private String change;

    public ChangeConfiguration(String change){
        this.change = change;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        String value;
        int val;
        switch(this.change){
            case CHANGE_NAME:
                value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(),"Entrez le nom de la partie :");
                if(value.length() > 0 && value.length() <= 50)
                    GameConfiguration.getInstance().setName(value);
                else
                    EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),"Le nom de la partie doit faire maximum 50 caractères et ne doit pas être vide");
                break;
            case CHANGE_DESCRIPTION:
                value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(),"Entrez la description de la partie :");
                if(value.length() > 0 && value.length() <= 50)
                    GameConfiguration.getInstance().setDescription(value);
                else
                    EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),"La description de la partie doit faire maximum 50 caractères et ne doit pas être vide");
                break;
            case CHANGE_MAP:
                value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(),"Selectionnez la map :");
                GameConfiguration.getInstance().setMap(value);
                break;
            case CHANGE_DURATION:
                value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(),"Entrez la durée de la partie (en minutes) :");
                val = -1;
                try{
                    val = Integer.parseInt(value);
                }catch(NumberFormatException e){}

                if(val > 0)
                    GameConfiguration.getInstance().setDuration(val);
                else
                    EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),"La durée doit être un entier supérieur à 0");
                break;
            case CHANGE_MAX_PLAYERS:
                value = EnigmaOptionPane.showInputDialog(EnigmaGameLauncher.getInstance().getWindow(),"Entrez le nombre de joueurs (entre 1 et 4) :");
                val = -1;
                try{
                    val = Integer.parseInt(value);
                }catch(NumberFormatException e){}

                if(val > 0 && val <= 4)
                    GameConfiguration.getInstance().setMaxGamePlayers(val);
                else
                    EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(),"Le nombre de joueurs doit être compris entre 1 (solo) et 4 (multijoueurs)");
                break;
        }

        LaunchGameDisplay.getInstance().refreshCurrentDisplay();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}

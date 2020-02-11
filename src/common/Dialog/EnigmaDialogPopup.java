package common.Dialog;

import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import common.entities.types.Content;

public class EnigmaDialogPopup extends Window {

    /**
     * dialogue que l'on va afficher
     */
    private Dialog dialog;

    private Table container;

    private Label text;

    public EnigmaDialogPopup() {
        super("", LibgdxUtility.loadSkin("assets/files/atlas/dialog.json","assets/files/atlas/uiskin.atlas"));

        this.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/4);
        this.setPosition(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight());

        container = new Table(this.getSkin());
        text = new Label("", getSkin());

        Label enter = new Label("Appuyer sur Entrée pour continuer", getSkin());

        container.add(text).expand().top().fillX();
        container.row();
        container.add(enter).expandX();

        this.add(container).expand().fill();
        this.setVisible(false);
    }

    /**
     * Afficher le dialogue au départ
     * @param content
     */
    public void showDialog(Content content){
        dialog = new DialogNode();
        dialog.addText(content.getContent());
        text.setText(dialog.getText());
        this.setVisible(true);
    }

    /**
     * méthode de test
     * @param content
     */
    public void showDialog(String content){
        dialog = new DialogNode();
        dialog.addText(content);
        text.setText(dialog.getText());
        this.setVisible(true);
    }

    /**
     * Cette méthode affiche la prochaine parti du dialogue si elle existe sinon elle cache
     * la fenêtre
     */
    public void nextPart(){
        if (dialog instanceof DialogNode){
            //on prends un des chemins de l'arbre
            if (((DialogNode) dialog).getRight() != null){
                dialog = ((DialogNode) dialog).getRight();
                text.setText(dialog.getText());
                return;
            }else if(((DialogNode) dialog).getLeft() != null){
                dialog = ((DialogNode) dialog).getLeft();
                text.setText(dialog.getText());
                return;
            }
        }
        //le dialogue est fini
        this.setVisible(false);
    }
}

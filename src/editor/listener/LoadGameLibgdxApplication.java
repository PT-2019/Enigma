package editor.listener;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import game.MyGame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Charge l'application libgdx dans une fenêtre SWING
 */
public class LoadGameLibgdxApplication implements ActionListener {

    /** la fenêtre dans laquelle charger l'applciation **/
    private final JFrame frame;

    /**
     * Charge l'application libgdx dans une fenêtre SWING
     * @param frame la fenêtre dans laquelle charger l'applciation
     */
    public LoadGameLibgdxApplication(@NotNull JFrame frame){
        this.frame = Objects.requireNonNull(frame);//forcément une fenêtre
    }

    /**
     * Lorsque l'observateur est appelé, vide la fenêtre et charge l'application
     * dedans. Change l'observateur de fermeture de la fenêtr pour qu'il ferme l'application LIBGDX
     * à sa fermeture.
     *
     * @param actionEvent l'évènement qui déclenche le changement, peut être null
     */
    @Override
    public void actionPerformed(@Nullable ActionEvent actionEvent) {
        final Container container = this.frame.getContentPane();//récupère contenu fenêtre
        container.removeAll();//vide la fenêtre
        container.setLayout(new BorderLayout());
        LwjglAWTCanvas canvas = new LwjglAWTCanvas(new MyGame());//Récupère le jeu
        container.add(canvas.getCanvas(), BorderLayout.CENTER);//ajoute le jeu
        //change le listiner pour qu'il ferme l'application
        this.frame.addWindowListener(new CloseWindowLibgdxApplication(canvas));
        this.frame.revalidate();//met à jour #burk
    }
}

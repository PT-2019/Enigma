package editor.listener;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Ferme une fenêtre qui contient une application LIBGDX
 */
public class CloseWindowLibgdxApplication extends WindowAdapter {

    /**
     * L'application Libgdx
     */
    private final LwjglAWTCanvas application;

    /**
     * Ferme une fenêtre qui contient une application LIBGDX
     * @param application canvas de l'application
     */
    public CloseWindowLibgdxApplication(LwjglAWTCanvas application){
        this.application = application;
    }

    /**
     * Méthode appelée si on cherche à fermer la fenêtre.
     *
     * Si la fenêtre contient un application libgdx, elle la ferme
     * proprement avant de quitter
     *
     * @param windowEvent évenement de fermeture
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        if(this.application != null){
            this.application.stop();
            this.application.exit();
        }
        System.exit(0);
    }
}

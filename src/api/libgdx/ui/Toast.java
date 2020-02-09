package api.libgdx.ui;

import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

/**
 * Popup d'informations en mode toast android
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 04/02/2020
 * @since 6.0 04/02/2020
 */
public class Toast extends Window {

    /**
     * Size
     * @since 6.1
     */
    protected static final float WIDTH = 300;

    /**
     * Size
     * @since 6.1
     */
    protected static final float HEIGHT = 70;

    /**
     * Padding
     * @since 6.1
     */
    private static final float PADDING = 10;

    /**
     * Valeur de base des attributs fadeIn, fadeOut et delay
     * @since 6.0
     */
    @SuppressWarnings("WeakerAccess")
    protected static final float BASE_FADE_IN_DELAY = 0.3f;

    /**
     * Valeur de base des attributs fadeIn, fadeOut et delay
     * @since 6.0
     */
    @SuppressWarnings("WeakerAccess")
    protected static final float BASE_FADE_OUT_DELAY = 0.5f;

    /**
     * Valeur de base des attributs fadeIn, fadeOut et delay
     * @since 6.0
     */
    @SuppressWarnings("WeakerAccess")
    protected static final float DELAY = 1f;

    /**
     * Temps en secondes pour que le toast apparaissent
     * @since 6.0
     */
    protected float fadeIn;

    /**
     * Temps en secondes pour que le toast disparaisse
     * @since 6.0
     */
    protected float fadeOut;

    /**
     * Temps en secondes pendant lequel le toast est affiché
     * @since 6.0
     */
    protected float delay;

    /**
     * Crée un toast, caché {@link #setVisible(boolean)}
     * @since 6.0
     */
    public Toast(String jsonPath, String skinPath, String title) {
        //chargement du style de la fenêtre
        super(title, LibgdxUtility.loadSkin(jsonPath, skinPath));
        //attributes
        this.fadeIn = BASE_FADE_IN_DELAY;
        this.fadeOut = BASE_FADE_OUT_DELAY;
        this.delay = DELAY;
        //size
        this.setSize(WIDTH, HEIGHT);
        //title center
        this.getTitleLabel().setAlignment(Align.center);
        //don't show
        this.setVisible(false);
    }

    /**
     * "Cache le composant" mais il est toujours mis à jour (différent de setVisible)
     * @param b true pour cacher sinon false
     * @since 6.0
     */
    @SuppressWarnings("SameParameterValue")
    protected void hide(boolean b){
        if(b) this.getColor().a = 0f;
        else this.getColor().a = 1f;
    }

    /**
     * Retourne l'opacité
     * @return opacité entre 0 et 1.
     * @since 6.0
     */
    private float getAlpha() {
        return this.getColor().a;
    }

    /**
     * Update du toast
     * @param text texte a afficher
     */
    public void update(String text){
        if(this.hasActions()){
            //change juste le texte et on reset le temps d'affichage si +de 50% visible
            if(this.getAlpha() >= 0.5f){ //50% affiché, on skip a 100%
                this.clearActions();
                this.clear();
                this.addAction(Actions.fadeIn(0.01f));//instant
                this.add(text);
                this.resize();
                this.addAction(Actions.sequence(Actions.delay(delay), Actions.fadeOut(fadeOut)));

                return;
            }
        }

        //sinon lance l'action normale
        this.addAction(Actions.sequence(Actions.run(new ShowToast(this, text))));
    }

    /**
     * Re-dimensionne le toast si besoin.
     * Appelé dès que le contenu est changé
     * @since 6.1
     */
    protected void resize() {
        this.pack();
        if(this.getWidth() > WIDTH) this.setSize(PADDING*2 + this.getWidth(), HEIGHT);
        else this.setSize(WIDTH, HEIGHT);
    }

    /**
     * Affiche le toast
     *
     * @author Jorys-Micke ALAÏS
     * @author Louka DOZ
     * @author Loic SENECAT
     * @author Quentin RAMSAMY-AGEORGES
     *
     * @version 6.0 05/02/2020
     * @since 6.0 05/02/2020
     */
    private static final class ShowToast implements Runnable {

        private final Toast toast;
        private final String text;

        ShowToast(Toast toast, String text){
            this.toast = toast;
            this.text = text;
        }

        @Override
        public void run() {
            this.toast.clear();
            this.toast.add(this.text);
            this.toast.resize();
            this.toast.addAction(
                    Actions.sequence(
                            Actions.fadeIn(this.toast.fadeIn),
                            Actions.delay(this.toast.delay),
                            Actions.fadeOut(this.toast.fadeOut)
                    )
            );
        }
    }
}

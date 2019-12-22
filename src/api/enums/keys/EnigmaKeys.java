package api.enums.keys;

/**
 * Interface qui représente les touches du jeu
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 22/12/2019
 * @since 4.0 22/12/2019
 */
public interface EnigmaKeys {

    /**
     * Renvoi true si key1 ou key2 correspondent a keyCode
     *
     * @param keyCode une touche {@link com.badlogic.gdx.Input.Keys}
     * @return true si key1 ou key2 correspondent a keyCode
     * @since 4.0 22/12/2019
     */
    boolean isKey(int keyCode);

    /**
     * Renvoi la touche1
     *
     * @return la touche 1
     * @since 4.0 22/12/2019
     */
    int getKey1();

    /**
     * Renvoi la touche2
     *
     * @return la touche 2
     * @since 4.0 22/12/2019
     */
    int getKey2();

    /**
     * Change les touches
     *
     * @param key1 nouvelle touche 1
     * @param key2 nouvelle touche 2
     * @since 4.0 22/12/2019
     */
    void set(int key1, int key2);

}

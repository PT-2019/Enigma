package editor.entity.interfaces;

/**
 * Définie une entité comme verrouillable ou déverrouillable
 */
public interface Lockable {

    /**
     * Verrouille l'objet
     */
    void lock();

    /**
     * Déverrouille l'objet
     */
    void unlock();

    /**
     * Indique si l'objet est verrouillé
     *
     * @return true si il est verrouillé, false sinon
     */
    boolean isLocked();
}

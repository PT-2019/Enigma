package editor.entities.interfaces;

/**
 * Définie une entité comme verrouillable ou déverrouillable
 */
public interface Lockable {

    /**
     * Verrouille l'objet
     */
    public void lock();

    /**
     * Déverrouille l'objet
     */
    public void unlock();

    /**
     * Indique si l'objet est verrouillé
     * @return true si il est verrouillé, false sinon
     */
    public boolean isLocked();
}

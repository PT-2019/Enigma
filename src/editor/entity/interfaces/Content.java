package editor.entity.interfaces;

/**
 * Permet à un objet d'avoir un contenu lisible par les joueurs
 *
 * @version 1.0
 */
public interface Content {

    /**
     * Ajoute un contenu à l'objet
     *
     * @param content Contenu à ajouter
     */
    void addContent(String content);

    /**
     * Obtenir le contenu
     *
     * @return le contenu, le contenu peut être vide
     */
    String getContent();
}

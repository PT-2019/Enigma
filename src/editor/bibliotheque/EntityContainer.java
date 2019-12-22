package editor.bibliotheque;

import editor.entity.EntitySerializable;
import editor.utils.ConvenienceMethod;
import org.intellij.lang.annotations.MagicConstant;

import javax.swing.*;
import java.awt.dnd.DragSource;

/**
 * Il s'agit d'une case des menu o&#249; on sélectionne des entités pour les placer
 * sur la map.
 */

/**
 * Desc
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version x ${DATE}
 * @see ???
 * @since x ${DATE}
 */
@Deprecated
public class EntityContainer extends JLabel {

    private EntitySerializable entity;

    public EntityContainer() {
    }

    /**
     * Met une entité dans la case
     *
     * @param entity entité a placer
     */
    public void setEntity(EntitySerializable entity) {
        this.entity = entity;
        this.setIcon(new ImageIcon(entity.getPath()));
    }

    /**
     * Retourne le contenu de la case
     *
     * @return l'entité contenue dans la case
     */
    public EntitySerializable getContent() {
        if (entity == null)
            throw new IllegalStateException("there isn't an entity!");

        return entity;
    }

    /**
     * Convenience method pour définir cette entité comme déplaçable par le drag and drop
     *
     * @param actionCopy  le type d'action
     * @param dragAndDrop le gestionnaire de drag and drop
     */
    @ConvenienceMethod
    public void setDragSource(@MagicConstant int actionCopy, DragAndDropDND dragAndDrop) {
        new DragSource().createDefaultDragGestureRecognizer(this, actionCopy, dragAndDrop);
    }
}

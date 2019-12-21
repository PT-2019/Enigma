package api.actors;

/**
 * Crée un acteur qui peut drag and drop
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0 21/12/2019
 * @since 4.0 21/12/2019
 */
public abstract class GameActorDragAndDrop extends GameActorTextured {

	/**
	 * Indique s'il est glissable ou déposable, false de base
	 */
	protected boolean draggable, droppable;

	/**
	 * Crée un acteur glissable déposable.
	 * On doit lui ajouter un observateur qui s'occupe de le déplacer.
	 * Ceci n'est que le model
	 *
	 * @since 4.0 20/12/2019
	 */
	public GameActorDragAndDrop() {
		super();
		this.draggable = false;
		this.droppable = false;
	}

	/**
	 * Retourne true si l'acteur est glissable
	 * @return true si l'acteur est glissable
	 *
	 * @since 4.0 20/12/2019
	 */
	public boolean isDraggable(){ return this.draggable; }

	/**
	 * Retourne true si l'acteur est déposable
	 * @return true si l'acteur est déposable
	 *
	 * @since 4.0 20/12/2019
	 */
	public boolean isDroppable(){ return this.droppable; }
}


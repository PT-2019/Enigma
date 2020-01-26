package editor.enigma.create.enigma;

import api.entity.GameObject;
import api.entity.types.Living;
import api.entity.types.Lockable;
import api.entity.types.NeedContainer;
import editor.entity.NPC;

/**
 * Opérations disponibles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public enum Operations {
	GIVE("Donne un object à l'utilisateur", "Objects uniquement (livre...).", null),
	SUMMON("Invoque une entité", "Seulement des personnages, pas de héros.", null),

	//TODO: IMPOSSIBLE UNLOCK ENTITES MENU
	UNLOCK( "Dévérouille un object", "Seulement un object \"Décors\" fermable.", null),
	;

	public final String value;
	/**
	 * Le nom d'une classe dont la méthode run crée.
	 *
	 * Non utilisé et non fonctionnel.
	 *
	 * La classe possèderait un constructeur qui prends une EnigmaView et
	 * un OperéationListener.
	 *
	 * @see editor.enigma.create.listeners.OperationListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public final Class<? extends Runnable> initClass;
	/**
	 * ignore
	 */
	public final String tooltip;
	/**
	 * Message si la condition n'est pas respectée
	 */
	public final String restrict;

	Operations(String value, String restrict, Class<? extends Runnable> initClass) {
		this.value = value;
		this.initClass = initClass;
		this.tooltip = "";
		this.restrict = restrict;
	}

	/**
	 * Retourne true si le gameObject respecte les conditions de l'opération
	 * @param object gameObject
	 * @return true si le gameObject respecte les conditions de l'opération
	 */
	public boolean isValid(GameObject object) {
		//cette méthode n'est pas géniale, on a écrit à la main les conditions
		if(this.equals(GIVE)){
			return (object instanceof NeedContainer);
		} else if(this.equals(SUMMON)){
			if(object instanceof Living){
				if(object instanceof NPC){
					return !((NPC) object).isHero();
				}
				return true;
			}
		} else if(this.equals(UNLOCK)){
			return (object instanceof Lockable);
		}
		return false;
	}
}

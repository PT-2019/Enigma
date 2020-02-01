package editor.menus;

import common.entities.GameObject;
import data.TypeEntity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Liste des options popup et les types d'entités qui
 * ont le droit a cet option
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public enum AvailablePopUpOption {
	/**
	 * Activer ou non la collision
	 */
	SET_ACCESS(TypeEntity.ITEM, null, TypeEntity.PASSAGE),
	/**
	 * Leur donner un nom
	 */
	SET_NAME(TypeEntity.LIVING),
	/**
	 * Voir, ajouter, supprimer des énigmes
	 */
	MANAGE_ENIGMAS(TypeEntity.ENIGMA_CONTAINER),
	/**
	 * Supprimer l'entité
	 */
	DELETE(TypeEntity.ALL),
	/**
	 * Ajoute d'autres entités dans cette entité
	 */
	ADD_ITEMS(TypeEntity.CONTAINER, null, TypeEntity.CONTAINER_MANAGER),
	/**
	 * Ouvre un champ de saisie pour définir le contenu de l'object.
	 * Pré-rempli avec l'ancien contenu.
	 */
	SET_CONTENT(TypeEntity.CONTENT),
	/**
	 * Ouvre un menu permettant de cacher l'un des deux salles
	 * des objets de type passage
	 */
	SET_PASSAGE_ROOM_HIDDEN(),
	/**
	 * Checkbox qui définit si l'entité est verrouillée ou non
	 */
	SET_LOCKED(TypeEntity.LOCKABLE),
	/**
	 * Checkbox qui définit si l'entité est activée ou non
	 */
	SET_ACTIVATED(TypeEntity.ACTIVATABLE),
	DEFINE_AS_HERO(TypeEntity.NPC),
	DEFINE_AS_NPC(TypeEntity.PLAYER),
	;

	private ArrayList<TypeEntity> entities;
	private ArrayList<TypeEntity> excluded;

	AvailablePopUpOption(TypeEntity... args) {
		this.entities = new ArrayList<>();
		this.excluded = new ArrayList<>();
		if (args != null) {
			boolean end = false;
			for (TypeEntity e : args) {
				if (e == null) end = true;
				else {
					if (!end) entities.add(e);
					else excluded.add(e);
				}
			}
		}
	}

	/**
	 * Retourne si l'option est disponible pour un type d'entité
	 *
	 * @param option l'option
	 * @param entity une entité
	 * @return true si l'option est disponible pour un type d'entité
	 */
	public static boolean isAvailable(AvailablePopUpOption option, GameObject entity) {
		if (!option.excluded.isEmpty()) {
			for (Map.Entry<TypeEntity, Boolean> entry : entity.getImplements().entrySet()) {
				if (entry.getValue() && option.excluded.contains(entry.getKey())) {
					return false;
				}
			}
		}

		for (Map.Entry<TypeEntity, Boolean> entry : entity.getImplements().entrySet()) {
			if (entry.getValue() && option.entities.contains(entry.getKey())) {
				return true;
			}
		}
		return false;
	}
}

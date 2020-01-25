package api.entity;

import api.entity.types.EnigmaContainer;
import api.entity.types.NeedContainer;

/**
 * Un item consommable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public interface Consumable extends EnigmaContainer, Entity, NeedContainer, Item {

	//stack-able ?
}

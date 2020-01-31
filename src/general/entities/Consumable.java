package general.entities;

import general.entities.types.EnigmaContainer;
import general.entities.types.NeedContainer;

/**
 * Un item consommable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public interface Consumable extends EnigmaContainer, Entity, NeedContainer, Item {

	//stack-able ?
}

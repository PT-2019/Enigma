package api.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Une annotation pour les classes crées uniquement pour faciliter
 * l'utilisation
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
@Target(ElementType.TYPE)
public @interface ConvenienceClass {
}

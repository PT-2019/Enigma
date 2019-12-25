package api.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Une annotation pour les méthodes créées uniquement pour faciliter
 * l'utilisation
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
@Target(ElementType.METHOD)
public @interface ConvenienceMethod {
}

package api.utils.annotations;

/**
 * Indique du contenu temporaire
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 25/12/2019
 * @since 4.0 25/12/2019
 */
public @interface Temporary {

	/**
	 * Pourquoi c'est temporaire
	 *
	 * @return Pourquoi c'est temporaire
	 */
	String reason() default "";

	/**
	 * Depuis quand on a implémenter le contenu temporaire
	 *
	 * @return quand on a implémenter le contenu temporaire
	 */
	float since() default 0;

	/**
	 * Indique une aide pour un patch possible
	 *
	 * @return une aide pour un patch possible
	 */
	String aidePatch() default "";

}

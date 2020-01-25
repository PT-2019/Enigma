package api.hud;

/**
 * Un composant ré-utilisable a l'infini
 *
 * @author Loic SENECAT
 *
 * @version 4.0
 * @since 4.0
 */
public interface ResetComponent {

	/**
	 * Nettoye le composant
	 */
	void clean();

	/**
	 * Initialise le component
	 */
	void initComponent();

}

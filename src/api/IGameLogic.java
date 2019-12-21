package api;

/**
 * Il s'agit de la structure d'un jeu
 *
 * @version 3.0 10 décembre 2019
 * @since 3.0 10 décembre 2019
 */
public interface IGameLogic {

	/**
	 * Initialisations, allocations...
	 */
	void init();

	/**
	 * récupération des saisies
	 */
	void input();

	/**
	 * Mise a jour du jeu
	 *
	 * @param dt temps depuis le dernier appel
	 */
	void update(float dt);

	/**
	 * Affiche a l'écran
	 */
	void render();

	/**
	 * Redimensionnement de l'écran
	 *
	 * @param width  largeur de l'écran
	 * @param height hauteur de l'écran
	 */
	void resize(int width, int height);

	/**
	 * Ferme et libère les ressources
	 */
	void dispose();
}

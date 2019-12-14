package game.api;

/**
 * Interface witch represent basic and common pattern of a game.
 *
 * @version 3.0 10 décembre 2019
 */
public interface IGameLogic {

	/**
	 * Initialisations, allocations...
	 */
	void init();

	/**
	 * poll user input
	 */
	void input();

	/**
	 * Update of the game
	 *
	 * @param dt elapsed time since last call
	 */
	void update(float dt);

	/**
	 * render (print) everything on the screen
	 */
	void render();

	/**
	 * Called each time the screen is resized
	 *
	 * @param width  screen width
	 * @param height screen height
	 */
	void resize(int width, int height);

	/**
	 * Close and frees
	 */
	void dispose();
}

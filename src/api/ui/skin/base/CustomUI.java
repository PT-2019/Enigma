package api.ui.skin.base;

import javax.swing.JComponent;
import java.awt.Graphics;

/**
 * Méthode générales aux classes d'UI
 *
 * @param <T> la classe d'UI
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1
 * @since 4.0
 */
public interface CustomUI<T extends CustomUI> {

	/**
	 * Méthode pour dessiner l'ui
	 *
	 * @param g gestionnaire du dessin
	 * @param c le composent représenté par l'ui
	 */
	void paint(Graphics g, JComponent c);

	/**
	 * Crée une copie de l'ui
	 * <p>
	 * Attention, vous devez override duplicate et faire un appel de duplicate(this)
	 * sinon vous aurez un problème de types.
	 * <p>
	 * On suppose que vous avez codé cette méthode du style :
	 * protected  &lt;T extends --YOUR CLASS--&gt; T duplicate(T customYourClassUI);
	 *
	 * @return une copie de l'ui
	 */
	T duplicate();

}

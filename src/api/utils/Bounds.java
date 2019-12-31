package api.utils;

import api.utils.annotations.ConvenienceClass;
import api.utils.annotations.Immutable;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Un rectangle représentant la taille de quelque chose
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 22/12/2019
 * @since 4.0 22/12/2019
 */
@Immutable
@ConvenienceClass
public final class Bounds {

	/**
	 * Les 4 sommets
	 */
	public final float left, right, top, bot;

	/**
	 * Crée un rectangle représentant la taille de quelque chose
	 *
	 * @param left  position x la plus à gauche
	 * @param right position x la plus à droite
	 * @param top   position y la plus haute
	 * @param bot   position y la plus basse
	 */
	public Bounds(float left, float right, float top, float bot) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bot = bot;
	}

	/**
	 * Crée un rectangle représentant la taille de quelque chose depuis
	 * un rectangle libgdx
	 *
	 * @param bounds un rectangle libgdx
	 * @since 4.0 22/12/2019
	 */
	public Bounds(Rectangle bounds) {
		this.left = bounds.x;
		this.right = this.left + bounds.width;
		this.top = bounds.y + bounds.height;
		this.bot = bounds.y;
	}

	/**
	 * Retourne true si le rectangle contient un point
	 *
	 * @param pos un point
	 * @return true si le rectangle contient un point
	 * @since 4.0 22/12/2019
	 */
	public boolean contains(Vector2 pos) {
		if (pos.x >= this.left && pos.x <= this.right) {
			return pos.y >= this.bot && pos.y <= this.top;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Bounds{" + "left=" + left + ", right=" + right + ", top=" + top + ", bot=" + bot + '}';
	}
}

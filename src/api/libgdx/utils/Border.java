package api.libgdx.utils;

import api.utils.annotations.Immutable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Bordures de la map LIBGDX
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0
 * @since 3.0
 */
@Immutable
public final class Border extends ShapeRenderer {

	private static final Color BORDER_COLOR = new Color(0, 0, 0, 255);

	private int col;
	private int row;
	private int tile;

	/**
	 * Crée une bordure d'une map
	 *
	 * @param col  nombre de colonnes de la map
	 * @param row  nombre de lignes de la map
	 * @param tile largeur d'une case de la map (tile)
	 */
	public Border(int col, int row, int tile) {
		this.col = col;
		this.row = row;
		this.tile = tile;
	}

	/**
	 * Dessine la bordure
	 */
	public void draw() {
		this.begin(ShapeType.Line);
		this.setColor(BORDER_COLOR);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				this.rect(j * tile, tile * i, tile, tile);
			}
		}
		this.end();
	}
}

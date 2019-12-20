package game.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Border extends ShapeRenderer {
	private int col;
	private int row;
	private int tile;

	public Border(int col, int row, int tile) {
		this.col = col;
		this.row = row;
		this.tile = tile;
	}

	public void draw() {
		this.begin(ShapeType.Line);
		this.setColor(0, 0, 0, 255);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				this.rect(j * tile, tile * i, tile, tile);
			}
		}

		this.end();
	}
}

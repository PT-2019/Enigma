package editor.Texture;

public abstract class  TextureType{
	
	protected int col;

	protected int row;

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public void setCol(int col) {
		this.col = col; 
	}

	public void setRow(int row) {
		this.row  = row;
	}

	public abstract String getPath();
}
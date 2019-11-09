package editor.Texture.Ancienne_Structure;

public enum TextureType{
	TERRE(0,0),
	EAU(0,0),
	SOL(0,0),
	MUR(0,0);

	private int col;

	private int row;

	TextureType(int col,int row) {
		this.col = col;
		this.row = row;
	}

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
}
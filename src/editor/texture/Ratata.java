package editor.texture;

public enum Ratata implements TextureType{
	DEVANT_1(0,0)
	;//enum sans valeurs

	private final static String path="assets/monsters/019.png";

	private int col;

	private int row;

	Ratata(int col,int row){ //constructeur package-private
		this.row = row;
		this.col = col;
	}

	@Override
	public int getCol() {
		return col;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public void setCol(int col) {
		this.col = col; 
	}

	@Override
	public void setRow(int row) {
		this.row  = row;
	}

	@Override
	public String getPath(){
		return path;
	}
}
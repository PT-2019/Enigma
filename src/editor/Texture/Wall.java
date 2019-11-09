package editor.texture;

public enum Wall implements TextureType{
	//ces valeurs sont fictives
	NORTH(0,0),
	SOUTH(0,1),
	OUEST(0,2),
	EAST(0,3);

	//a remplir
	private final static String path="";

	private int col;

	private int row;

	public Ratata(int col,int row){
		this.row = row;
		this.col = col;
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


	@Override
	public String getPath(){
		return path;
	}
}
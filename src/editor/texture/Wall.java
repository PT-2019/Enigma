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

	Wall(int col,int row){
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
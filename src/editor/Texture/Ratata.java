package editor.texture;

public enum Ratata implements TextureType{

	private final static String path="019.png";

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
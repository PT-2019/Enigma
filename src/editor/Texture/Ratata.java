public class Ratata extends TextureType{

	private final static String path="019.png";

	public Ratata(int col,int row){
		this.row = row;
		this.col = col;
	}

	@Override
	public String getPath(){
		return path;
	}
}
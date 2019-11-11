package editor.texture;

/**
 * Enum pour tester
 */
public enum Ratata implements TextureType{
	DEVANT_1(0,0),//enum sans valeurs
    GAUCHE_1(1,0),
	DROITE_1(2,1),
    DERRIERE_1(3,0);

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
	public String getPath(){
		return path;
	}
}
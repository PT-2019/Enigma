package common.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import data.Layer;
import data.TypeEntity;

import java.util.EnumMap;

/**
 * Une classe représentant un GameObject null ou non existant
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 10/02/2020
 * @since 6.0 10/02/2020
 */
public class NullGameObjet implements GameObject {

	//toutes les méthodes retournent un résultat "non null"

	@Override
	public float getGameObjectWidth() {
		return 0;
	}

	@Override
	public float getGameObjectHeight() {
		return 0;
	}

	@Override
	public void setDimension(int width, int height) {
	}

	@Override
	public Vector2 getGameObjectPosition() {
		return new Vector2();
	}

	@Override
	public void setGameObjectPosition(Vector2 pos) {
	}

	@Override
	public Array<Float> getTiles(Layer layer) {
		return new Array<>();
	}

	@Override
	public void setTiles(Array<Float> texture, Layer layer) {
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		return TypeEntity.emptyMap();
	}

	@Override
	public String getReadableName() {
		return "";
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void setID(int id) {
	}
}

package common.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import data.Layer;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Un gameObject null
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 18/02/2020
 * @since 6.0 18/02/2020
 */
public class NullGameObject implements GameObject {

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
	public Map<Layer, Array<Float>> getTiles() {
		return new HashMap<>();
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
	public boolean isTemp() {
		return true;
	}

	@Override
	public void setTemp(boolean temp) {
	}

	@Override
	public int getID() {
		return Integer.MIN_VALUE;
	}

	@Override
	public void setID(int id) {

	}
}

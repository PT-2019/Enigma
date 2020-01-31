package general.entities.special;

import data.TypeEntity;
import general.entities.types.AbstractGameObject;
import general.entities.types.ContainersManager;
import general.language.GameFields;
import general.language.GameLanguage;

import java.util.EnumMap;

/**
 * Une pièce
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 24/12/2019
 * @since 4.0 24/12/2019
 */
public class Room extends AbstractGameObject implements ContainersManager {

	/**
	 * Crée une pièce
	 *
	 * @since 4.0
	 */
	public Room() {
		super(-1);
	}

	/**
	 * Crée une pièce d'une certaine taille
	 *
	 * @param cols nombre de colonnes
	 * @param rows nombre de lignes
	 * @since 4.0
	 */
	public Room(int cols, int rows) {
		super(-1);
		this.setDimension(cols, rows);
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.CONTAINER, true);
		imp.put(TypeEntity.CONTAINER_MANAGER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.ROOM);
	}
}

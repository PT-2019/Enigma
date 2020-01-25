package game.entity.item;

import api.entity.AbstractGameObject;
import api.entity.types.Container;
import api.entity.types.ContainersManager;
import api.enums.TypeEntity;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.GameFields;

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
public class Room extends AbstractGameObject implements ContainersManager, Container {

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

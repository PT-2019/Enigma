package common.entities.special;

import common.entities.types.AbstractGameObject;
import common.entities.types.ContainersManager;
import common.language.GameFields;
import common.language.GameLanguage;
import data.TypeEntity;

import java.util.EnumMap;

/**
 * Une pièce
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 4.0 24/12/2019
 */
public class Room extends AbstractGameObject implements ContainersManager {

	private boolean discovered, showed;

	/**
	 * Crée une pièce
	 *
	 * @since 4.0
	 */
	public Room() {
		super(-1);
		this.discovered = false;
		this.showed = true;
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
		this.discovered = false;
		this.showed = true;
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.CONTAINER, true);
		imp.put(TypeEntity.CONTAINER_MANAGER, true);
		return imp;
	}

	@Override
	public String toString() {
		return super.toString() + " id=" + this.id;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.ROOM);
	}

	/**
	 * Retourne si la room a étée découverte
	 *
	 * @return true si la room a étée découverte
	 * @since 6.0
	 */
	public boolean isDiscovered() {
		return this.discovered;
	}

	/**
	 * Définit si la room a étée découverte
	 *
	 * @param discovered true si la room a étée découverte
	 * @since 6.0
	 */
	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	/**
	 * Retourne si la room est cachée, au sens que son contenu est caché
	 *
	 * @return true si la room est cachée, au sens que son contenu est caché
	 * @since 6.0
	 */
	public boolean isShowed() {
		return showed;
	}

	/**
	 * Retourne si la room est cachée, au sens que son contenu est caché
	 *
	 * @param showed true si la room est cachée, au sens que son contenu est caché
	 * @since 6.0
	 */
	public void setShowed(boolean showed) {
		this.showed = showed;
	}
}

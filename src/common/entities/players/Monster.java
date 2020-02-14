package common.entities.players;

import com.badlogic.gdx.maps.MapProperties;
import common.entities.types.AbstractLivingEntity;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Un monstre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class Monster extends AbstractLivingEntity implements EntityGame {

	/**
	 * Points de vie maximaux du joueur
	 */
	private static final int MAX_MONSTER_PV = 100;

	public Monster() {
		this(-1);
	}

	/**
	 * @param name Nom du monstre
	 */
	public Monster(String name) {
		this(-1, name);
	}

	/**
	 * @param id ID
	 */
	public Monster(int id) {
		this(id, null);
	}

	/**
	 * @param id   ID
	 * @param name Nom du joueur
	 */
	public Monster(int id, String name) {
		super(id, name);
		this.pv = MAX_MONSTER_PV;
	}

	//implements

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.MONSTER, true);
		imp.put(TypeEntity.LIVING, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.MONSTER);
	}

	// save

	@Override
	public HashMap<SaveKey, String> getSave() {
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.KEY, this.key);
		save.put(PlayerSave.JSON, this.json);
		save.put(PlayerSave.NAME, this.name);
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.key = data.get(PlayerSave.KEY.getKey(), String.class);
		this.json = data.get(PlayerSave.JSON.getKey(), String.class);
		this.name = data.get(PlayerSave.NAME.getKey(), String.class);
		if (this.name.isEmpty() || this.name.isBlank()) this.name = this.key;
	}

	//toString

	@Override
	public String toString() {
		return "Monster{" + "MAX_PLAYER_PV=" + MAX_MONSTER_PV + ", pv=" + pv + ", id=" + id +
				", tiles=" + tiles + ", bounds=" + bounds + '}';
	}
}

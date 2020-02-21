package common.entities.players;

import api.utils.Utility;
import com.badlogic.gdx.maps.MapProperties;
import common.entities.Item;
import common.entities.types.AbstractLivingEntity;
import common.entities.types.Container;
import common.entities.types.Content;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveInventory;
import common.save.entities.SaveKey;
import data.TypeEntity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Un personnage non joueur (NPC)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class NPC extends AbstractLivingEntity implements Container, Content {

	/**
	 * Points de vie maximaux du joueur
	 */
	private static final int MAX_NPC_PV = 10000;

	/**
	 * True si c'est un héro possible
	 */
	private boolean hero;

	/**
	 * Dialogue du monstre
	 */
	private String dialog;

	private ArrayList<Item> items;

	public NPC() {
		this(-1);
	}

	/**
	 * @param name Nom du npc
	 */
	public NPC(String name) {
		this(-1, name);
	}

	/**
	 * @param id ID
	 */
	public NPC(int id) {
		this(id, null);
	}

	/**
	 * @param id   ID
	 * @param name Nom du joueur
	 */
	public NPC(int id, String name) {
		super(id, name);
		this.pv = MAX_NPC_PV;
		this.items = new ArrayList<>();
		this.hero = false;
	}

	//implements

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.NPC, true);
		imp.put(TypeEntity.CONTENT, true);
		imp.put(TypeEntity.LIVING, true);
		imp.put(TypeEntity.CONTAINER, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.NPC);
	}

	//hero

	public boolean isHero() {
		return hero;
	}

	public void setHero(boolean hero) {
		this.hero = hero;
	}

	//save

	@Override
	public HashMap<SaveKey, String> getSave() {
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.KEY, this.key);
		save.put(PlayerSave.JSON, this.json);
		save.put(PlayerSave.HERO, String.valueOf(this.hero));
		//save des ids des éléments de l'inventaire
		save.put(PlayerSave.INVENTORY, SaveInventory.save(this.items));
		save.put(PlayerSave.NAME, this.name);
		save.put(PlayerSave.CONTENT, this.dialog);
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.key = data.get(PlayerSave.KEY.getKey(), String.class);
		this.json = data.get(PlayerSave.JSON.getKey(), String.class);
		this.hero = Boolean.parseBoolean(data.get(PlayerSave.HERO.getKey(), String.class));
		//pourrait passer la map en argument de load ? Fait une boucle qui dans un thread demande en permanence
		//si chargement fini et si oui alors fini le chargement des items en récupérant selon ids.
		SaveInventory.load(data.get(PlayerSave.INVENTORY.getKey(), String.class));
		this.name = data.get(PlayerSave.NAME.getKey(), String.class);
		this.dialog = data.get(PlayerSave.CONTENT.getKey(), String.class);
		if (dialog != null) this.dialog = Utility.asciiEscapedToNormalString(this.dialog);
		if (this.name.isEmpty() || this.name.isBlank()) this.name = this.key;
	}

	//toString

	@Override
	public String toString() {
		return "NPC{" + "MAX_NPC_PV=" + MAX_NPC_PV + ", pv=" + pv + ", id=" + id +
				", tiles=" + tiles + ", bounds=" + bounds + ", name='" + name + '\'' + ", hero=" + hero + '}';
	}

	//inventory

	@Override
	public boolean addItem(Item item) {
		return this.items.add(item);
	}

	@Override
	public boolean removeItem(Item item) {
		return this.items.remove(item);
	}

	@Override
	public ArrayList<Item> getItems() {
		return this.items;
	}

	@Override
	public String getContent() {
		return this.dialog;
	}

	@Override
	public void setContent(String content) {
		this.dialog = content;
	}
}

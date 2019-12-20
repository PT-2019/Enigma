package game.entity;

import editor.entity.EntitySerializable;

public class EntityContainer extends ActeurTextureGlisserDeposer {

	private final EntitySerializable entity;

	/**
	 * Un crapit, n'appartient à aucun joueur
	 */
	public EntityContainer(String path) {
		this.setAnimation(path,1,1,1);
		this.faireBoucler(true);

		//déplaçable
		this.glissable = true;
		this.deposable = false;

		this.entity = null;

		//collision
		this.setRecouvrement(6);
	}

	public EntityContainer(EntitySerializable entitySerializable) {
		this.setAnimation(entitySerializable.getPath(),1,1,1);
		this.faireBoucler(true);
		this.entity = entitySerializable;

		//déplaçable
		this.glissable = true;
		this.deposable = false;

		//collision
		this.setRecouvrement(6);
	}
}

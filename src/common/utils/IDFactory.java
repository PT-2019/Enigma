package common.utils;

import api.utils.annotations.ConvenienceClass;
import common.entities.types.IDInterface;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Crée et gère des ID à attribuer à des objets
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 2.0
 */
public class IDFactory {

	/**
	 * L'id d'un object n'ayant pas d'id
	 * @since 6.0
	 */
	public static final int ID_FACTORY_NO_ID = Integer.MIN_VALUE;
	/**
	 * indice du premier id négatif
	 */
	private static final int BASE_REVERSE = -1;
	/**
	 * indice du premier id positif
	 */
	private static final int BASE_NORMAL = 0;

	/**
	 * Retourne l'instance unique
	 * @return l'instance unique
	 * @since 2.0
	 */
	@Deprecated
	public static IDFactory getInstance() {
		return new IDFactory();
	}

	/**
	 * Liste des objets enregistrés
	 * @since 2.0
	 */
	private ArrayList<IDInterface> list;

	/**
	 * Compteur des ids
	 * @since 6.0
	 */
	private int count, countReverse;

	/**
	 * Liste des ids des objets supprimés
	 * @since 6.0
	 */
	private ArrayList<Integer> deleted;

	/**
	 * Crée une factory
	 * @since 2.0
	 */
	public IDFactory() {
		this.reset();
	}

	/**
	 * Remet à zéro l'IDFactory
	 */
	public void reset(){
		this.list = new ArrayList<>();
		this.deleted = new ArrayList<>();
		this.count = BASE_NORMAL;
		this.countReverse = BASE_REVERSE;
	}

	/**
	 * Ajoute un ID à un object.
	 * Si l'élément était déjà contenu dans liste, alors rien n'est fait.
	 *
	 * @param object Objet sur lequel on veut ajouter un ID
	 *
	 * @since 2.0
	 */
	public void newID(IDInterface object) {
		if(this.list.contains(object)) return;

		if(!this.deleted.isEmpty()){
			int s = BASE_REVERSE;
			for (int i = 0; i < this.deleted.size(); i++) {
				if(this.deleted.get(i) >= BASE_NORMAL)
					s = i;
			}
			//donne un id qui s'est libéré
			System.out.println(s+" "+this.deleted);
			if(s != BASE_REVERSE) object.setID(this.deleted.remove(s));
			else {
				//on lui donne un nouvel id
				object.setID(this.count);
				this.count++;
			}
			//ajout à la liste
			this.list.add(object);
		} else {
			//donne dernier disponible
			object.setID(this.count);
			//augmente
			this.count++;
			//ajout à la liste
			this.list.add(object);
		}
	}

	/**
	 * Donne un id a une entité, l'id doit forcément être libre
	 * @param object un object
	 * @param id l'id a lui donner
	 * @since 6.0
	 */
	public void malloc(@NotNull IDInterface object, Integer id) {
		if(isIdInList(id)) throw new IllegalArgumentException("id "+id+" already taken");

		object.setID(id);
		this.list.add(object);//ajoute
		this.deleted.remove(id);//si contenu
		for (int i = 0; i < id; i++) {
			if(!isIdInList(i) && !this.deleted.contains(i))
				this.deleted.add(i);
		}
		if(id >= this.count) this.count = id+1;

		Logger.printDebugALL("IDFactory#malloc","Après l'ajout de "+id+", j'ai "+
				this.list+" et libre "+this.deleted);
	}

	/**
	 * Retourne si l'id est dans la liste
	 * @param id id
	 * @return true si dans la liste
	 * @since 6.0
	 */
	private boolean isIdInList(int id){
		for (IDInterface i : this.list) {
			if(i.getID() == id) return true;
		}
		return false;
	}

	/**
	 * Le prochain id qui sera attribué
	 * @return le prochain id qui sera attribué
	 * @since 6.0
	 */
	public int getNextID(){
		return count;
	}

	/**
	 * Ajoute un ID à un object.
	 * Si l'élément était déjà contenu dans liste, alors rien n'est fait.
	 *
	 * Les ids sont négatifs.
	 *
	 * @param object Objet sur lequel on veut ajouter un ID
	 *
	 * @since 6.0
	 */
	public void reverseID(IDInterface object){
		if(this.list.contains(object)) return;

		if(!this.deleted.isEmpty()){
			int s = BASE_NORMAL;
			for (Integer i : this.deleted) {
				if(i <= BASE_REVERSE)
					s = i;
			}
			//donne un id qui s'est libéré
			if(s != BASE_NORMAL) object.setID(this.deleted.remove(s));
			//on lui donne un nouvel id
			else {
				object.setID(this.countReverse);
				this.countReverse--;
			}
			//ajout à la liste
			this.list.add(object);
		} else {
			//donne dernier disponible
			object.setID(this.countReverse);
			//augmente
			this.countReverse--;
			//ajout à la liste
			this.list.add(object);
		}
	}

	/**
	 * Libère l'id d'un object
	 * @param object un object
	 * @since 6.0
	 */
	public void free(IDInterface object) {
		if(!list.contains(object)) return;

		this.deleted.add(object.getID());//ajoute aux ids libres
		this.list.remove(object);//suppression
		object.setID(ID_FACTORY_NO_ID);
	}

	/**
	 * Retourne un objet selon son ID
	 *
	 * @param id un id
	 * @return L'objet ou un null object
	 *
	 * @since 2.0
	 */
	public IDInterface getElementByID(int id) {
		for (IDInterface i : this.list) {
			if(i.getID() == id)
				return i;
		}
		return new NullIDInterfaceObject();
	}

	/**
	 * Un null object représentant IDInterface
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 *
	 * @version 6.0 02/02/2020
	 * @since 6.0 02/02/2020
	 */
	@ConvenienceClass
	public class NullIDInterfaceObject implements IDInterface {

		NullIDInterfaceObject(){}

		@Override
		public int getID() {
			return ID_FACTORY_NO_ID;
		}

		@Override
		public void setID(int id) {
		}
	}
}

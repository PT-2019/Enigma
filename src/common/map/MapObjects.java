package common.map;

import com.badlogic.gdx.math.Vector2;
import common.entities.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Stocke des {@link common.entities.GameObject GameObjects} en fonction de leur tile d'appartenance
 *
 * Patron de conception : Façade.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 5.0
 */
public class MapObjects {
	/**
	 * Liste
	 *
	 * @since 5.0
	 */
	private HashMap<Vector2, ArrayList<GameObject>> objects;

	/**
	 * Création du stocke, vide de base
	 *
	 * @since 5.0
	 */
	public MapObjects() {
		this.objects = new HashMap<>();
	}

	/**
	 * @param map Valeurs de départ
	 *
	 * @since 5.0
	 */
	public MapObjects(Map<Vector2, ArrayList<GameObject>> map) {
		this.objects = new HashMap<>(map);
	}

	/**
	 * Ajouter
	 *
	 * @param v  Vecteur
	 * @param go Liste d'éléments
	 *
	 * @since 5.0
	 */
	public void put(Vector2 v, ArrayList<GameObject> go) {
		this.objects.put(v, go);
	}

	/**
	 * Ajouter
	 *
	 * @param v  Vecteur
	 * @param go Elément
	 *
	 * @since 5.0
	 */
	public void put(Vector2 v, GameObject go) {
		if (!objects.containsKey(v)) {
			ArrayList<GameObject> a = new ArrayList<>();
			a.add(go);
			this.objects.put(v, a);
		} else
			this.objects.get(v).add(go);
	}

	/**
	 * Retire tous les éléments à tel vecteur
	 *
	 * @param v Vecteur
	 *
	 * @since 5.0
	 */
	public void remove(Vector2 v) {
		this.objects.remove(v);
	}

	/**
	 * Retire un éléments à tel vecteur
	 *
	 * @param v  Vecteur
	 * @param go Elément
	 *
	 * @since 5.0
	 */
	public void remove(Vector2 v, GameObject go) {
		this.objects.get(v).remove(go);
	}

	/**
	 * Contient un élément
	 *
	 * @param go Elément
	 * @return true si l'élément est contenu, false sinon
	 *
	 * @since 5.0
	 */
	public boolean contains(GameObject go) {
		return (this.getVectorByObject(go) != null);
	}

	/**
	 * Obtenir la taille
	 *
	 * @return La taille
	 *
	 * @since 5.0
	 */
	public int size() {
		return this.objects.size();
	}

	/**
	 * Obtenir la liste complète
	 *
	 * @return Liste complète
	 *
	 * @since 5.0
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Vector2, ArrayList<GameObject>> getAll() {
		return (HashMap<Vector2, ArrayList<GameObject>>) this.objects.clone();
	}

	/**
	 * Obtenir les éléments à tel vecteur
	 *
	 * @param v Vecteur
	 * @return Les éléments
	 *
	 * @since 5.0
	 */
	public ArrayList<GameObject> getObjectsByVector(Vector2 v) {
		return this.objects.get(v);
	}

	/**
	 * Obtenir un vecteur de tel élément
	 *
	 * @param go Elément
	 * @return Vecteur, null si l'éléments n'existe pas
	 *
	 * @since 5.0
	 */
	public Vector2 getVectorByObject(GameObject go) {
		for (Map.Entry<Vector2, ArrayList<GameObject>> map : this.objects.entrySet()) {
			if (map.getValue().contains(go))
				return map.getKey();
		}
		return null;
	}

	/**
	 * Obtenir un élément depuis son ID
	 *
	 * @param id ID
	 * @return L'élément, null si aucun élément a cet ID
	 *
	 * @since 5.0
	 */
	public GameObject getObjectByID(int id) {
		for (ArrayList<GameObject> list : this.objects.values()) {
			for (GameObject o : list) {
				if (o.getID() == id)
					return o;
			}
		}
		return null;
	}

	/**
	 * Obtenir tous les éléments d'une telle classe
	 *
	 * @param t   Classe
	 * @param <T> Type de la classe
	 * @return Les éléments
	 * @since 5.0
	 */
	@SuppressWarnings("unchecked")
	public <T extends GameObject> ArrayList<T> getAllObjectsByClass(Class<T> t) {
		ArrayList<GameObject> obj = new ArrayList<>();

		for (Map.Entry<Vector2, ArrayList<GameObject>> map : this.objects.entrySet()) {
			for (GameObject go : map.getValue()) {
				if (go.getClass().getName().equals(t.getName()))
					obj.add(go);
			}
		}

		return (ArrayList<T>) obj.clone();
	}

	/**
	 * Retourne une mapObject depuis une clef
	 * @param start une clef
	 * @return une mapObject depuis une clef
	 * @since 6.1
	 */
	public MapObjects getEntries(Vector2 start) {
		if(!this.objects.containsKey(start)) return null;
		MapObjects m = new MapObjects();
		m.put(start,this.objects.get(start));
		return m;
	}

	/**
	 * Retourne une mapObject depuis une clef
	 * @param start une clef
	 * @return une mapObject depuis une clef
	 * @since 6.1
	 */
	public MapObject getFirstEntry(Vector2 start) {
		if(!this.objects.containsKey(start)) return null;
		ArrayList<GameObject> gameObjects = this.objects.get(start);
		if(gameObjects.size() <= 0) return null;
		return new MapObject(start, gameObjects.get(0));
	}

	/**
	 * Retourne un mapObject depuis une valeur
	 * @param object une valeur
	 * @return un mapObject depuis une valeur
	 * @throws IllegalArgumentException si pas dans la map
	 * @since 6.1
	 */
	public MapObject getEntry(GameObject object) {
		int id = object.getID();
		for (Map.Entry<Vector2, ArrayList<GameObject>> entry:this.objects.entrySet()){
			for (GameObject o :entry.getValue()) {
				if (o.getID() == id)
					return new MapObject(entry.getKey(), o);
			}
		}
		return null;
	}
}

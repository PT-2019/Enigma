package general.map;

import com.badlogic.gdx.math.Vector2;
import general.entities.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Stocke des {@link general.entities.GameObject GameObjects} en fonction de leur tile d'appartenance
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
     */
    private HashMap<Vector2, ArrayList<GameObject>> objects;

    public MapObjects(){
        this.objects = new HashMap<>();
    }

    /**
     * @param map Valeurs de départ
     */
    public MapObjects(Map<Vector2, ArrayList<GameObject>> map){
        this.objects = new HashMap<>(map);
    }

    /**
     * Ajouter
     * @param v Vecteur
     * @param go Liste d'éléments
     */
    public void put(Vector2 v, ArrayList<GameObject> go){
        this.objects.put(v,go);
    }

    /**
     * Ajouter
     * @param v Vecteur
     * @param go Elément
     */
    public void put(Vector2 v, GameObject go){
        if(!objects.containsKey(v)) {
            ArrayList<GameObject> a = new ArrayList<>();
            a.add(go);
            this.objects.put(v,a);
        }else
            this.objects.get(v).add(go);
    }

    /**
     * Retire tous les éléments à tel vecteur
     * @param v Vecteur
     */
    public void remove(Vector2 v){
        this.objects.remove(v);
    }

    /**
     * Retire un éléments à tel vecteur
     * @param v Vecteur
     * @param go Elément
     */
    public void remove(Vector2 v, GameObject go){
        this.objects.get(v).remove(go);
    }

    /**
     * Contient un élément
     * @param go Elément
     * @return true si l'élément est contenu, false sinon
     */
    public boolean contains(GameObject go){
        return (this.getVectorByObject(go) != null);
    }

    /**
     * Obtenir la taille
     * @return La taille
     */
    public int size(){
        return this.objects.size();
    }

    /**
     * Obtenir la liste complète
     * @return Liste complète
     */
    @SuppressWarnings("unchecked")
    public HashMap<Vector2, ArrayList<GameObject>> getAll(){
        return (HashMap<Vector2, ArrayList<GameObject>>) this.objects.clone();
    }

    /**
     * Obtenir les éléments à tel vecteur
     * @param v Vecteur
     * @return Les éléments
     */
    public ArrayList<GameObject> getObjectsByVector(Vector2 v){
        return this.objects.get(v);
    }

    /**
     * Obtenir un vecteur de tel élément
     * @param go Elément
     * @return Vecteur, null si l'éléments n'existe pas
     */
    public Vector2 getVectorByObject(GameObject go){
        for(Map.Entry<Vector2, ArrayList<GameObject>> map : this.objects.entrySet()){
            if(map.getValue().contains(go))
                return map.getKey();
        }
        return null;
    }

    /**
     * Obtenir un élément depuis son ID
     * @param id ID
     * @return L'élément, null si aucun élément a cet ID
     */
    public GameObject getObjectByID(int id){
        for(ArrayList<GameObject> list : this.objects.values()){
            for(GameObject o : list){
                if(o.getID() == id)
                    return o;
            }
        }
        return null;
    }

    /**
     * Obtenir tous les éléments d'une telle classe
     * @param t Classe
     * @param <T> Type de la classe
     * @return Les éléments
     */
    @SuppressWarnings("unchecked")
    public <T extends GameObject> ArrayList<T> getAllObjectsByClass(Class<T> t){
        ArrayList<GameObject> obj = new ArrayList<>();

        for(Map.Entry<Vector2, ArrayList<GameObject>> map : this.objects.entrySet()){
            for(GameObject go : map.getValue()) {
                if (go.getClass().getName().equals(t.getName()))
                    obj.add(go);
            }
        }

        return (ArrayList<T>) obj.clone();
    }
}

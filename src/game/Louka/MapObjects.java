package game.Louka;

import api.entity.GameObject;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapObjects {
    private HashMap<Vector2, ArrayList<GameObject>> objects;

    public MapObjects(){
        this.objects = new HashMap<>();
    }

    public MapObjects(Map<Vector2, ArrayList<GameObject>> map){
        this.objects = new HashMap<>(map);
    }

    public void put(Vector2 v, ArrayList<GameObject> go){
        this.objects.put(v,go);
    }

    public void put(Vector2 v, GameObject go){
        if(!objects.containsKey(v)) {
            ArrayList<GameObject> a = new ArrayList<>();
            a.add(go);
            this.objects.put(v,a);
        }else
            this.objects.get(v).add(go);
    }

    public void remove(Vector2 v){
        this.objects.remove(v);
    }

    public void remove(Vector2 v, GameObject go){
        this.objects.get(v).remove(go);
    }

    public boolean contains(GameObject go){
        return (this.getVectorByObject(go) != null);
    }

    public int size(){
        return this.objects.size();
    }

    @SuppressWarnings("unchecked")
    public HashMap<Vector2, ArrayList<GameObject>> getAll(){
        return (HashMap<Vector2, ArrayList<GameObject>>) this.objects.clone();
    }

    public ArrayList<GameObject> getObjectsByVector(Vector2 v){
        return this.objects.get(v);
    }

    public Vector2 getVectorByObject(GameObject go){
        for(Map.Entry<Vector2, ArrayList<GameObject>> map : this.objects.entrySet()){
            if(map.getValue().contains(go))
                return map.getKey();
        }
        return null;
    }

    public GameObject getObjectByID(int id){
        for(ArrayList<GameObject> list : this.objects.values()){
            for(GameObject o : list){
                if(o.getID() == id)
                    return o;
            }
        }
        return null;
    }

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

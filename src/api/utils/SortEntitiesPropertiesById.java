package api.utils;

import api.enums.TmxProperties;
import com.badlogic.gdx.maps.MapProperties;

import java.util.Comparator;


public class SortEntitiesPropertiesById implements Comparator<MapProperties> {

    @Override
    public int compare(MapProperties t1, MapProperties t2) {
        /*int id1 = Integer.parseInt(t1.get(TmxProperties.TMX_ID, String.class));
        int id2 = Integer.parseInt(t2.get(TmxProperties.TMX_ID, String.class));*/
        Integer id1 = t1.get(TmxProperties.TMX_ID, Integer.class);
        System.out.println(id1);
        Integer id2 = t2.get(TmxProperties.TMX_ID, Integer.class);
        if(id1 == null) id1 = 0;
        if(id2 == null) id2 = 0;
        return Integer.compare(id1, id2);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}

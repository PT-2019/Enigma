package common.data;

import java.util.ArrayList;
import java.util.HashMap;

public class UserData {

    private String name;

    private final static String NAME = "name";

    public UserData(String name){
        this.name = name;
    }

    public UserData(HashMap<String,String> data){

        ArrayList<String> dataName = new ArrayList<>();
        dataName.add(NAME);

        for(String name : dataName){
            String get = data.get(name);
            if(get != null){
                switch(name){
                    case NAME:
                        this.name = get;
                        break;
                }
            } else
                throw new IllegalArgumentException("Attribut " + name + " manquant");
        }
    }

    public HashMap<String,String> getData(){
        HashMap<String,String> data = new HashMap<>();
        data.put(NAME, this.name);

        return data;
    }

    public String getName(){
        return this.name;
    }
}

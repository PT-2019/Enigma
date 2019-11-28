package editor.map;

import editor.enums.Layer;
import editor.texture.Texture;
import editor.texture.TextureArea;
import editor.texture.TextureProxy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadHandler extends DefaultHandler {

    private String currentName;

    private TextureProxy proxyTexture;

    private Map loadMap;

    private int[] dataTexture = new int[4];

    private String currentLayer;

    private int indice;

    public LoadHandler(){
        proxyTexture = new TextureProxy();
        indice = 0;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentName = qName;

        if (qName.equals("map")){
            int col = Integer.parseInt(attributes.getValue("width"));
            int row = Integer.parseInt(attributes.getValue("height"));

            loadMap = new Map(col,row);
        } else if (qName.equals("tileset")){
            dataTexture[0] = Integer.parseInt(attributes.getValue("tileheight"));
            dataTexture[1] = Integer.parseInt(attributes.getValue("columns"));
            dataTexture[2] = Integer.parseInt(attributes.getValue("firstgid"));
            dataTexture[3] = dataTexture[2] + Integer.parseInt(attributes.getValue("tilecount"));

        }else if(qName.equals("image")){
            TextureArea currentTexture = new TextureArea(dataTexture[0],attributes.getValue("source"),
                    dataTexture[1],dataTexture[2],dataTexture[3]);

            proxyTexture.addTexture(currentTexture);
        } else if(qName.equals("layer")){
            indice = 0;
            currentLayer = attributes.getValue("name");

        } else if(qName.equals("data")){

        } else if(qName.equals("room")){

        }
    }

    @Override
    public void characters(char[] value, int start, int length) throws SAXException {

        String str = new String(value, start, length);

        str = str.replaceAll("\\D","");

        if (currentName.equals("data")){
            System.out.println(str);

            for (int i = 0; i < str.length(); i++) {
                char tmp = str.charAt(i);

                //on rentre dans le if que si il y a un caractère qui nous intéresse
                Case tmpCase = loadMap.getCase(indice);

                int num = Integer.parseInt(String.valueOf(tmp));

                if (tmpCase == null){
                    tmpCase = new Case();
                    loadMap.setCase(indice,tmpCase);
                }
                    if (currentLayer.equals("Colision")){
                        if (num == 1){
                            tmpCase.setWalkable(true);
                        }
                    }else{
                        tmpCase.setTexture(Layer.valueOf(currentLayer),new Texture(num,proxyTexture.getImage(num)));
                    }
                indice++;
            }
        }
    }

    public  Map getMap(){
        return loadMap;
    }

    public TextureProxy getProxyTexture() {
        return proxyTexture;
    }
}

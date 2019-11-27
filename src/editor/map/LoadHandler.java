package editor.map;

import editor.texture.TextureArea;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadHandler extends DefaultHandler {

    private String currentName;

    private TextureArea current;

    private Map loadMap;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentName = qName;

        if (qName.equals("map")){

        } else if (qName.equals("tileset")){

        }else if(qName.equals("image")){

        } else if(qName.equals("layer")){

        } else if(qName.equals("data")){

        } else if(qName.equals("room")){

        }
    }
}

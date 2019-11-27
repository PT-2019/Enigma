package editor.utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
public class JsonReader {

	public static void main(String[] args) {//test
		System.out.println(JsonReader.importJson("assets/uiskin.atlas"));
	}

	private static String readFile(String path){
		// on va mettre tout notre fichier dans string
		StringBuilder string = new StringBuilder();
		// on lit tout notre fichier mais il faut refaire les sauts de ligne
		BufferedReader bufferReader;

		try{
			// lis tout le fichier
			bufferReader = new BufferedReader(new FileReader(new File(path)));
			String line; // stocke la ligne courante

			// lecture ligne par ligne
			while((line = bufferReader.readLine()) != null){
				//ajoute la ligne
				string.append(line);
				// ajoute saut de ligne (pas conservé de base)
				string.append("\n");
			}
			bufferReader.close(); //fermeture du fichier
		}catch(IOException e){
			e.printStackTrace();
		}

		return string.toString();
	}

	public static JsonFile importJson(final String path){
		final int lastSlash = path.lastIndexOf(File.separator);
		String[] jsonFile = JsonReader.readFile(path).split(System.lineSeparator());//get file as an array of string
		String imagePath;
		if(lastSlash > 0)
			imagePath = path.substring(0, lastSlash) + "/" + jsonFile[1];//path of image relative to .atlas file
		else imagePath = jsonFile[1];

		final StringBuilder sb = new StringBuilder();//string builder for errors
		HashMap<String, Point> map = new HashMap<>();//map sub-texture name => line in the file

		if(!jsonFile[0].isEmpty())//first line should be empty
			sb.append("should start with an empty line.");

		Point p = new Point();
		p.x = -1;
		boolean inside = false;

		for (int i = 6; i < jsonFile.length ; i++) {
			if(!jsonFile[i].startsWith("  ")){
				if(inside){
					p.y = i-1;
					map.put(jsonFile[p.x-1],new Point(p));
					p.x = -1;
					inside = false;
				}
				if(p.x == -1) p.x = i+1;
			} else {
				inside = true;
			}
		}

		if(!sb.toString().isEmpty())
			System.out.println(sb.toString());

		return new JsonFile(imagePath, map, jsonFile);

	}
}

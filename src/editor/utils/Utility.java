package editor.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;


/**
 * Cette classe lit un fichier {@link #readFile(String)} et le renvoi sous la forme d'une String.
 *
 * @version 2.0 27 novembre 2019
 */
public class Utility {

	/**
	 * Cette méthode lit un fichier et le renvoi sous la forme d'une String.
	 * Les saut de lignes sont conservés.
	 *
	 * @param path le chemin du fichier
	 *
	 * @return le fichier sous la forme d'une String
	 *
	 * @throws IllegalStateException si la lecture échoue
	 *
	 * @since 2.0
	 */
	public static String readFile(String path){
		StringBuilder string = new StringBuilder();//pour notre fichier
		BufferedReader bufferReader;

		try{
			//ouverture du flux
			bufferReader = new BufferedReader(new FileReader(new File(path)));
			String line; // stocke la ligne courante

			while((line = bufferReader.readLine()) != null){
				string.append(line);//ajoute la ligne
				string.append("\n");//et son saut de ligne //System.lineSeparator()
			}
			bufferReader.close(); //fermeture du flux
		}catch(IOException e){//+file not found exception
			throw new IllegalStateException("lecture de fichier raté");
		}

		return string.toString();
	}

}

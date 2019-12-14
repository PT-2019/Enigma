package editor.utils;

import java.util.Scanner;


public class CalculHeader{

	/**
	 * Cette méthode affiche le nb de colonne et le max d'un texture à ajouter
	 * dans un fichier de texture (cf demander à Quentin ou Loïc)
	 */
	public static void dispInfo(int width,int height,int tile,int max_last){
		int nbcol = width/tile;
		int nbligne;

		if (width%tile != 0){
			System.out.println("Le tile ou la Longeur de l'image est mauvaise");
			return;
		}

		System.out.println("nombre de colonne :"+nbcol);

		nbligne = height/tile;

		if (height%tile != 0){
			System.out.println("Le tile ou la hauteur de l'image est mauvais");
			return;
		}

		System.out.println("max de la texture :"+(nbcol*nbligne+max_last));
	}


	//main for the test
	public static void main(String[] args) {
		CalculHeader t = new CalculHeader();

		Scanner sc= new Scanner(System.in);

		System.out.println("Hauteur de l'image :");
		int height= sc.nextInt();

		System.out.println("Longeur de l'image :");
		int width = sc.nextInt();

		System.out.println("Tile de l'image :");
		int tile = sc.nextInt();

		System.out.println("Maximum de la dernière texture :");
		int max = sc.nextInt();

		dispInfo(width,height,tile,max);
	}
}



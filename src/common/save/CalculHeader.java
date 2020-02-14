package common.save;

import java.util.Scanner;

/**
 * Calcule le header d'une map .tmx
 *
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0
 * @since 1.0
 */
public class CalculHeader {

	/**
	 * Cette méthode affiche le nb de colonne et le max d'un texture à ajouter
	 * dans un fichier de texture (cf demander à Quentin ou Loïc)
	 *
	 * @param width    none
	 * @param height   none
	 * @param tile     none
	 * @param max_last none
	 */
	private static void displayInfo(int width, int height, int tile, int max_last) {
		int nbCol = width / tile;
		int nbLigne;

		if (width % tile != 0) {
			System.out.println("Le tile ou la Longueur de l'image est mauvaise");
			return;
		}

		System.out.println("nombre de colonne :" + nbCol);

		nbLigne = height / tile;

		if (height % tile != 0) {
			System.out.println("Le tile ou la hauteur de l'image est mauvais");
			return;
		}

		System.out.println("max de la texture :" + (nbCol * nbLigne + max_last));
	}


	//main for the test
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Hauteur de l'image :");
		int height = sc.nextInt();

		System.out.println("Longueur de l'image :");
		int width = sc.nextInt();

		System.out.println("Tile de l'image :");
		int tile = sc.nextInt();

		System.out.println("Maximum de la dernière texture :");
		int max = sc.nextInt();

		displayInfo(width, height, tile, max);
	}
}



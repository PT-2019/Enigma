package common.utils;

import common.entities.consumable.Key;

/**
 * Un main de test de IDFactory
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class MainTestIDFactory {

	public static void main(String[] args) {
		IDFactory f = new IDFactory();

		Key k1 = new Key();
		Key k2 = new Key();
		Key k3 = new Key();
		Key k4 = new Key();
		Key k5 = new Key();
		Key k6 = new Key();
		Key k7 = new Key();

		f.newID(k1);
		f.newID(k2);
		f.newID(k3);
		f.newID(k4);
		f.newID(k5);

		System.out.println("Préparation d'élements");

		System.out.println(k1.getID()); //0
		System.out.println(k2.getID()); //1
		System.out.println(k3.getID()); //2
		System.out.println(k4.getID()); //3
		System.out.println(k5.getID()); //4

		System.out.println("Ré-attribution");

		f.newID(k1);

		System.out.println(k1.getID()); //0

		System.out.println("Suppression");

		f.free(k3);

		System.out.println(k1.getID()); //0
		System.out.println(k2.getID()); //1
		System.out.println(k3.getID()); //Integer.MIN_VALUE
		System.out.println(k4.getID()); //3
		System.out.println(k5.getID()); //4

		System.out.println("Ajout après suppression");

		f.newID(k6);
		f.newID(k7);

		System.out.println(k1.getID()); //0
		System.out.println(k2.getID()); //1
		System.out.println(k3.getID()); //Integer.MIN_VALUE
		System.out.println(k4.getID()); //3
		System.out.println(k5.getID()); //4
		System.out.println(k6.getID()); //2
		System.out.println(k7.getID()); //5
	}

}

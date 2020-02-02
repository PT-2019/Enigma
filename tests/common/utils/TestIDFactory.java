package common.utils;

import common.entities.consumable.Key;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Classe de test de IDFactory
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 02/02/2020
 * @since 6.0 02/02/2020
 */
public class TestIDFactory {

	/**
	 * Un test ultra basique qui crée des éléments et vérifie leurs ids
	 */
	@Test
	public void testNormalActivity(){
		final int NB = 5;
		final IDFactory factory = new IDFactory();

		for (int i = 0; i < NB; i++) {
			Key k = new Key();
			factory.newID(k);

			assertEquals(k.getID(), i);
		}
	}

	/**
	 * Un test ultra basique qui crée des éléments et vérifie leurs ids
	 * mais qui en plus vérifie le comportement si on ajoute un object qui
	 * existe déjà.
	 */
	@Test
	public void testAddAlreadyAdded(){
		final int NB = 5;
		final IDFactory factory = new IDFactory();

		Key k = null;
		int i;
		for (i = 0; i < NB; i++) {
			k = new Key();
			factory.newID(k);
			assertEquals(i, k.getID());
		}
		factory.newID(k);
		assertEquals(i-1, k.getID()); //id inchangé

	}

	/**
	 * Un test qui ajoute des entités, en supprime et en ajoute.
	 * Les entités supprimées doivent avoir un id = {@link IDFactory#ID_FACTORY_NO_ID}.
	 *
	 * On regarde aussi que les nouvelles entités prennent les ids des entités supprimés.
	 */
	@Test
	public void testAddDeleteAdd(){
		//Les test (ici) sont faits tel que NB_ADD_AFTER_DELETE < DELETED
		// NB_BASE > DELETED
		//on peut pas retirer plus que l'on a
		//et on regarde si les ajoutés après la suppression ont pris les ids de deleted
		final int NB_BASE = 5, NB_ADD_AFTER_DELETE = 2, DELETED = 3;
		final ArrayList<Key> keys = new ArrayList<>();
		final ArrayList<Integer> deleted = new ArrayList<>();
		final ArrayList<Integer> freeIds = new ArrayList<>();
		final IDFactory factory = new IDFactory();
		final Random random = new Random();

		//rempli le tableau
		for (int i = 0; i < NB_BASE; i++) {
			keys.add(i, new Key());

			factory.newID(keys.get(i));

			assertEquals(i, keys.get(i).getID());
		}

		//supprime des éléments
		for (int i = 0; i < DELETED; i++) {
			int index = -1;
			//éviter du supprimer un id déjà supprimé
			while(index == -1){
				index = random.nextInt(NB_BASE);
				for (Integer integer : deleted) {
					if(integer == null) continue;
					if(integer == index) {
						index = -1;
						break;
					}
				}
			}
			//sauvegarde les ids supprimés
			freeIds.add(i, keys.get(index).getID());
			//libère un id aléatoire parmi les disponibles
			factory.free(keys.get(index));
			//sauvegarde la position du supprimé
			deleted.add(i, index);
			//vérifie leur id
			assertEquals(keys.get(index).getID(), IDFactory.ID_FACTORY_NO_ID);
		}

		//ajout d'élements
		for (int i = NB_BASE-1; i < NB_ADD_AFTER_DELETE + NB_BASE; i++) {
			keys.add(i, new Key());
			factory.newID(keys.get(i));
			//peu pas être plus grand
			//Car on ajoute moins qu'on a retiré
			assertTrue(keys.get(i).getID() < NB_BASE);
			//existe dans la liste des ids libres
			assertTrue(freeIds.contains(keys.get(i).getID()));
		}

		for (int i = 0; i < keys.size() ; i++) {
			if(deleted.contains(i)){
				assertEquals(IDFactory.ID_FACTORY_NO_ID, keys.get(i).getID());
			}
		}

	}
}
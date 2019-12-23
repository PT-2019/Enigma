package editor.utils.dnd;

import api.enums.EntitiesCategories;
import com.badlogic.gdx.Gdx;
import editor.screens.EditorScreen;
import game.ui.CategoriesMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Observateur des boutons de sélection de catégorie
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 3.0 14 décembre 2019
 * @see EditorScreen
 * @since 1.0 14 novembre 2019
 * @deprecated since 4.0 car on doit utiliser libgdx
 */
@Deprecated
public class ChoixObjet implements ActionListener {

	/**
	 * Cet méthode permet de gerer l'affichage de la partie gauche du menu via
	 * les boutons grace au card layout
	 *
	 * @param actionEvent clic sur une des catégories
	 * @see EditorScreen
	 * @since 1.0 14 novembre 2019
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String msg = actionEvent.getActionCommand();

		/*

			PATCH FAIT!

			ceci est un fix temporaire qui charge une variable public static pour changer de catégorie
				normalement, les observés et l'observateur sont en libgdx.
				Ce listener doit être marqué deprecated et patché
				Il faut retirer les observateurs qui sont ajoutés dans MenuScreen
		 */
		for (EntitiesCategories category : EntitiesCategories.values()) {
			if (msg.equals(category.name)) {
				//Gdx.app.postRunnable(() -> CategoriesMenu.c.loadCategory(category));
				return;
			}
		}
	}
}

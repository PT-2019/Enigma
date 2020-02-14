package api.net.utils;

import api.utils.annotations.ConvenienceClass;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Une classe qui contient des méthodes utiles liées au réseau
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 12/02/2020
 * @since 6.0 12/02/2020
 */
@ConvenienceClass
public final class NetUtility {

	/**
	 * Ouvre un lien dans un navigateur
	 *
	 * @param link un lien
	 * @throws IllegalStateException    si l'opération n'est pas supporté
	 * @throws IllegalArgumentException si le lien est incorrect/invalide/non trouvé
	 * @see Desktop
	 */
	public static void openLinkInBrowser(String link) {
		//test si disponible
		if (!Desktop.isDesktopSupported())
			throw new IllegalStateException("Open link is not supported.");

		Desktop desktop = Desktop.getDesktop();

		//open link
		try {
			desktop.browse(new URL(link).toURI());
		} catch (IOException | URISyntaxException e) {
			throw new IllegalArgumentException("Open link failed");
		}
	}

}

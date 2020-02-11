package editor.bar.doc;

import api.utils.Utility;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Serialization de la documentation
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 11/02/2020
 * @since 6.0 11/02/2020
 */
@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection","unused"})
public final class DocumentationFile {

	/**
	 * Charge la documentation
	 * @param path chemin
	 * @return retourne le fichier lu. Un object par catégorie
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<DocumentationFile> loadDocumentation(String path){
		return new Json().fromJson(ArrayList.class, DocumentationFile.class, Utility.readFile(path));
	}

	private String tooltip = "";
	private String title = "";
	private ArrayList<DocItems> docItems = new ArrayList<>();

	DocumentationFile() {
	}

	/**
	 * Retourne le contenu de la catégorie
	 * @return Retourne le contenu de la catégorie
	 */
	public ArrayList<DocItems> getDocItems() {
		return this.docItems;
	}

	/**
	 * Retourne le titre de la catégorie
	 * @return le titre de la catégorie
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Retourne un tooltip de la catégorie
	 * @return un tooltip de la catégorie
	 */
	public String getToolTip() {
		return this.tooltip;
	}

	@Override
	public String toString() {
		return "DocumentationFile{" + "tooltip='" + tooltip + '\'' + ", title='" + title + '\'' + ", docItems=" + docItems + '}';
	}

	/**
	 * Serialization d'un élément de la documentation
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 *
	 * @version 6.0 11/02/2020
	 * @since 6.0 11/02/2020
	 */
	public static final class DocItems {

		private String type;
		private String[] content;

		DocItems(){}

		/**
		 * Retourne le type de l'élément sous la forme d'un string
		 * @return le type de l'élément sous la forme d'un string
		 * @see DocumentationTypes
		 */
		public String getType() {
			return this.type;
		}

		/**
		 * Retourne le contenu de l'élément sous la forme d'un tableau de string
		 * @return le contenu de l'élément sous la forme d'un tableau de string
		 */
		public String[] getContent() {
			return this.content;
		}

		@Override
		public String toString() {
			return "DocItems{" + "type='" + type + '\'' + ", content=" + Arrays.toString(content) + '}';			}
	}
}

package editor.enigma;

import api.entity.utils.IDInterface;
import api.enums.Attributes;
import editor.enigma.condition.Condition;
import editor.enigma.operation.Operation;
import editor.entity.Player;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Enigma gère une énigme. Une énigme est définie par les {@link editor.enigma.condition.Condition conditions} à satisfaire pour résoudre l'énigme ainsi que les {@link editor.enigma.operation.Operation opérations} réalisées si toutes les conditions sont satisfaites.
 * Une énigme contient aussi des {@link editor.enigma.Advice indices} déstinés aux joueurs.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see editor.enigma.condition.Condition
 * @see editor.enigma.operation.Operation
 * @see editor.enigma.Advice
 * @since 2.0
 */
public class Enigma implements ActionListener, IDInterface {

	/**
	 * Une minute en millisecondes
	 */
	private final static int ONE_MINUTES_IN_MILLISECOND = 60000;
	/**
	 * Valeur de départ de l'index pointant l'indice actuel
	 */
	private final static int ADVICE_INDEX_STARTING_VALUE = -1;
	/**
	 * Titre
	 */
	private String title;
	/**
	 * Description
	 */
	private String description;
	/**
	 * Conditions
	 */
	private ArrayList<Condition> conditions;
	/**
	 * Opérations
	 */
	private ArrayList<Operation> operations;
	/**
	 * Indices. Ils sont rangés par ordre d'importance
	 */
	private ArrayList<Advice> advices;
	/**
	 * Index pointant l'indice actuel
	 */
	private int currentAdvice;
	/**
	 * Est-ce que l'énigme à été trouvée
	 */
	private boolean known;
	/**
	 * Chronomètre le temps qui sépare deux énigmes
	 */
	private Timer timer;
	/**
	 * Id associé à une entité pour la sauvegarde
	 */
	private int id;

	public Enigma() {
		this.currentAdvice = ADVICE_INDEX_STARTING_VALUE;
		this.title = "";
		this.description = "";
		this.known = false;
		this.timer = new Timer(0, this);
		this.conditions = new ArrayList<Condition>();
		this.operations = new ArrayList<Operation>();
		this.advices = new ArrayList<Advice>();
		this.id = -1;
	}

	/**
	 * @param title       Titre de l'énigme
	 * @param description Description de l'énigme
	 */
	public Enigma(String title, String description) {
		this.currentAdvice = ADVICE_INDEX_STARTING_VALUE;
		this.title = title;
		this.description = description;
		this.known = false;
		this.timer = new Timer(0, this);
		this.conditions = new ArrayList<Condition>();
		this.operations = new ArrayList<Operation>();
		this.advices = new ArrayList<Advice>();
		this.id = -1;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	@SuppressWarnings("unchecked")
	public Enigma(Map<String, Object> attributes) {
		if (attributes.containsKey(Attributes.TITLE)) this.title = (String) attributes.get(Attributes.TITLE);
		else throw new IllegalArgumentException("Attribut \"title\" abscent");
		if (attributes.containsKey(Attributes.DESCRIPTION))
			this.description = (String) attributes.get(Attributes.DESCRIPTION);
		else throw new IllegalArgumentException("Attribut \"description\" abscent");
		if (attributes.containsKey(Attributes.KNOWN))
			this.known = Boolean.parseBoolean((String) attributes.get(Attributes.KNOWN));
		else throw new IllegalArgumentException("Attribut \"known\" abscent");
		if (attributes.containsKey(Attributes.CURRENT_ADVICE_INDEX))
			this.currentAdvice = Integer.parseInt((String) attributes.get(Attributes.CURRENT_ADVICE_INDEX));
		else throw new IllegalArgumentException("Attribut \"currentAdviceIndex\" abscent");
		if (attributes.containsKey(Attributes.ADVICES))
			this.advices = (ArrayList<Advice>) attributes.get(Attributes.ADVICES);
		else throw new IllegalArgumentException("Attribut \"advices\" abscent");
		if (attributes.containsKey(Attributes.CONDITIONS))
			this.conditions = (ArrayList<Condition>) attributes.get(Attributes.CONDITIONS);
		else throw new IllegalArgumentException("Attribut \"conditions\" abscent");
		if (attributes.containsKey(Attributes.OPERATIONS))
			this.operations = (ArrayList<Operation>) attributes.get(Attributes.OPERATIONS);
		else throw new IllegalArgumentException("Attribut \"operations\" abscent");
		if (attributes.containsKey(Attributes.ID)) {
			this.id = Integer.parseInt((String) attributes.get(Attributes.ID));
		}

		this.timer = new Timer(0, this);
		if (this.known && this.currentAdvice + 1 < this.advices.size()) {
			this.timer.setInitialDelay(this.advices.get(this.currentAdvice + 1).getDelay() * ONE_MINUTES_IN_MILLISECOND);
			this.timer.setRepeats(false);
			this.timer.start();
		}
	}

	/**
	 * Vérifie que toutes les conditions sont satisfaites
	 *
	 * @param p Joueur ayant intéragit avec l'entité ayant appelé cette méthode
	 */
	public void verifyConditions(Player p) {
		for (Condition condition : this.conditions) {
			//On teste que les conditions sont remplies, si ce n'est pas le cas, la méthode s'arrête là
			if (!condition.verify(p)) {
				System.out.println("Toutes les conditions n'ont pas été validées");
				return;
			}
		}

		//On lance toutes les opérations de l'enigme
		for (Operation operation : this.operations) {
			operation.doOperation(p);
		}
	}

	/**
	 * Ajoute une condition
	 *
	 * @param c Condition à ajouter
	 * @throws IllegalArgumentException Si la condition existe déjà dans l'énigme
	 */
	public void addCondition(Condition c) {
		if (this.conditions.contains(c)) throw new IllegalArgumentException("Cet élément existe déjà dans la liste");
		this.conditions.add(c);
	}

	/**
	 * Retire une condition
	 *
	 * @param c Condition à retirer
	 */
	public void removeCondition(Condition c) {
		this.conditions.remove(c);
	}

	/**
	 * Obtenir la liste des conditions
	 *
	 * @return Iterator de toutes les conditions de l'énigme
	 */
	@SuppressWarnings("unchecked")
	public Iterator<Condition> getAllConditions() {
		ArrayList<Condition> c = (ArrayList<Condition>) this.conditions.clone();
		return c.iterator();
	}

	/**
	 * Ajoute une opération
	 *
	 * @param o Opération à ajouter
	 * @throws IllegalStateException Si l'opération existe déjà dans l'énigme
	 */
	public void addOperation(Operation o) {
		if (this.operations.contains(o)) throw new IllegalStateException("Cet élément existe déjà dans la liste");
		this.operations.add(o);
	}

	/**
	 * Retire une opération
	 *
	 * @param o Opération à retirer
	 */
	public void removeOperation(Operation o) {
		this.operations.remove(o);
	}

	/**
	 * Obtenir la liste des opérations
	 *
	 * @return Iterator de toutes les opérations de l'énigme
	 */
	@SuppressWarnings("unchecked")
	public Iterator<Operation> getAllOperations() {
		ArrayList<Operation> o = (ArrayList<Operation>) this.operations.clone();
		return o.iterator();
	}

	/**
	 * Obtenir le titre de l'énigme
	 *
	 * @return Titre de l'énigme, chaine vide sinon
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Indique le titre
	 *
	 * @param title Titre
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Obtenir la description de l'énigme
	 *
	 * @return Description de l'énigme, chaine vide sinon
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Indique la description
	 *
	 * @param description Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Ajoute un indice
	 *
	 * @param a Indice à ajouter
	 * @throws IllegalStateException Si l'indice existe déjà dans l'énigme
	 */
	public void addAdvice(Advice a) {
		if (this.advices.contains(a)) throw new IllegalStateException("Cet élément existe déjà dans la liste");
		this.advices.add(a);
	}

	/**
	 * Retire un indice
	 *
	 * @param a Indice à retirer
	 */
	public void removeAdvice(Advice a) {
		this.advices.remove(a);
	}

	/**
	 * Echange de position deux indices
	 *
	 * @param advice1 Premier indice
	 * @param advice2 Second indice
	 * @throws IllegalArgumentException Si le premier indice n'existe pas dans l'énigme
	 * @throws IllegalArgumentException Si le second indice n'existe pas dans l'énigme
	 */
	public void switchAdvices(Advice advice1, Advice advice2) {
		if (!this.advices.contains(advice1))
			throw new IllegalArgumentException("Le premier indice transmis n'existe pas dans l'énigme");
		if (!this.advices.contains(advice2))
			throw new IllegalArgumentException("Le second indice transmis n'existe pas dans l'énigme");
		int index1 = this.advices.indexOf(advice1);
		int index2 = this.advices.indexOf(advice2);
		this.advices.set(index1, advice2);
		this.advices.set(index2, advice1);
	}

	/**
	 * Obtenir le texte de l'indice actuel
	 *
	 * @return Texte de l'indice actuel
	 */
	public String getTextAdvice() {
		if (this.currentAdvice != ADVICE_INDEX_STARTING_VALUE && this.currentAdvice < this.advices.size())
			return this.advices.get(this.currentAdvice).getAdvice();
		else return "Aucune aide pour l'instant";
	}

	/**
	 * Obtenir l'indice actuel
	 *
	 * @return Indice actuel, null sinon
	 */
	public Advice getAdvice() {
		if (this.currentAdvice != ADVICE_INDEX_STARTING_VALUE && this.currentAdvice < this.advices.size())
			return this.advices.get(this.currentAdvice);
		else return null;
	}

	/**
	 * Obtenir la liste des indices
	 *
	 * @return Iterator de tous les indices de l'énigme
	 */
	@SuppressWarnings("unchecked")
	public Iterator<Advice> getAllAdvices() {
		ArrayList<Advice> a = (ArrayList<Advice>) this.advices.clone();
		return a.iterator();
	}

	/**
	 * Savoir si l'énigme à déjà été découverte
	 *
	 * @return true si l'énigme à été découverte, false sinon
	 */
	public boolean isKnown() {
		return this.known;
	}

	/**
	 * Indique que l'énigme à été découverte
	 */
	public void discovered() {
		if (!this.known) {
			this.known = true;
			System.out.println("Nouvelle énigme découverte!");
			System.out.println(this.getTitle() + " : " + this.getDescription());
			if (this.currentAdvice + 1 < this.advices.size()) {
				this.timer.setInitialDelay(this.advices.get(this.currentAdvice + 1).getDelay() * ONE_MINUTES_IN_MILLISECOND);
				this.timer.setRepeats(false);
				this.timer.start();
			}
		}
	}

	/**
	 * Passe à l'indice suivant
	 *
	 * @param actionEvent Evénement
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (this.currentAdvice + 1 < this.advices.size()) {
			this.currentAdvice++;
			if (this.currentAdvice + 1 < this.advices.size()) {
				this.timer.setInitialDelay(this.advices.get(this.currentAdvice + 1).getDelay() * ONE_MINUTES_IN_MILLISECOND);
				this.timer.start();
			}
		}
	}

	/**
	 * Obtenir un EnumMap de l'objet avec ses attributs et leur état
	 *
	 * @return EnumMap de l'objet
	 * @see api.enums.Attributes
	 */
	public HashMap<String, Object> objectToMap() {
		HashMap<String, Object> object = new HashMap<>();
		object.put(Attributes.PATH, this.getClass().getName());
		object.put(Attributes.TITLE, this.title);
		object.put(Attributes.DESCRIPTION, this.description);
		object.put(Attributes.KNOWN, this.known + "");
		object.put(Attributes.CURRENT_ADVICE_INDEX, this.currentAdvice + "");
		object.put(Attributes.ID, id);

		ArrayList<HashMap<String, Object>> advices = new ArrayList<>();
		for (Advice a : this.advices) {
			advices.add(a.objectToMap());
		}
		object.put("advices", advices);

		ArrayList<HashMap<String, Object>> conditions = new ArrayList<>();
		for (Condition c : this.conditions) {
			conditions.add(c.objectToMap());
		}
		object.put("conditions", conditions);

		ArrayList<HashMap<String, Object>> operations = new ArrayList<>();
		for (Operation o : this.operations) {
			operations.add(o.objectToMap());
		}
		object.put("operations", operations);
		return object;
	}

	/**
	 * Compare deux énigmes
	 *
	 * @param o Enigme
	 * @return true si les deux énigles sont les même, false sinon
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Enigma)) return false;

		Enigma e = (Enigma) o;
		return (this.title.equals(e.getTitle()) && this.description.equals(e.getDescription()) && e.getAllConditions().equals(this.getAllConditions()) && e.getAllOperations().equals(this.getAllOperations()) && e.getAllAdvices().equals(this.getAllAdvices()));
	}

	/**
	 * Retourne l'id ou -1 si aucun, ne peut être définit qu'à la sauvegarde et pour la sauvegarde
	 *
	 * @return l'id ou -1 si aucun, ne peut être définit qu'à la sauvegarde et pour la sauvegarde
	 */
	public int getID() {
		return id;
	}

	/**
	 * Définit l'id ou -1 si aucun, ne peut être définit qu'à la sauvegarde et pour la sauvegarde
	 *
	 * @param id l'id ou -1 si aucun, ne peut être définit qu'à la sauvegarde et pour la sauvegarde
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Version texte de l'énigme
	 *
	 * @return Texte représentant l'énigme
	 */
	@Override
	public String toString() {
		return "[Enigma  : title = \"" + this.title + "\", descrption = \"" + this.description + "\", isKnown = " + this.isKnown() + ", currentAdviceIndex = " + this.currentAdvice + ", currentAdvice = " + this.getAdvice() + ", currentTextAdvice = \"" + this.getTextAdvice() + "\"]";
	}

	/**
	 * Version texte longue de l'énigme
	 *
	 * @return Texte représentant l'énigme
	 */
	public String toLongString() {
		StringBuilder s = new StringBuilder("[Enigma  : title = \"" + this.title + "\", descrption = \"" + this.description + "\", isKnown = " + this.isKnown() + ", currentAdviceIndex = " + this.currentAdvice + ", currentAdvice = " + this.getAdvice() + ", currentTextAdvice = \"" + this.getTextAdvice() + "\", allAdvices = {");
		int sizeA = this.advices.size() - 1;
		int sizeC = this.conditions.size() - 1;
		int sizeO = this.operations.size() - 1;
		int i = 0;

		for (Advice a : this.advices) {
			s.append(a);
			if (i < sizeA) s.append(", ");
			i++;
		}

		i = 0;
		s.append("}, allCondtitions = {");
		for (Condition c : this.conditions) {
			s.append(c.toLongString());
			if (i < sizeC) s.append(", ");
			i++;
		}

		i = 0;
		s.append("}, allOperations = {");
		for (Operation o : this.operations) {
			s.append(o.toLongString());
			if (i < sizeO) s.append(", ");
			i++;
		}

		s.append("}]");
		return s.toString();
	}
}

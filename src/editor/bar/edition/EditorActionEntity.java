package editor.bar.edition;

/**
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 29/01/2020
 * @since 5.0 29/01/2020
 */
public class EditorActionEntity implements EditorAction {

	public EditorActionEntity() {
	}

	@Override
	public void doAction() {
		System.out.println("ajout d'une entité");
	}

	@Override
	public void undoAction() {
		System.out.println("suppresion d'une entité");
	}
}

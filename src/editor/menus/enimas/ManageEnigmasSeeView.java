package editor.menus.enimas;

import common.enigmas.Advice;
import common.enigmas.Enigma;
import common.enigmas.condition.Condition;
import common.enigmas.operation.Operation;
import common.hud.EnigmaLabel;
import common.hud.ui.EnigmaLabelUI;
import common.utils.Logger;
import api.utils.Utility;
import common.hud.EnigmaPanel;
import editor.menus.AbstractPopUpView;
import editor.menus.AbstractSubPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
import editor.menus.OptionRunnableFactory;


import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.Iterator;

/**
 * Vue de l'item sélectionné
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0
 * @since 5.0
 */
public class ManageEnigmasSeeView extends AbstractSubPopUpView implements Drawable {

	private static final String TITLE = "Consulter les informations de l'énigme";

	private static final String SEPARATOR = "------------";
	private static final String NONE = "Aucune";
	private static final String OPERATIONS = "Opérations";
	private static final String CONDITIONS = "Conditions";
	private static final String CLUES = "Indices";

	/**
	 * Menu extra
	 */
	private EnigmaPanel extra;

	/**
	 * Vue de l'item sélectionné
	 * @param parent parent
	 */
	ManageEnigmasSeeView(AbstractPopUpView parent) {
		super(TITLE, parent, true);
	}

	void setChecked(Enigma checked) {
		Logger.printDebug("Enigma#See", checked.toLongString());

		this.content.removeAll();
		this.extra = new EnigmaPanel(new GridLayout(1,1));

		EnigmaLabelUI ui = new EnigmaLabelUI();
		ui.setAllBackgrounds(Color.WHITE);
		ui.setAllForegrounds(Color.BLACK);

		int row = 2, c=0;

		String name = checked.getTitle();
		String desc = checked.getDescription();
		Iterator<Advice> allAdvices = checked.getAllAdvices();
		Iterator<Condition> allConditions = checked.getAllConditions();
		Iterator<Operation> allOperations = checked.getAllOperations();

		extra.add(new EnigmaLabel("Nom:"+name, ui));
		extra.add(new EnigmaLabel("Description:"+desc, ui));
		extra.add(new EnigmaLabel(SEPARATOR+" "+CLUES+" "+SEPARATOR, ui));

		//Affichage des indices
		while(allAdvices.hasNext()){
			Advice a = allAdvices.next();
			extra.add(new EnigmaLabel(a.toString(), ui));
			c+=2;
		}

		if(c != 0){
			row += c;
			c = 0;
		} else {
			extra.add(new EnigmaLabel(NONE, ui));
			row++;
		}

		extra.add(new EnigmaLabel(SEPARATOR+" "+CONDITIONS+" "+SEPARATOR, ui));
		row++;
		//Affichage des conditions
		while(allConditions.hasNext()){
			Condition a = allConditions.next();
			extra.add(new EnigmaLabel(a.toLongString(), ui));
			c++;
		}

		if(c != 0){
			row += c;
			c = 0;
		} else {
			extra.add(new EnigmaLabel(NONE, ui));
			row++;
		}

		extra.add(new EnigmaLabel(SEPARATOR+" "+OPERATIONS+" "+SEPARATOR, ui));
		row++;

		//Affichage des opérations
		while(allOperations.hasNext()){
			Operation a = allOperations.next();
			extra.add(new EnigmaLabel(a.toLongString(), ui));
			c++;
		}

		if(c != 0){
			row += c;
		} else {
			extra.add(new EnigmaLabel(NONE, ui));
			row++;
		}

		((GridLayout)this.extra.getLayout()).setRows(row);

		JScrollPane panelS = new JScrollPane(this.extra);
		panelS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		this.content.add(panelS, BorderLayout.CENTER);
		this.content.revalidate();
	}

	@Override
	public Container getDrawable() {
		return this.extra;
	}

	@Override
	public void invalidateDrawable() {
		//close and invalidate list
		this.parent.getCardLayout().show(this.parent.getPanel(), ManageEnigmasView.MENU);
		this.parent.invalidateDrawable();
	}

	@Override
	public void clean() {
	}

	@Override
	public void initComponent() {
	}
}

package editor.view.listeners.available;

import api.enums.AvailablePopUpOption;
import editor.entity.NPC;
import editor.hud.EnigmaPanel;
import editor.hud.ui.EnigmaJCheckBoxUI;
import editor.view.cases.CasePopUp;
import editor.view.listeners.AvailableOptionRunnable;
import editor.view.listeners.available.view.AbstractPopUpView;
import game.entity.map.MapTestScreenCell;

import javax.swing.JCheckBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Définit l'entité actuelle comme étant un Hero
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class DefineAsHero implements AvailableOptionRunnable {

	private static final String HERO = "Héro";

	/**
	 * Parent
	 */
	private final CasePopUp parent;

	/**
	 * Définit l'entité actuelle comme étant un Hero
	 *
	 * @param parent parent
	 */
	public DefineAsHero(CasePopUp parent){
		this.parent = parent;
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.DEFINE_AS_HERO;
	}

	@Override
	public void run() {
		JCheckBox hero = new JCheckBox(HERO);
		hero.setUI(EnigmaJCheckBoxUI.createUI(hero));
		hero.setSelected(((NPC)parent.getCell().getEntity()).isHero());
		hero.addItemListener(new CheckHeroBox(this.parent.getCell()));
		this.parent.getPanel().add(hero);
	}

	@Override
	public void run(AbstractPopUpView view, EnigmaPanel panel) {
		JCheckBox hero = new JCheckBox(HERO);
		hero.setUI(EnigmaJCheckBoxUI.createUI(hero));
		hero.setSelected(((NPC)parent.getCell().getEntity()).isHero());
		hero.addItemListener(new CheckHeroBox(this.parent.getCell()));
		panel.add(hero);
	}

	/**
	 * CheckBox listener
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 *
	 * @version 5.0 25/01/2020
	 * @since 5.0 25/01/2020
	 */
	private static final class CheckHeroBox  implements ItemListener {

		private final NPC npc;

		CheckHeroBox(MapTestScreenCell cell) {
			this.npc = (NPC) cell.getEntity();
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				this.npc.setHero(true);
			} else {
				this.npc.setHero(false);
			}
		}
	}
}
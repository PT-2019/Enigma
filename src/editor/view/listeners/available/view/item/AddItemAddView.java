package editor.view.listeners.available.view.item;

import api.entity.Consumable;
import api.entity.GameObject;
import api.entity.Item;
import api.entity.types.Container;
import api.enums.AvailablePopUpOption;
import api.utils.Utility;
import com.badlogic.gdx.math.Vector2;
import editor.enigma.create.enigma.EnigmaView;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.view.listeners.AvailableOptionRunnable;
import editor.view.listeners.available.view.AbstractPopUpView;
import editor.view.listeners.available.view.AbstractSubPopUpView;
import game.EnigmaGame;
import game.entity.map.MapTestScreen;
import game.screen.TestScreen;
import game.utils.DragAndDropBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;

public class AddItemAddView extends AbstractSubPopUpView {

	private static final String TITLE = "Ajouter un object";
	private static final String NO_ITEMS = "Veuillez sélectionner un objet (menu)";
	private static final String INVALID = "Entité Invalide. Objets Uniquement (livre, ...)";
	private static final String SUMBIT = "Confimer";
	private final EnigmaButton submit;
	private GameObject item;
	private final EnigmaLabel empty;
	private final EnigmaPanel panel;
	private boolean finished;

	private EnumMap<AvailablePopUpOption, AvailableOptionRunnable> runnables;

	public AddItemAddView(AbstractPopUpView parent, AddItemListView list) {
		super(TITLE, parent, true);
		this.finished = false;

		runnables = new EnumMap<>(AvailablePopUpOption.class);
		for (Class<?> c : AvailableOptionRunnable.classes) {
			AvailableOptionRunnable runnable = (AvailableOptionRunnable) Utility.instance(c, parent.getPopUp());
			runnables.put(runnable.getOption(), runnable);
		}

		/*
		MapTestScreenCell cell = parent.getPopUp().getCell();

		//navigation = new NavigationPanel(this);

		if (cell.getEntity() == null) {
			//this.navigation.setText("Aucune entité");
		} else {
			//this.navigation.setText(cell.getEntity().getReadableName());
			if(cell != null && cell.getEntity() != null) {
				for (AvailablePopUpOption option : AvailablePopUpOption.values()) {
					if (AvailablePopUpOption.isAvailable(option, cell.getEntity())) {
						AvailableOptionRunnable runnable = runnables.get(option);
						if (runnable != null) runnable.run(parent, footer);
					}
				}
			}
		}*/

		this.item = null;

		panel = new EnigmaPanel();
		panel.setLayout(new GridBagLayout());
		empty = new EnigmaLabel(NO_ITEMS);
		empty.getComponentUI().setAllForegrounds(Color.YELLOW, Color.YELLOW, Color.YELLOW);
		panel.add(empty);

		submit = new EnigmaButton(SUMBIT);
		submit.setVisible(false);
		submit.addActionListener(new SubmitListener(parent, this, list));
		footer.add(submit);

		this.content.add(panel, BorderLayout.CENTER);
	}

	@Override
	public void update(GameObject object) {
		if(!(object instanceof Consumable)){
			empty.setText(INVALID);
			submit.setVisible(false);
			this.setGameObject(object);
			this.setGameObject(null);
		} else {
			this.setGameObject(object);
			empty.setText(object.getReadableName()+" (id="+object.getID()+")");
			submit.setVisible(true);
		}
		/*for (AvailablePopUpOption option : AvailablePopUpOption.values()) {
			if (AvailablePopUpOption.isAvailable(option, cell.getEntity())) {
				AvailableOptionRunnable runnable = runnables.get(option);
				if (runnable != null) runnable.run(parent, footer);
			}
		}*/
	}

	public void setGameObject(GameObject object) {
		if(this.item != null && !this.finished) {//si j'avais un object temporaire, je le supprime
			//il existe déjà un object
			Vector2 pos = this.item.getGameObjectPosition();
			//this.object est un temporaire
			if(pos == null || pos.x < 0 || pos.y < 0) {
				MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
				//supprime de la map
				map.removeEntity(this.item);
				//l'id du temporaire est forcément le dernier donc transfert de l'id si besoin
				if(object != null && object.getID() > this.item.getID())
					object.setID(this.item.getID());//transfert de l'ID du supprimé
			}
		}
		this.item = object;
	}

	@Override
	public void clean() {
		this.infoLabel.setText("");
		this.empty.setText(NO_ITEMS);
		this.setGameObject(null);
		DragAndDropBuilder.setForPopup(null);
	}

	@Override
	public void onHide() {
	}

	@Override
	public void onShow() {
	}

	@Override
	public void initComponent() {
	}

	public EnigmaLabel getInfoLabel() {
		return this.infoLabel;
	}

	private static class SubmitListener implements ActionListener {
		private final AbstractPopUpView parent;
		private final AddItemAddView addItemAddView;
		private final AddItemListView list;

		public SubmitListener(AbstractPopUpView parent, AddItemAddView addItemAddView, AddItemListView list) {
			this.parent = parent;
			this.addItemAddView = addItemAddView;
			this.list = list;
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			GameObject entity = this.parent.getPopUp().getCell().getEntity();
			if(entity instanceof Container) {
				((Container) entity).addItem((Item) addItemAddView.item);
				addItemAddView.finished = true;
				addItemAddView.clean();
				list.clean();
				list.initComponent();
				parent.getCardLayout().show(parent.getPanel(), "menu");
				addItemAddView.finished = false;
				EnigmaView.setAvailable(null);
			}
		}
	}
}
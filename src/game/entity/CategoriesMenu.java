package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import editor.datas.EntitiesCategories;
import editor.entity.EntityFactory;
import editor.entity.EntitySerializable;
import game.utils.InputListener;
import org.intellij.lang.annotations.MagicConstant;

import javax.swing.*;
import java.awt.dnd.DnDConstants;

public class CategoriesMenu extends Window implements InputListener, Disposable {

	private static final int PADDING = 10, WIDTH = 300;
	public Table t;

	public static CategoriesMenu c;

	public CategoriesMenu() {
		super("",getBasicStyle(Gdx.files.internal("assets/files/atlas/uiskin.json"),
				new TextureAtlas("assets/files/atlas/uiskin.atlas")));
		c = this;

		//this.setMovable(false);
		//this.setResizable(true);
		//this.setVisible(false);

		t = new Table();
		this.add(t);

		//take almost all the screen
		this.setSize(WIDTH, Gdx.graphics.getHeight());

		loadCategory(EntitiesCategories.ROOMS);
	}

	public void loadCategory(EntitiesCategories c){
		t.clear();
		Array<EntitySerializable> entities = EntityFactory.getEntitiesByCategory(c);

		/*for (int i = 0; i < entities.size ; i++) {
			if(i%2 == 0)
				t.row();
			t.add(new EntityContainer(entities.get(i)));
		}*/

		//on ajoute au minimum 12 cases, on met des entités dedans si on en a
		for (int i = 0; i < 12 || i < entities.size; i++) {
			if (i < entities.size)
				t.add(new EntityContainer(entities.get(i)));

			if(i%2 == 0) t.row();
		}
	}

	@Override
	public void dispose() {

	}

	private static Skin getBasicStyle(FileHandle json, TextureAtlas atlas) {
		return new Skin(json, atlas);
	}
}

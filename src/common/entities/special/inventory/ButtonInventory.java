package common.entities.special.inventory;

import api.libgdx.utils.EmptyDrawableFromImage;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import common.entities.Consumable;
import common.entities.Item;
import game.dnd.inventory.InventoryDndBuilder;

/**
 * Bouton qui représente une case de l'inventaire
 */
public class ButtonInventory extends ImageButton {

    /**
     * L'item du bouton
     */
    private Item item;

    /**
     * Image contenu dans le bouton
     */
    private Image img;

    /**
     * Texture actuellement affichée
     */
    private TextureAtlas.AtlasRegion texture;
    private boolean selected;

    @SuppressWarnings("WeakerAccess")
    public ButtonInventory(InventoryDisplay inventoryDisplay, Item i){
        super(inventoryDisplay.getSkin());
        this.item = i;
        refreshButton();
        this.addListener(new InventoryDndBuilder(this, inventoryDisplay));
    }

    @SuppressWarnings("WeakerAccess")
    public ButtonInventory(InventoryDisplay inventoryDisplay){
        super(inventoryDisplay.getSkin());
        this.addListener(new InventoryDndBuilder(this, inventoryDisplay));
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    /**
     * Ajoute l'image de l'item si possible
     */
    public void refreshButton(){
        if(this.item != null){
            //nouvelle texture
            this.texture =
                    new TextureAtlas(((Consumable) this.item).getAtlasPath())
                            .findRegion(((Consumable) this.item).getAtlasRegionName());
            //crée l'image
            if(this.img == null) {
                this.img = new Image(this.texture);
            } else {//on la change
                this.img.setDrawable(new TextureRegionDrawable(this.texture));
            }

            this.add(this.img).width(InventoryDisplay.ACTOR_WIDTH)
                    .height(InventoryDisplay.ACTOR_HEIGHT)
                    .padTop(InventoryDisplay.ACTOR_HEIGHT / 2f);
        } else if(this.img != null){
            this.img.setDrawable(new EmptyDrawableFromImage(img));
        }
    }

    public Image getImg() {
        return this.img;
    }

    public TextureRegion getTexture() {
        return this.texture;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}

package game.gui;

import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import data.EntitiesCategories;

import java.util.ArrayList;

/**
 *
 */
public class Toast extends Window {

    private static final float WIDTH = 500, HEIGHT = 100;
    private static final String TITLE = "Info";
    private static ArrayList<String> messages;

    private static Toast toast;

    private Toast() {
        /*super("test", LibgdxUtility.loadSkin("assets/files/atlas/uiskin.json", "assets/files/atlas/uiskin.atlas"));

        this.setPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, Align.center);
        this.setSize(100,50);

        this.getTitleLabel().setAlignment(Align.center);

        this.setVisible(true);*/
        //chargement du style de la fenêtre
        super(TITLE, LibgdxUtility.loadSkin("assets/files/atlas/uiskin.json", "assets/files/atlas/uiskin.atlas"));
        this.getTitleLabel().setAlignment(Align.center);

        this.setSize(WIDTH, HEIGHT);

        this.setPosition(Gdx.graphics.getWidth()/2f + WIDTH/2f - CategoriesMenu.WIDTH, Gdx.graphics.getHeight()/2f + Gdx.graphics.getHeight()/2f * 0.7f  + HEIGHT/2, Align.center);

        //container contenu catégorie
        Table container = new Table(this.getSkin());

        //ajout des catégories
        Table table = new Table(this.getSkin());
        table.add().padTop(10).row();
        EntitiesCategories[] categories = EntitiesCategories.values();
        table.row();
        //contenu
        ScrollPane scrollPane = new ScrollPane(container, this.getSkin());
        scrollPane.setFadeScrollBars(false);
        scrollPane.setFlickScroll(false);
        table.add(scrollPane).expand().colspan(3).fill();

        this.add(table).expand().fill();

        this.setVisible(true);
    }

    public static void create(Stage s){
        if(toast == null){
            toast = new Toast();
        }
        s.addActor(toast);
    }

    //public static void addToStage(float x, float y, Stage s)

    /*public class DialogBox extends InGameWindow {

        private static final int HEIGHT = 150, CHOICES_PER_LINE = 2;

        //dialog fields
        private final Label text;
        private final Table choices;
        private final HorizontalGroup next;
        private Entity entity;
        private Entity target;

        public DialogBox() {
            super(TalesOfDemonsLords.getFilesManager().getSkin(SkinsList.DIALOG.jsonPath, SkinsList.DIALOG.atlasPath));

            this.text = new Label("", this.getSkin());
            this.text.setWrap(true);
            this.text.setWidth(Gdx.graphics.getWidth() - 4 * PADDING);

            this.setSize(DialogBoxSize.INFORMATION);

            this.choices = new Table();

            this.next = new HorizontalGroup();
            this.next.addActor(new Image(this.getSkin().getAtlas().findRegion("arrow1")));
            this.next.addActor(new Label("Press enter to continue", this.getSkin()));
            this.next.setVisible(false);

            this.add(this.text).width(Gdx.graphics.getWidth() - 4 * PADDING).expandY();
            this.row();
            this.add(this.choices).expandX();
            this.row();
            this.add(this.next);

            this.setPosition(PADDING, PADDING);
        }

        public void setSize(DialogBoxSize size){
            if(size == DialogBoxSize.NORMAL){
                this.text.setAlignment(Align.topLeft);
                this.setSize(Gdx.graphics.getWidth(), HEIGHT);
                this.invalidate();
            } else if( size == DialogBoxSize.INFORMATION){
                this.text.setAlignment(Align.center);
                this.setSize(Gdx.graphics.getWidth(), HEIGHT/2f);
                this.invalidate();
            }
        }

        public void setDialogContent(Entity entity, Entity target) {
            this.entity = entity;
            this.target = target;
            if (!target.hasConversations()) return;
            this.loadContent(target.getCurrentConversation());
        }

        private void resetContent() {
            this.next.setVisible(false);
            this.choices.clear();
            this.text.setText("");
        }

        private void loadContent(Conversation conversation) {
            this.resetContent();
            String name = target.getName();
            if(conversation.isPlayer()) name = entity.getName();
            this.text.setText(name + ": " + conversation.getContent());
            this.next.setVisible(conversation.hasNext());

            if (this.next.isVisible()) return;

            Array<Choice> choices = conversation.getChoices();

            if(choices == null) return;

            for (int i = 1, rowCount = choices.size / CHOICES_PER_LINE - 1; i <= choices.size; i++) {
                Cell<Choice> cell = this.choices.add(choices.get(i - 1));
                if (!cell.getActor().hasSkin()) {
                    cell.getActor().loadSkin(this.getSkin());
                }

                if (rowCount >= 1) {
                    cell.padBottom(5);
                }

                if (i % CHOICES_PER_LINE == 0) {
                    cell.row();
                    rowCount--;
                } else {
                    cell.padRight(50);
                }
            }

            this.choices.invalidate();
        }

        private void nextConversation(boolean forceExit) {
            Conversation c = this.target.getCurrentConversation();
            if (c == null || forceExit || c.hasAction()) {//end
                if (c != null && c.hasAction()) {
                    int retour = EntityActionFactory.create(entity, target, c.getAction()).run();
                    if (retour == EntityActionFactory.NONE)//process normal next
                        this.target.nextConversation();
                    else
                        this.target.setConversation(retour);//set this one*
                }
                this.setVisible(false);
                this.target.setState(State.WAIT);
                this.entity.setState(State.WAIT);
            } else {
                loadContent(c);
            }
        }

        private boolean check(int keycode, boolean process) {
            if (this.isVisible() && entity!=null) {
                if (process) {
                    if (PlayerKeys.USE.isKeyCode(keycode)) {//next ? enter ? end ?
                        if (next.isVisible() || choices.getCells().size == 0) {//process
                            Conversation current = this.target.getCurrentConversation();
                            this.target.nextConversation();
                            nextConversation(current.shouldExit());
                        } else {
                            ArrayCell cells = this.choices.getCells();
                            Choice old = null;
                            //find selected position
                            for (int i = 0; i < cells.size; i++) {
                                old = (Choice) cells.get(i).getActor();
                                if (old.isSelected()) {
                                    break;
                                }
                            }
                            if (old == null) throw new IllegalStateException("pas de conversation target");
                            this.target.setConversation(old.getDestination());
                            nextConversation(false);
                        }
                    } else {//change choice ???
                        //If there are choices then we can select one
                        if (this.choices.getChildren().size != 0) {
                            Array<Cell> cells = this.choices.getCells();
                            int pos = -1;
                            Choice old = null;
                            //find selected position
                            for (int i = 0; i < cells.size; i++) {
                                old = (Choice) cells.get(i).getActor();
                                if (old.isSelected()) {
                                    pos = i;
                                    break;
                                }
                            }
                            if (pos == -1)
                                Gdx.app.error(Dialog.class.toString(), "Dialog choices pos, invalid");
                            else {
                                if (PlayerKeys.MENU_NEXT.isKeyCode(keycode)) {
                                    if (pos + 1 < cells.size) {
                                        ((Choice) cells.get(pos + 1).getActor()).setSelected(true);
                                        old.setSelected(false);
                                    }
                                } else if (PlayerKeys.MENU_PREV.isKeyCode(keycode)) {
                                    if (pos - 1 >= 0) {
                                        ((Choice) cells.get(pos - 1).getActor()).setSelected(true);
                                        old.setSelected(false);
                                    }
                                } else if (PlayerKeys.MENU_DOWN.isKeyCode(keycode)) {
                                    if (pos + CHOICES_PER_LINE < cells.size) {
                                        ((Choice) cells.get(pos + CHOICES_PER_LINE).getActor()).setSelected(true);
                                        old.setSelected(false);
                                    }
                                } else if (PlayerKeys.MENU_UP.isKeyCode(keycode)) {
                                    if (pos - CHOICES_PER_LINE >= 0) {
                                        ((Choice) cells.get(pos - CHOICES_PER_LINE).getActor()).setSelected(true);
                                        old.setSelected(false);
                                    }
                                }
                            }
                        }
                    }
                }

                //catch all events but let menu event go thought
                if (PlayerKeys.MENU.isKeyCode(keycode)) {
                    this.setVisible(false);
                    this.target.setState(State.WAIT);
                    this.entity.setState(State.WAIT);
                    return false;
                }
                return true;
            }

            return false;
        }

        @Override
        public boolean keyDown(int keycode) {
            return this.check(keycode, true);
        }

        @Override
        public boolean keyUp(int keycode) {
            return this.check(keycode, false);
        }

        @Override
        public boolean keyTyped(char character) {
            return this.check(character, false);
        }

        public void setContent(String content){
            this.text.setText(content);
        }

        @Override
        public void dispose() {
            this.entity = null;
            this.target = null;
        }
    }*/


}

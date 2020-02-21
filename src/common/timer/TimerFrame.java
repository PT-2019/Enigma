package common.timer;

import api.libgdx.utils.LibgdxUtility;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import data.EnigmaScreens;
import game.EnigmaGame;
import game.screens.GameEndScreen;

/**
 * Classe qui gère le timer du jeu
 */
public class TimerFrame extends Window {

	private static String SKIN_PATH = "assets/files/atlas/uiskin2.json";

	private static String ATLAS_PATH = "assets/files/atlas/uiskin.atlas";

	private float secondTimer;

	private float minuteTimer;

	/**
	 * On retient la dernière valeur où on a rafraichi le timer
	 */
	private float period;
	/**
	 * temps actuelle
	 */
	private float currentTime;

	private Label time;

	public TimerFrame() {
		super("", LibgdxUtility.loadSkin(SKIN_PATH, ATLAS_PATH));

		this.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() / 12);
		this.setPosition(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight());
		//on met un temps par default
		minuteTimer = 60f;
		secondTimer = 0f;
		period = 1f;
		time = new Label("", this.getSkin());
		this.updateTime();
		this.add(time).expand();
		this.setVisible(true);
	}

	public TimerFrame(float sec, float min) {
		super("", LibgdxUtility.loadSkin(SKIN_PATH, ATLAS_PATH));
		this.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() / 12);
		this.setPosition(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight());
		this.minuteTimer = min;
		this.secondTimer = sec;
		this.period = 1f;
		this.time = new Label("", this.getSkin());
		this.updateTime();
		this.add(time).expand();
		this.setVisible(true);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		this.currentTime += delta;
		if (this.currentTime > this.period) {
			this.currentTime -= this.period;

			if (this.secondTimer == 0) {
				this.secondTimer = 59;
				this.minuteTimer--;
			} else {
				this.secondTimer--;
			}

			if (minuteTimer <= 0 && secondTimer <= 0) {
				this.minuteTimer = this.secondTimer = 0;
				this.updateTime();

				this.period = Integer.MAX_VALUE;

				//préviens GameOver
				GameEndScreen.setHasWin(false);
				//charge l'écran
				EnigmaGame.reload(EnigmaScreens.GAME_END.name());
			} else {
				this.updateTime();
			}
		}
	}

	private void updateTime() {
		this.time.setText((int) minuteTimer + ":" + (int) secondTimer);
	}
}

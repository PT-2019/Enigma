package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Une composant de l'application ayant
 * une texture ou une animation et pouvant être déplacé (glisser déposer)
 *
 * @author Maxime Huyghe
 * @author Quentin Ramsamy--Ageorges
 * @author Thibault Meynier
 * @author Loïc Senecat
 * @author Louka Doz
 * @author Lucca Anthoine
 * @version 1.0 27 octobre 2019
 */
public abstract class ActeurTextureGlisserDeposer extends ActeurTextureAnimation {

	/**
	 * Son possible qui s'active au déplacement
	 */
	protected String dragSound;

	/**
	 * Indique si on peut glisser cet objet
	 */
	protected boolean glissable;

	/**
	 * Indique si deposer sur cet objet est "valide"
	 */
	protected boolean deposable;

	/**
	 * L'observateur de base du glisser déposer
	 */
	protected GlisserDeposer listener;

	/**
	 * Construit une texture glissable
	 */
	public ActeurTextureGlisserDeposer() {
		super();
		this.listener = new GlisserDeposer(this);
		addListener(this.listener);
		this.dragSound = "";
		this.deposable = true;
		this.glissable = true;
	}

	/**
	 * Retourne vrai si on peut glisser cet acteur
	 * @return vrai si on peut glisser cet acteur
	 */
	public boolean estGlissable(){ return this.glissable; }

	/**
	 * Retourne vrai si on peut déposer un acteur glissable sur cet acteur
	 * @return vrai si on peut déposer un acteur glissable surcet acteur
	 */
	public boolean estDeposable(){ return this.deposable; }
}


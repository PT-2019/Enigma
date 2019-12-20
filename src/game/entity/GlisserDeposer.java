package game.entity;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Observateur d'un acteur déplaçable, se charge de le déplacer
 *
 * @author Maxime Huyghe
 * @author Quentin Ramsamy--Ageorges
 * @author Thibault Meynier
 * @author Loïc Senecat
 * @author Louka Doz
 * @author Lucca Anthoine
 * @version 1.0 28 octobre 2019
 * @see ActeurTextureGlisserDeposer
 */
public class GlisserDeposer extends InputListener {

	/** l'acteur à déplacer **/
	protected ActeurTextureGlisserDeposer acteur;

	/** de combien on le déplace **/
	protected float deplacementX, deplacementY;

	/** position de départ du joueur **/
	protected float positionDepartX, positionDepartY;

	/** position intermédiaire **/
	protected float positionIntermediaireX, positionIntermediaireY;

	/** l'entité n'a jamais étée déplacée **/
	protected boolean premierDeplacement;

	/**
	 * Observateur d'un acteur déplaçable, se charge de le déplacer
	 *
	 * @param acteur l'acteur à observer et déplacer
	 */
	public GlisserDeposer(ActeurTextureGlisserDeposer acteur) {
		this.acteur = acteur;
		this.deplacementX = 0;
		this.deplacementY = 0;
		this.positionDepartX = 0;
		this.positionDepartY = 0;
		this.premierDeplacement = true;
		this.positionIntermediaireX = 0;
		this.positionIntermediaireY = 0;
	}

	// Sélection
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if(this.acteur.estGlissable()) {
			this.deplacementX = x; //sauvegarde la décalage
			this.deplacementY = y;
			//garde la position de départ ou cas ou on doit l'y ramener
			this.positionIntermediaireX = this.acteur.getX();
			this.positionIntermediaireY = this.acteur.getY();
			if(premierDeplacement){
				this.positionDepartX = this.acteur.getX();
				this.positionDepartY = this.acteur.getY();
				this.premierDeplacement = false;
			}
			//this.acteur.addAction(Actions.scaleTo(1.3f, 1.3f, 0.5f)); //zoom
			this.acteur.toFront();//place au premier plan
			return true; //event traité
		}
		return false;
	}

	// Relachement
	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		//this.acteur.addAction( Actions.scaleTo(1f, 1f, 0.5f) );//dezoom
		this.acteur.addAction(Actions.moveTo(this.positionDepartX,this.positionDepartY, 0.5f));
	}

	// Déplacement
	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		//calcule entre la position du clic et la nouvelle position
		//déplace l'acteur à sa nouvelle pos
		this.acteur.moveBy(x - this.deplacementX,y - this.deplacementY);
	}
}


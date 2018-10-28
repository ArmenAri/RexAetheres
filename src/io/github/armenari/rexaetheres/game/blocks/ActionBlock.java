package io.github.armenari.rexaetheres.game.blocks;

import io.github.armenari.rexaetheres.renderer.Animation;
import io.github.armenari.rexaetheres.renderer.Renderer;

public abstract class ActionBlock {

	protected int ID;
	protected Animation animation;
	protected boolean playAnimation = false;
	protected int x, y;

	protected boolean actionDone = false;
	protected boolean unactionDone = false;
	protected boolean infiniteAction;
	protected boolean multiAction;

	public ActionBlock(int x, int y, int ID, Animation animation) {
		this.setID(ID);
		this.animation = animation;
		this.x = x;
		this.y = y;
		this.infiniteAction = false;
		this.multiAction = false;
	}

	public abstract void action();

	public abstract void unaction();

	public void playAnimation() {
		this.animation.next();
	}

	public void closeAnimation() {
		this.animation.previous();
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	public Animation getAnimation() {
		return this.animation;
	}

	public void render(float f, float g, int frame) {
		Renderer.renderUIWithAnimation(this.getAnimation().getAsset(), f, g, frame);
	}

	/**
	 * @return the actionDone
	 */
	public boolean isActionDone() {
		return actionDone;
	}

	/**
	 * @param actionDone
	 *            the actionDone to set
	 */
	public void setActionDone(boolean actionDone) {
		this.actionDone = actionDone;
	}

	/**
	 * @return the unactionDone
	 */
	public boolean isUnactionDone() {
		return unactionDone;
	}

	/**
	 * @param unactionDone
	 *            the unactionDone to set
	 */
	public void setUnactionDone(boolean unactionDone) {
		this.unactionDone = unactionDone;
	}

	public boolean isInfiniteAction() {
		return infiniteAction;
	}

	public boolean isMultiAction() {
		return multiAction;
	}

}

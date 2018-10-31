package io.github.armenari.rexaetheres.game.blocks;

import io.github.armenari.rexaetheres.renderer.Animation;

public class Activator extends ActionBlock {

	private int signal;
	private int code;
	private boolean activated = false;

	public Activator(int x, int y, int ID, Animation animation, int code) {
		super(x, y, ID, animation);
		animation.setFrame((int) (Math.random() * animation.getFrameLimit() + 1));
		multiAction = true;
		this.setCode(code);
	}

	@Override
	public void action() {
		this.setSignal(this.animation.getFrame());
		if (getSignal() == code) {
			activated = true;
		} else {
			activated = false;
		}
	}

	@Override
	public void unaction() {
		this.setSignal(this.animation.getFrame());
		if (getSignal() == code) {
			activated = true;
		} else {
			activated = false;
		}
	}

	/**
	 * @return the signal
	 */
	public int getSignal() {
		return signal;
	}

	/**
	 * @param signal the order to set
	 */
	public void setSignal(int signal) {
		this.signal = signal;
	}

	/**
	 * @return the order
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param order the order to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}

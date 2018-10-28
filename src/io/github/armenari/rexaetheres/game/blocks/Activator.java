package io.github.armenari.rexaetheres.game.blocks;

import io.github.armenari.rexaetheres.renderer.Animation;

public class Activator extends ActionBlock {

	private int signal;
	
	public Activator(int x, int y, int ID, Animation animation) {
		super(x, y, ID, animation);
		multiAction = true;
	}

	@Override
	public void action() {
		this.setSignal(this.animation.getFrame());  
	}

	@Override
	public void unaction() {
		this.setSignal(this.animation.getFrame());
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}
}

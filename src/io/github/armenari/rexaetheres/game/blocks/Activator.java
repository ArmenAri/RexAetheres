package io.github.armenari.rexaetheres.game.blocks;

import io.github.armenari.rexaetheres.utils.Animation;

public class Activator extends ActionBlock {

	private int signal;
	
	public Activator(int x, int y, int ID, Animation animation) {
		super(x, y, ID, animation);
		multiAction = true;
	}

	@Override
	public void action() {
		this.signal = this.animation.getFrame();
		System.out.println(signal);
	}

	@Override
	public void unaction() {
		this.signal = this.animation.getFrame();
		System.out.println(signal);
	}
}

package io.github.armenari.rexaetheres.game.items;

import io.github.armenari.rexaetheres.renderer.Texture;

public class Scroll extends Item {

	public Scroll(int ID, String name, int x, int y) {
		super(ID, name, x, y);
		this.texture = Texture.scrolls;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGuiOpened(boolean b) {
		// TODO Auto-generated method stub
		
	}

}

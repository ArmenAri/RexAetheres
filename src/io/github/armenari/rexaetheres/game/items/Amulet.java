package io.github.armenari.rexaetheres.game.items;

import io.github.armenari.rexaetheres.renderer.Texture;

public class Amulet extends Item {

	public Amulet(int ID, String name, int x, int y) {
		super(ID, name, x, y);
		this.texture = Texture.amulets;
	}

	@Override
	public void action() {
		
	}

}

package io.github.armenari.rexaetheres.game.items;

import io.github.armenari.rexaetheres.renderer.Texture;

public class Potion extends Item {

	public Potion(int ID, String name, int x, int y) {
		super(ID, name, x, y);
		this.texture = Texture.potions;
	}

	@Override
	public void action() {

	}

}

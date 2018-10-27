package io.github.armenari.rexaetheres.game.blocks;

import java.util.Random;

import io.github.armenari.rexaetheres.game.Level;
import io.github.armenari.rexaetheres.game.items.Amulet;
import io.github.armenari.rexaetheres.game.items.Potion;
import io.github.armenari.rexaetheres.game.items.Scroll;
import io.github.armenari.rexaetheres.utils.Animation;

public class Chest extends ActionBlock {

	public Chest(int x, int y, int ID, Animation animation) {
		super(x, y, ID, animation);
	}

	@Override
	public void action() {
		Level.items.add(new Potion(new Random().nextInt(12), "Potion", x, y));
		Level.items.add(new Amulet(new Random().nextInt(12), "Amulet", x, y));
		Level.items.add(new Scroll(new Random().nextInt(12), "Scroll", x, y));

	}

	@Override
	public void unaction() {

	}
}

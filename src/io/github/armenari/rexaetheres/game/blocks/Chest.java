package io.github.armenari.rexaetheres.game.blocks;

import java.util.Random;

import io.github.armenari.rexaetheres.game.Level;
import io.github.armenari.rexaetheres.game.items.Amulet;
import io.github.armenari.rexaetheres.game.items.HintPaper;
import io.github.armenari.rexaetheres.game.items.Potion;
import io.github.armenari.rexaetheres.game.items.Scroll;
import io.github.armenari.rexaetheres.renderer.Animation;

public class Chest extends ActionBlock {

	public Chest(int x, int y, int ID, Animation animation) {
		super(x, y, ID, animation);
	}

	@Override
	public void action() {
		Level.items.add(new Potion(new Random().nextInt(12), "Potion", x, y));
		Level.items.add(new Amulet(new Random().nextInt(12), "Amulet", x, y));
		Level.items.add(new Scroll(new Random().nextInt(12), "Scroll", x, y));
		Level.items.add(new HintPaper(0, "Paper", x, y, "Bonjour et bienvenue"
														 + "  dans RexAetheres. "
														 + "                          "
														 + "Pour sortir composer le co"
														 + "de requis !"
														 + " Il est tres simple mais i"
														 + "l faut reflechir un peu qu"
														 + "and meme !                "
														 + "(3 - 2)                   "
														 + "(4 - 2)                   "
														 + "(5 - 2)                   "
														 + "Bonne chance ! Et merceeee"));
	}

	@Override
	public void unaction() {
		
	}
}

package io.github.armenari.rexaetheres.game.blocks;

import io.github.armenari.rexaetheres.game.Game;
import io.github.armenari.rexaetheres.game.Level;
import io.github.armenari.rexaetheres.renderer.Animation;
import io.github.armenari.rexaetheres.utils.Constants;

public class Door extends ActionBlock {

	private Door destination;

	public Door(int x, int y, int ID, Animation animation) {
		super(x, y, ID, animation);
		infiniteAction = true;
	}

	@Override
	public void action() {
		if (Game.access_granted) {
			if (destination == null) {
				for (int i = 0; i < Level.actionBlocks.size(); i++) {
					if (Level.actionBlocks.get(i).getClass().getName().contains("Door")
							&& Level.actionBlocks.get(i).getID() == this.getID() && Level.actionBlocks.get(i) != this) {
						destination = (Door) Level.actionBlocks.get(i);
					}
				}
			}

			Level.player.setPosX(destination.getX());
			Level.player.setPosY(destination.getY() + Constants.TILE_SIZE * Constants.SCALE);
			destination.getAnimation().setFrame(destination.getAnimation().getFrameLimit());
		}
	}

	@Override
	public void unaction() {
		// TODO Auto-generated method stub
	}
}

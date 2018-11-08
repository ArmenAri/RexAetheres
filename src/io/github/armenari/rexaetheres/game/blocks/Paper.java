package io.github.armenari.rexaetheres.game.blocks;

import org.lwjgl.opengl.Display;

import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.utils.Constants;

public class Paper extends TileBlocks {
	
	private String text;

	public Paper(String text, int x, int y, int ID) {
		super(x, y, ID);
		this.text = text;
	}

	@Override
	public void action() {
		
	}

	@Override
	public void render() {
		Renderer.renderQuad(Display.getWidth() / 2 - Constants.PAPER_WIDTH / 2, Display.getHeight() / 2 - Constants.HEIGHT / 2, Constants.PAPER_WIDTH, 700, Constants.BLACK);
		Renderer.renderText(text, 0, 0, 5, 16);
	}

}

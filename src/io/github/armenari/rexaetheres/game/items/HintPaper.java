package io.github.armenari.rexaetheres.game.items;

import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.renderer.Texture;
import io.github.armenari.rexaetheres.utils.Constants;

public class HintPaper extends Item {
	
	private boolean guiOpened = false;
	private static final float WIDTH = 400;
	private static final float HEIGHT = WIDTH * 1.618f;
	private String msg;

	public HintPaper(int ID, String name, int x, int y, String msg) {
		super(ID, name, x, y);
		this.texture = Texture.hint_paper;
		this.infiniteUse = true;
		this.msg = msg;
	}

	@Override
	public void action() {
		this.setGuiOpened(true);
	}
	
	public void renderGUI() {
		Renderer.renderQuad(Constants.WIDTH / 2 - WIDTH / 2 , Constants.HEIGHT / 2 - HEIGHT / 2, WIDTH, HEIGHT, Constants.WHITE);
		Renderer.renderText(msg, Constants.WIDTH / 2 - WIDTH / 2 , Constants.HEIGHT / 2 - HEIGHT / 2, WIDTH, 16, Constants.BLACK);
	}

	/**
	 * @return the guiOpened
	 */
	public boolean isGuiOpened() {
		return guiOpened;
	}

	/**
	 * @param guiOpened the guiOpened to set
	 */
	public void setGuiOpened(boolean guiOpened) {
		this.guiOpened = guiOpened;
	}

	@Override
	public void unaction() {
		// TODO Auto-generated method stub
		
	}

}

package io.github.armenari.rexaetheres.game.items;

import java.util.Random;

import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.renderer.Texture;
import io.github.armenari.rexaetheres.utils.Constants;

public abstract class Item {

	protected int ID;
	protected String name;
	protected Texture texture;
	

	protected float x;
	protected float y;

	protected float dx = (float) new Random().nextGaussian() * 3f;
	protected float dy = (float) new Random().nextGaussian() * 3f;
	
	protected boolean infiniteUse = false;

	public Item(int ID, String name, int x, int y) {
		this.ID = ID;
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public abstract void action();

	public void render() {
		int tile_x = Constants.TILE_SIZE * (ID % 8);
		int tile_y = Constants.TILE_SIZE * (int) (ID / 8);
		
		Renderer.renderOffsetImage(texture, x, y, Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE, texture.getWidth(),
				texture.getHeight(), new float[] { 1, 1, 1, 0.7f }, tile_x, tile_y, tile_x + Constants.TILE_SIZE, tile_y + Constants.TILE_SIZE);
		
	
	}

	public void render(float x_, float y_, float size) {
		int tile_x = Constants.TILE_SIZE * (ID % 8);
		int tile_y = Constants.TILE_SIZE * (int) (ID / 8);
		
		
	    Renderer.renderOffsetImage(texture, x_, y_, size, size, texture.getWidth(), texture.getHeight(),
				new float[] { 1, 1, 1, 0.7f }, tile_x, tile_y, tile_x + Constants.TILE_SIZE, tile_y + Constants.TILE_SIZE);
	
	   
	    
	}

	public void update() {
		x += dx;
		y += dy;

		dx *= Constants.DRAG;
		dy *= Constants.DRAG;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @param dx
	 *            the dx to set
	 */
	public void setDx(float dx) {
		this.dx = dx;
	}

	/**
	 * @param dy
	 *            the dy to set
	 */
	public void setDy(float dy) {
		this.dy = dy;
	}

	/**
	 * @return the dx
	 */
	public float getDx() {
		return dx;
	}

	/**
	 * @return the dy
	 */
	public float getDy() {
		return dy;
	}

	/**
	 * @return the infiniteUse
	 */
	public boolean isInfiniteUse() {
		return infiniteUse;
	}

	public abstract void unaction();

	public abstract void setGuiOpened(boolean b);

}

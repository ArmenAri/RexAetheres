package io.github.armenari.rexaetheres.game.blocks;

public abstract class TileBlocks {

	protected int ID;
	protected int x, y;

	public TileBlocks(int x, int y, int ID) {
		this.setID(ID);
		this.x = x;
		this.y = y;
	}

	public abstract void action();
	public abstract void render();

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
}

package io.github.armenari.rexaetheres.game.entities;

public abstract class Entity {
	
	private String name;
	protected float posX, posY;

	public Entity(String name, float posX, float posY) {
		this.name = name;
		this.posX = posX;
		this.posY = posY;
	}
	
	/*
	 * Rendering the entity on the screen
	 */
	public abstract void render();
	
	/*
	 * Updating the entity
	 */
	public abstract void update();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the posX
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}
}

package io.github.armenari.rexaetheres.game.entities;

import java.util.Timer;

import org.lwjgl.input.Mouse;

import io.github.armenari.rexaetheres.game.Game;
import io.github.armenari.rexaetheres.game.Inventory;
import io.github.armenari.rexaetheres.game.Level;
import io.github.armenari.rexaetheres.game.blocks.ActionBlock;
import io.github.armenari.rexaetheres.game.blocks.Door;
import io.github.armenari.rexaetheres.game.items.Item;
import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.renderer.Texture;
import io.github.armenari.rexaetheres.utils.Constants;
import io.github.armenari.rexaetheres.utils.Methods;

public class Player extends Entity {

	private int life = 75;
	private int mana = 50;
	private float endurance = 100;

	Inventory inventory;

	Timer timer = new Timer();

	public Player(String name, float posX, float posY) {
		super(name, posX, posY);
		this.inventory = new Inventory();
	}

	public Player(String name) {
		super(name, 2 * Constants.TILE_SIZE * Constants.SCALE, 2 * Constants.TILE_SIZE * Constants.SCALE);
		this.inventory = new Inventory();
	}

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return the mana
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * @param mana the mana to set
	 */
	public void setMana(int mana) {
		this.mana = mana;
	}

	/**
	 * @return the endurance
	 */
	public float getEndurance() {
		return endurance;
	}

	/**
	 * @param endurance the endurance to set
	 */
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	@Override
	public void render() {
		Renderer.renderImage(Texture.character, this.posX, this.posY, Constants.PLAYER_SIZE * Constants.SCALE,
				Constants.PLAYER_SIZE * Constants.SCALE, new float[] { 1, 1, 1, 0.9f });
		Renderer.renderQuad(posX, posY - 20, (getLife() * Constants.TILE_SIZE * Constants.SCALE) / 100, 5,
				Constants.RED);
		Renderer.renderQuad(posX, posY - 15, (getMana() * Constants.TILE_SIZE * Constants.SCALE) / 100, 5,
				Constants.BLUE);
		Renderer.renderQuad(posX, posY - 10, (getEndurance() * Constants.TILE_SIZE * Constants.SCALE) / 100, 5,
				Constants.LIGHT_GREEN);
		

	}

	float alpha = 0;

	@Override
	public void update() {
		inventory.render();
		inventory.update();
		recoverItems();

		if (Mouse.isButtonDown(0)) {
			int x = (int) (Mouse.getX() / (Constants.TILE_SIZE * Constants.SCALE));
			int y = (int) ((Constants.HEIGHT - Mouse.getY()) / (Constants.TILE_SIZE * Constants.SCALE));
			if (!(x > Game.levelReader.getMapWidth() - 1 || y > Game.levelReader.getMapHeight() - 1)) {
				if (!Game.levelReader.getTile(x, y).isSolid()) {
					x = (int) (x * Constants.TILE_SIZE * Constants.SCALE);
					y = (int) (y * Constants.TILE_SIZE * Constants.SCALE);
					if (Math.abs(x - posX) <= 64 && Math.abs(y - posY) <= 64 && getEndurance() > 0) {
						setPosX(x);
						setPosY(y);
						endurance -= 0.1f;

					} else {
						Game.notifications
								.get(0).launch(
										"you cannot move to " + (int) (x / Constants.TILE_SIZE / Constants.SCALE)
												+ " : " + (int) (y / Constants.TILE_SIZE / Constants.SCALE),
										Constants.RED);
						float cosAlpha = (float) Math.abs(Math.sin(alpha)) / 5;
						int posXScaled = (int) (posX / (Constants.TILE_SIZE * Constants.SCALE));
						int posYScaled = (int) (posY / (Constants.TILE_SIZE * Constants.SCALE));

						if (!Game.levelReader.getTile(posXScaled + 1, posYScaled).isSolid()) {
							Renderer.renderImage(Texture.character, posX + 1 * Constants.TILE_SIZE * Constants.SCALE,
									posY, Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE,
									new float[] { Constants.LIGHT_GREEN[0], Constants.LIGHT_GREEN[1],
											Constants.LIGHT_GREEN[2], cosAlpha });
						}
						if (!Game.levelReader.getTile(posXScaled - 1, posYScaled).isSolid()) {
							Renderer.renderImage(Texture.character, posX - 1 * Constants.TILE_SIZE * Constants.SCALE,
									posY, Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE,
									new float[] { Constants.LIGHT_GREEN[0], Constants.LIGHT_GREEN[1],
											Constants.LIGHT_GREEN[2], cosAlpha });
						}
						if (!Game.levelReader.getTile(posXScaled, posYScaled + 1).isSolid()) {
							Renderer.renderImage(Texture.character, posX,
									posY + 1 * Constants.TILE_SIZE * Constants.SCALE,
									Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE,
									new float[] { Constants.LIGHT_GREEN[0], Constants.LIGHT_GREEN[1],
											Constants.LIGHT_GREEN[2], cosAlpha });
						}
						if (!Game.levelReader.getTile(posXScaled, posYScaled - 1).isSolid()) {
							Renderer.renderImage(Texture.character, posX,
									posY - 1 * Constants.TILE_SIZE * Constants.SCALE,
									Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE,
									new float[] { Constants.LIGHT_GREEN[0], Constants.LIGHT_GREEN[1],
											Constants.LIGHT_GREEN[2], cosAlpha });
						}
						if (!Game.levelReader.getTile(posXScaled - 1, posYScaled - 1).isSolid()) {
							Renderer.renderImage(Texture.character, posX - 1 * Constants.TILE_SIZE * Constants.SCALE,
									posY - 1 * Constants.TILE_SIZE * Constants.SCALE,
									Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE,
									new float[] { Constants.LIGHT_GREEN[0], Constants.LIGHT_GREEN[1],
											Constants.LIGHT_GREEN[2], cosAlpha });
						}
						if (!Game.levelReader.getTile(posXScaled + 1, posYScaled + 1).isSolid()) {
							Renderer.renderImage(Texture.character, posX + 1 * Constants.TILE_SIZE * Constants.SCALE,
									posY + 1 * Constants.TILE_SIZE * Constants.SCALE,
									Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE,
									new float[] { Constants.LIGHT_GREEN[0], Constants.LIGHT_GREEN[1],
											Constants.LIGHT_GREEN[2], cosAlpha });
						}
						if (!Game.levelReader.getTile(posXScaled + 1, posYScaled - 1).isSolid()) {
							Renderer.renderImage(Texture.character, posX + 1 * Constants.TILE_SIZE * Constants.SCALE,
									posY - 1 * Constants.TILE_SIZE * Constants.SCALE,
									Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE,
									new float[] { Constants.LIGHT_GREEN[0], Constants.LIGHT_GREEN[1],
											Constants.LIGHT_GREEN[2], cosAlpha });
						}
						if (!Game.levelReader.getTile(posXScaled - 1, posYScaled + 1).isSolid()) {
							Renderer.renderImage(Texture.character, posX - 1 * Constants.TILE_SIZE * Constants.SCALE,
									posY + 1 * Constants.TILE_SIZE * Constants.SCALE,
									Constants.TILE_SIZE * Constants.SCALE, Constants.TILE_SIZE * Constants.SCALE,
									new float[] { Constants.LIGHT_GREEN[0], Constants.LIGHT_GREEN[1],
											Constants.LIGHT_GREEN[2], cosAlpha });
						}
						alpha += 0.06f;
					}

				} else {
					actionWithBlocks(x, y);
				}
			}
		}

		if (Mouse.isButtonDown(1)) {
			int x = (int) (Mouse.getX() / (Constants.TILE_SIZE * Constants.SCALE));
			int y = (int) ((Constants.HEIGHT - Mouse.getY()) / (Constants.TILE_SIZE * Constants.SCALE));
			if (!(x > Game.levelReader.getMapWidth() - 1 || y > Game.levelReader.getMapHeight() - 1)) {
				if (Game.levelReader.getTile(x, y).isSolid()) {
					unactionWithBlocks(x, y);
				}
			}
		}
	}

	private void recoverItems() {
		for (int i = 0; i < Level.items.size(); i++) {
			Item item = Level.items.get(i);
			if (Methods.distance(item.getX() / (Constants.TILE_SIZE * Constants.SCALE),
					item.getY() / (Constants.TILE_SIZE * Constants.SCALE),
					posX / (Constants.TILE_SIZE * Constants.SCALE),
					posY / (Constants.TILE_SIZE * Constants.SCALE)) <= 3f) {
				if (inventory.getItems().size() < inventory.getInvSize()) {
					if (item.getDy() < 0.002f) {
						item.setDx((posX / (Constants.TILE_SIZE * Constants.SCALE)
								- item.getX() / (Constants.TILE_SIZE * Constants.SCALE)) * 2);
						item.setDy((posY / (Constants.TILE_SIZE * Constants.SCALE)
								- item.getY() / (Constants.TILE_SIZE * Constants.SCALE)) * 2);
					}
					if (Methods.distance(item.getX() / (Constants.TILE_SIZE * Constants.SCALE),
							item.getY() / (Constants.TILE_SIZE * Constants.SCALE),
							posX / (Constants.TILE_SIZE * Constants.SCALE),
							posY / (Constants.TILE_SIZE * Constants.SCALE)) <= 0.4f) {
						inventory.addItem(item);
						Game.notifications.get(0).launch(item.getName() + " x1", Constants.LIGHT_BLUE);
						Level.items.remove(item);
					}
				}
			}
		}
	}

	private void unactionWithBlocks(int x, int y) {
		for (ActionBlock ab : Level.actionBlocks) {
			if (Game.levelReader.getTile(x, y).getPosX() == ab.getX()
					&& Game.levelReader.getTile(x, y).getPosY() == ab.getY()
					&& Methods.distance(x, y, posX / (Constants.TILE_SIZE * Constants.SCALE),
							posY / (Constants.TILE_SIZE * Constants.SCALE)) <= 1) {
				ab.closeAnimation();
				if (ab.isMultiAction()) {
					ab.unaction();
				}
				if (ab.getAnimation().getFrame() == 0) {
					while (Mouse.next()) {
						if (!Mouse.getEventButtonState()) {
							if (Mouse.getEventButton() == 0) {
								if (!ab.isUnactionDone()) {
									if (!ab.isMultiAction()) {
										ab.unaction();
									}
									ab.setUnactionDone(true);
								}
							}
						}
					}
				}
			}
		}
	}

	private void actionWithBlocks(int x, int y) {
		for (int i = 0; i < Level.actionBlocks.size(); i++) {
			ActionBlock ab = Level.actionBlocks.get(i);
			if (Game.levelReader.getTile(x, y).getPosX() == ab.getX()
					&& Game.levelReader.getTile(x, y).getPosY() == ab.getY()
					&& Methods.distance(x, y, posX / (Constants.TILE_SIZE * Constants.SCALE),
							posY / (Constants.TILE_SIZE * Constants.SCALE)) <= 1) {

				if (ab instanceof Door) {
					if (Game.access_granted) {
						ab.playAnimation();
						
					} else {
						Game.notifications.get(0).launch("THE DOOR IS LOCKED !", Constants.RED);
					}
				} else {
					ab.playAnimation();
				}

				if (ab.isMultiAction()) {
					ab.action();
				}

				if (ab.getAnimation().getFrame() == ab.getAnimation().getFrameLimit()) {
					while (Mouse.next()) {
						if (!Mouse.getEventButtonState()) {
							if (Mouse.getEventButton() == 0) {
								if (!ab.isActionDone()) {
									if (!ab.isMultiAction()) {
										ab.action();
									}
									if (!ab.isInfiniteAction())
										ab.setActionDone(true);
								}
							}
						}
					}
				}
			}
		}
	}
}

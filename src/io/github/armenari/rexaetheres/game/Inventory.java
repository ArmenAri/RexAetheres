package io.github.armenari.rexaetheres.game;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import io.github.armenari.rexaetheres.game.items.Item;
import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.utils.Constants;

public class Inventory {

	private ArrayList<Item> items;

	private int startX = 932;
	private int startY = 150;

	private int invSize = 72;
	private int invDisplayItemSize = 32;
	private int itemsPerRow = 6;

	public Inventory() {
		this.items = new ArrayList<>();
	}

	public void addItem(Item item) {
		if (items.size() < 72)
			this.items.add(item);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public void render() {
		Renderer.renderQuad(startX, startY, (invDisplayItemSize * itemsPerRow),
				(invDisplayItemSize * (invSize / itemsPerRow)), new float[] { 1, 1, 1, 0.2f });
		Renderer.renderText("Inventory", startX - 16, startY - 50, 25);
		for (int i = 0; i < getItems().size(); i++) {
			getItems().get(i).render(startX + (i % itemsPerRow) * invDisplayItemSize,
					startY + (i / itemsPerRow) * invDisplayItemSize, invDisplayItemSize);
		}
	}

	public void update() {
		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Constants.HEIGHT - Mouse.getY();
			if (x > startX && x < startX + (invDisplayItemSize * itemsPerRow) && y > startY
					&& y < startY + (invDisplayItemSize * (invSize / itemsPerRow))) {
				while (Mouse.next()) {
					if (!Mouse.getEventButtonState()) {
						if (Mouse.getEventButton() == 0) {
							int itemIndex = ((x / invDisplayItemSize))
									+ (y / invDisplayItemSize - itemsPerRow) * itemsPerRow - 23;
							if (itemIndex < items.size()) {
								items.get(itemIndex).action();
								removeItem(items.get(itemIndex));
							}
						}
					}
				}

			}
		}
	}

	/**
	 * @return the items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * @return the invSize
	 */
	public int getInvSize() {
		return invSize;
	}

	/**
	 * @param invSize
	 *            the invSize to set
	 */
	public void setInvSize(int invSize) {
		this.invSize = invSize;
	}

}

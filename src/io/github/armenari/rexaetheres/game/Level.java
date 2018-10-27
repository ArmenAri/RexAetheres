package io.github.armenari.rexaetheres.game;

import io.github.armenari.rexaetheres.game.blocks.ActionBlock;
import io.github.armenari.rexaetheres.game.blocks.Activator;
import io.github.armenari.rexaetheres.game.blocks.Chest;
import io.github.armenari.rexaetheres.game.blocks.Door;
import io.github.armenari.rexaetheres.game.entities.Player;
import io.github.armenari.rexaetheres.game.items.Item;
import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.utils.Animation;
import io.github.armenari.rexaetheres.utils.Constants;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;

public class Level {

	public static Player player;
	public static ArrayList<ActionBlock> actionBlocks;
	public static ArrayList<Item> items;
	private ArrayList<Tile> tiles;
	private ArrayList<Tile> floor;
	private int mapHeight;
	private int mapWidth;

	public Level(String level) {
		actionBlocks = new ArrayList<>();
		items = new ArrayList<>();
		player = new Player("Player");
		floor = new ArrayList<>();
		this.tiles = createTileArray(level);

	}

	public void changeLevel(String path) {
		actionBlocks.clear();
		this.floor.clear();
		this.tiles.clear();
		this.tiles = createTileArray(path);
	}

	public void render() {
		Renderer.renderBackground();
		for (Tile t : getFloor()) {
			Renderer.renderTile(t);
		}
		for (Tile t : getTiles()) {
			Renderer.renderTile(t);
		}
		for (int i = 0; i < actionBlocks.size(); i++) {
			ActionBlock ab = actionBlocks.get(i);
			ab.render(ab.getX(), ab.getY(), ab.getAnimation().getFrame());
		}
		for (Item i : items) {
			i.render();
		}
		player.render();
	}

	public void update() {
		for (Item i : items) {
			i.update();
		}
		player.update();
	}

	public ArrayList<Tile> createTileArray(String path) {
		ArrayList<Tile> t = new ArrayList<>();
		JsonObject lvl;
		lvl = Renderer.initJSONFile("levels/" + path);
		JsonArray layer = lvl.getJsonArray("layers");
		int map_width = layer.getJsonObject(0).getInt("width");
		int map_height = layer.getJsonObject(0).getInt("height");

		this.mapHeight = map_height;
		this.mapWidth = map_width;

		for (int k = 0; k < layer.size(); k++) {
			for (int j = 0; j < map_height; j++) {
				for (int i = 0; i < map_width; i++) {
					JsonArray ids = layer.getJsonObject(k).getJsonArray("data");
					int id = ids.getInt(j * map_width + i) - 1;
					int tile_x = 16 * (id % 24);
					int tile_y = 16 * (int) (id / 24);
					int tile_size_x = tile_x + 16;
					int tile_size_y = tile_y + 16;
					if (id >= 0 && id != 54) {
						if(id == 120)
							actionBlocks.add(new Activator((int) (i * 16 * Constants.SCALE),
									(int) (j * 16 * Constants.SCALE), id, new Animation("activator", false)));
						else if (id == 176)
							actionBlocks.add(new Chest((int) (i * 16 * Constants.SCALE),
									(int) (j * 16 * Constants.SCALE), id, new Animation("chest", false)));
						else if (id == 172)
							actionBlocks.add(new Door((int) (i * 16 * Constants.SCALE),
									(int) (j * 16 * Constants.SCALE), id, new Animation("wooden_door", false)));
						else if (id == 148)
							actionBlocks.add(new Door((int) (i * 16 * Constants.SCALE),
									(int) (j * 16 * Constants.SCALE), id, new Animation("metal_door", false)));
						else if (id == 192)
							actionBlocks.add(new Door((int) (i * 16 * Constants.SCALE),
									(int) (j * 16 * Constants.SCALE), id, new Animation("stone_double_door", false)));

						t.add(new Tile(id, tile_x, tile_y, (int) (i * 16 * Constants.SCALE),
								(int) (j * 16 * Constants.SCALE), tile_size_x, tile_size_y, true));
					} else if (id == 54) {
						floor.add(new Tile(id, tile_x, tile_y, (int) (i * 16 * Constants.SCALE),
								(int) (j * 16 * Constants.SCALE), tile_size_x, tile_size_y, false));
					}
				}
			}
		}
		return t;
	}

	/**
	 * @return the tiles
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	/**
	 * @return the floor
	 */
	public ArrayList<Tile> getFloor() {
		return floor;
	}

	public Tile getTile(int x, int y) {
		for (Tile t : getTiles()) {
			if (t.getPosX() == x * 16 * Constants.SCALE && t.getPosY() == y * 16 * Constants.SCALE) {
				return t;
			}

		}
		for (Tile t : getFloor()) {
			if (t.getPosX() == x * 16 * Constants.SCALE && t.getPosY() == y * 16 * Constants.SCALE) {
				return t;
			}
		}
		return null;
	}

	/**
	 * @return the mapHeight
	 */
	public int getMapHeight() {
		return mapHeight;
	}

	/**
	 * @return the mapWidth
	 */
	public int getMapWidth() {
		return mapWidth;
	}
}
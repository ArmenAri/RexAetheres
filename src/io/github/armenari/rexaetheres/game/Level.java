package io.github.armenari.rexaetheres.game;

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;

import io.github.armenari.rexaetheres.game.blocks.ActionBlock;
import io.github.armenari.rexaetheres.game.blocks.Activator;
import io.github.armenari.rexaetheres.game.blocks.Chest;
import io.github.armenari.rexaetheres.game.blocks.Door;
import io.github.armenari.rexaetheres.game.entities.Player;
import io.github.armenari.rexaetheres.game.items.Item;
import io.github.armenari.rexaetheres.renderer.Animation;
import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.utils.Constants;

public class Level {
	public static Player player;
	public static ArrayList<ActionBlock> actionBlocks;
	public static ArrayList<Item> items;
	public static ArrayList<Activator> activators;
	public static ArrayList<Door> doors;
	private ArrayList<Tile> tiles;
	private ArrayList<Tile> floor;
	private int mapHeight;
	private int mapWidth;

	public Level(String level, String secret_key_path) {
		activators = new ArrayList<>();
		doors = new ArrayList<>();
		actionBlocks = new ArrayList<>();
		items = new ArrayList<>();
		player = new Player("Player");
		floor = new ArrayList<>();
		tiles = createTileArray(level, secret_key_path);
	}

//	public void changeLevel(String path, String secret_key_path) {
//		actionBlocks.clear();
//		this.floor.clear();
//		this.tiles.clear();
//		this.tiles = createTileArray(path, secret_key_path);
//	}

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

	public ArrayList<Tile> createTileArray(String path, String secret_key_path) {
		int act = 1;
		ArrayList<Tile> t = new ArrayList<>();
		JsonObject lvl;
		int activator_code;
		
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
					int tile_x = Constants.TILE_SIZE * (id % 24);
					int tile_y = Constants.TILE_SIZE * (int) (id / 24);
					int tile_size_x = tile_x + Constants.TILE_SIZE;
					int tile_size_y = tile_y + Constants.TILE_SIZE;
					if (id >= 0 && id != 54) {
						if (id == 120) {
							activator_code = Renderer.initJSONFile("secret_keys/" + secret_key_path).getJsonObject("activator_" + act).getInt("code");
							Activator a = new Activator((int) (i * Constants.TILE_SIZE * Constants.SCALE),
									(int) (j * Constants.TILE_SIZE * Constants.SCALE), id, new Animation("activator", false), activator_code);
							actionBlocks.add(a);
							activators.add(a);
							act++;
						}
						else if (id == 176)
							actionBlocks.add(new Chest((int) (i * Constants.TILE_SIZE * Constants.SCALE),
									(int) (j * Constants.TILE_SIZE * Constants.SCALE), id, new Animation("chest", false)));
						else if (id == 172) {
							Door d = new Door((int) (i * Constants.TILE_SIZE * Constants.SCALE),
									(int) (j * Constants.TILE_SIZE * Constants.SCALE), id, new Animation("wooden_door", false));
							actionBlocks.add(d);
							doors.add(d);
						}
							
						else if (id == 148) {
							Door d = new Door((int) (i * Constants.TILE_SIZE * Constants.SCALE),
									(int) (j * Constants.TILE_SIZE * Constants.SCALE), id, new Animation("metal_door", false));
							actionBlocks.add(d);
							doors.add(d);
						}
							
						else if (id == 192)
							actionBlocks.add(new Door((int) (i * Constants.TILE_SIZE * Constants.SCALE),
									(int) (j * Constants.TILE_SIZE * Constants.SCALE), id, new Animation("stone_double_door", false)));
						t.add(new Tile(id, tile_x, tile_y, (int) (i * Constants.TILE_SIZE * Constants.SCALE),
								(int) (j * Constants.TILE_SIZE * Constants.SCALE), tile_size_x, tile_size_y, true));

					} else if (id == 54) {
						floor.add(new Tile(id, tile_x, tile_y, (int) (i * Constants.TILE_SIZE * Constants.SCALE),
								(int) (j * Constants.TILE_SIZE * Constants.SCALE), tile_size_x, tile_size_y, false));
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
			if (t.getPosX() == x * Constants.TILE_SIZE * Constants.SCALE && t.getPosY() == y * Constants.TILE_SIZE * Constants.SCALE) {
				return t;
			}

		}
		for (Tile t : getFloor()) {
			if (t.getPosX() == x * Constants.TILE_SIZE * Constants.SCALE && t.getPosY() == y * Constants.TILE_SIZE * Constants.SCALE) {
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

package io.github.armenari.rexaetheres.game;

import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.glLight;

import java.util.ArrayList;

import io.github.armenari.rexaetheres.game.blocks.Activator;
import io.github.armenari.rexaetheres.game.blocks.Door;
import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.utils.Constants;
import io.github.armenari.rexaetheres.utils.Methods;

public class Game {

	public static Level levelReader;
	ArrayList<Boolean> passage_allowed;
	public static boolean access_granted;

	public Game() {
		levelReader = new Level("level_4.json", "secret_key_1.json");
		passage_allowed = new ArrayList<>();
	}

	public void render() {
		levelReader.render();
		glLight(GL_LIGHT0, GL_POSITION,
				Methods.floatBuffer(Level.player.getPosX() + Constants.TILE_SIZE / 2 * Constants.SCALE,
						Level.player.getPosY() + Constants.TILE_SIZE / 2 * Constants.SCALE, 48, 1));

		for (int i = 0; i < Level.doors.size(); i++) {
			Door d = Level.doors.get(i);
			if (access_granted) {
				Renderer.renderQuad(d.getX() + Constants.TILE_SIZE * Constants.SCALE + 7 * Constants.SCALE, d.getY() + Constants.TILE_SIZE / 2 * Constants.SCALE - 16, 8, 8, new float[] {0, 1, 0, 1});
			} else {
				Renderer.renderQuad(d.getX() + Constants.TILE_SIZE * Constants.SCALE + 7 * Constants.SCALE, d.getY() + Constants.TILE_SIZE / 2 * Constants.SCALE - 16, 8, 8, new float[] {1, 0, 0, 1});

			}
		}
	}

	public void update() {
		levelReader.update();
		access_granted = isAccesGranted();

	}

	public boolean isAccesGranted() {
		boolean can_pass;
		for (int i = 0; i < Level.activators.size(); i++) {
			Activator a = Level.activators.get(i);
			passage_allowed.add(a.isActivated());

			for (int j = (i - i / 2); j < Level.activators.size(); j++) {
				Activator b = Level.activators.get(j);
				if (a.getCode() == b.getSignal()) {
					Renderer.renderQuad(i * Constants.SCALE * 2, j * Constants.SCALE * 2, Constants.SCALE * 2,
							Constants.SCALE * 2, Constants.YELLOW);
				}
			}

		}
		can_pass = true;
		for (int i = 0; i < passage_allowed.size(); i++) {
			if (!passage_allowed.get(i)) {
				can_pass = false;
			}
		}
		passage_allowed.clear();
		return can_pass;
	}
}

package io.github.armenari.rexaetheres.component;

import javax.json.JsonObject;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import io.github.armenari.rexaetheres.game.Game;
import io.github.armenari.rexaetheres.renderer.Renderer;
import io.github.armenari.rexaetheres.utils.Constants;
import io.github.armenari.rexaetheres.utils.Methods;

public class Component {

	public static JsonObject animation_data = Renderer.initJSONFile("assets/animation_data.json");

	Game game;

	public Component() {
		game = new Game();
	}

	private void createDisplay(String title, int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.setIcon(IconLoader.load("/assets/icon.png"));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		Renderer.init();
	}

	public void render() {
		game.render();
	}

	public void update() {
		game.update();
	}

	private void start() {
		while (!Display.isCloseRequested()) {
			Renderer.clear();
			render();
			update();
			Display.update();
			Display.sync(Constants.FPS);
		}
		Display.destroy();
		System.exit(0);
	}

	public static void main(String[] args) {
		Component main = new Component();
		main.createDisplay(Constants.TITLE, Constants.WIDTH, Constants.HEIGHT);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_LIGHT1);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);

		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, Methods.floatBuffer(1f, 1f, 1f, 0f));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, Methods.floatBuffer(0f, 0f, 0f, 0f));

		main.start();
	}
}

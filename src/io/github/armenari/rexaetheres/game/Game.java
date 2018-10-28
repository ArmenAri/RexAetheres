package io.github.armenari.rexaetheres.game;

import io.github.armenari.rexaetheres.utils.Constants;
import io.github.armenari.rexaetheres.utils.Methods;

import static org.lwjgl.opengl.GL11.*;

public class Game {

    public static Level levelReader;
    float light = 0;

    public Game() {
        levelReader = new Level("level_4.json");
    }

    public void render() {
        levelReader.render();
        glLight(GL_LIGHT0, GL_POSITION,
                Methods.floatBuffer(
                        Level.player.getPosX() + Constants.TILE_SIZE / 2 * Constants.SCALE,
                        Level.player.getPosY() + Constants.TILE_SIZE / 2 * Constants.SCALE,
                        48, 1));
    }


    public void update() {
        levelReader.update();
    }
}

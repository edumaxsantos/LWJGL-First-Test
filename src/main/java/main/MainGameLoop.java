package main;

import engine.graphics.Loader;
import engine.graphics.RawModel;
import engine.graphics.Renderer;
import engine.io.Window;
import org.lwjgl.opengl.GL15;

public class MainGameLoop {

    public static final int WIDTH = 1080, HEIGHT = 720;

    public static void main(String[] args) {
        Window window = new Window(WIDTH, HEIGHT, "main");
        window.setBackgroundColor(0f, 0f, 0f);
        window.create();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        // OpenGL expects vertices to be defined counter clockwise by default
        float[] vertices = {
                -0.5f,  0.5f, 0f, // v0
                -0.5f, -0.5f, 0f, // v1
                 0.5f, -0.5f, 0f, // v2
                 0.5f,  0.5f, 0f, // v3
        };

        int[] indices = {
                0, 1, 3, // Top left tirangle (V0, V1, V3)
                3, 1, 2 // Bottom right triangle (V3, V1, V2)
        };

        RawModel model = loader.loadToVAO(vertices, indices);

        while(!window.shouldClose()) {
            renderer.prepare();
            renderer.render(model);

            window.update();
            window.swapBuffers();
        }
        loader.cleanUp();
        window.destroy();

    }


}

package main;

import engine.io.Window;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {

    public Thread game;
    public Window window;
    //public final int WIDTH = 1920, HEIGHT = 1080;
    public final int WIDTH = 1080, HEIGHT = 720;

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
        System.out.println("Initializing Game!");
        window = new Window(WIDTH, HEIGHT, "Game");
        window.setBackgroundColor(1f, 0f, 0f);
        window.create();

    }

    public void run() {
        init();
        while(!window.shouldClose() && !window.input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            update();
            render();
            if(window.input.isKeyDown(GLFW.GLFW_KEY_F11)) {
                window.setIsFullscreen(!window.getIsFullscreen());
            }
        }
        window.destroy();
        System.out.println("Closing Game!");
    }

    private void update() {
        //System.out.println("Updating Game!");
        window.update();
        if (window.input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {

            System.out.println("X: " + window.input.getMouseX() + ", Y: " + window.input.getMouseY());
            System.out.println("ScrollX: " + window.input.getScrollX() + ", ScrollY: " + window.input.getScrollY());
        }
    }

    private void render() {
        //System.out.println("Rendering Game!");
        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}

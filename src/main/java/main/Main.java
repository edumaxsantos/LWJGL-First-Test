package main;

import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.graphics.Vertex;
import engine.io.Window;
import engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {

    public Thread game;
    public Window window;
    //public final int WIDTH = 1920, HEIGHT = 1080;
    public final int WIDTH = 1080, HEIGHT = 720;

    public Renderer renderer;

    public Shader shader;

    public Mesh mesh = new Mesh(new Vertex[] {
            /*new Vertex(new Vector3f(0f, 0f, 0f)),
            new Vertex(new Vector3f(0.2f, 0f, 0f)),
            new Vertex(new Vector3f(0.2f, 0f, 0.2f)),
            new Vertex(new Vector3f(0f, 0f, 0.2f)),
            new Vertex(new Vector3f(0f, -0.2f, 0f)),
            new Vertex(new Vector3f(0.2f, -0.2f, 0f)),
            new Vertex(new Vector3f(0.2f, -0.2f, 0.2f)),
            new Vertex(new Vector3f(0f, -0.2f, 0.2f))*/
            new Vertex(new Vector3f(-0.5f, 0.5f, 0f), new Vector3f(1f, 0f, 0f)),
            new Vertex(new Vector3f(0.5f, 0.5f, 0f), new Vector3f(0f, 1f, 0f)),
            new Vertex(new Vector3f(0.5f, -0.5f, 0f), new Vector3f(0f, 0f, 1f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0f), new Vector3f(1f, 1f, 0f))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
            /*3, 2, 6,
            3, 2, 7*/

    });

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
        System.out.println("Initializing Game!");
        String resourcesPath = System.getProperty("user.dir") + "\\src\\main\\resources";
        shader = new Shader(resourcesPath + "\\shaders\\mainVertex.glsl", resourcesPath + "\\shaders\\mainFragment.glsl");
        renderer = new Renderer(shader);
        window = new Window(WIDTH, HEIGHT, "Game");
        window.setBackgroundColor(1f, 0f, 0f);
        window.create();
        mesh.create();
        shader.create();

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
        close();
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
        renderer.renderMesh(mesh);
        window.swapBuffers();
    }

    private void close() {
        mesh.destroy();
        shader.destroy();
        window.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}

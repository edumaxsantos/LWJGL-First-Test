package main;

import engine.graphics.*;
import engine.io.Input;
import engine.io.Window;
import engine.maths.Vector2f;
import engine.maths.Vector3f;
import engine.objects.Camera;
import engine.objects.GameObject;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {

    public Thread game;
    public Window window;
    //public final int WIDTH = 1920, HEIGHT = 1080;
    public final int WIDTH = 1280, HEIGHT = 760;

    public Renderer renderer;

    public Shader shader;

    String resourcesPath = System.getProperty("user.dir") + "\\src\\main\\resources";

    public Mesh mesh = new Mesh(new Vertex[] {
            //Back face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 0.0f)),

            //Front face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),

            //Right face
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),

            //Left face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),

            //Top face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),

            //Bottom face
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
    }, new int[] {
            //Back face
            0, 1, 3,
            3, 1, 2,

            //Front face
            4, 5, 7,
            7, 5, 6,

            //Right face
            8, 9, 11,
            11, 9, 10,

            //Left face
            12, 13, 15,
            15, 13, 14,

            //Top face
            16, 17, 19,
            19, 17, 18,

            //Bottom face
            20, 21, 23,
            23, 21, 22
    }, new Material(resourcesPath + "\\textures\\beautiful.png"));

    public GameObject[] objects = new GameObject[500];

    public GameObject object = new GameObject(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);

    public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0));


    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
        System.out.println("Initializing Game!");
        window = new Window(WIDTH, HEIGHT, "Game");
        shader = new Shader(resourcesPath + "\\shaders\\mainVertex.glsl", resourcesPath + "\\shaders\\mainFragment.glsl");
        renderer = new Renderer(shader, window);

        window.setBackgroundColor(1.0f, 0f, 0f);
        window.create();
        mesh.create();
        shader.create();

        objects[0] = object;
        for (int i = 1; i < objects.length; i++) {
            objects[i] = new GameObject(
                    new Vector3f(
                            (float) Math.random() * 50 - 25,
                            (float) Math.random() * 50 - 25,
                            (float) Math.random() * 50 - 25),
                    new Vector3f(0, 0, 0),
                    new Vector3f(1, 1, 1),
                    mesh
            );
        }

    }

    public void run() {
        init();
        while(!window.shouldClose() && !window.input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            update();
            render();
            if(Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
                window.setIsFullscreen(!window.getIsFullscreen());
            }
            if(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                window.mouseState(true);
            }
        }
        close();
        System.out.println("Closing Game!");
    }

    private void update() {
        window.update();
        camera.update(object);
    }

    private void render() {
        //System.out.println("Rendering Game!");
        //renderer.renderMesh(mesh);

        for (int i = 1; i < objects.length; i++) {
            renderer.renderGameObject(objects[i], camera);
        }

        renderer.renderGameObject(object, camera);
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

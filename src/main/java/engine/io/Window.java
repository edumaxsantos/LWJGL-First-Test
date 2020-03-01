package engine.io;

import engine.maths.Matrix4f;
import engine.utils.Color;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.glfwInit;


public class Window {

    @Getter
    private int width, height;
    @Getter
    private String title;
    @Getter
    private long window;
    public int frames;
    public long time;
    public Input input;
    private Color backgroundColor;
    private GLFWWindowSizeCallback windowSizeCallback;

    private boolean isResized;

    @Getter
    private Boolean isFullscreen = false;

    private int[] windowPosX = new int[1], windowPosY = new int[1];

    @Getter
    private Matrix4f projection;


    public Window(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
        projection = Matrix4f.projection(70f, (float) width / (float) height, 0.1f, 1000f);
    }

    public void create() {

        initWindow();

        defineWindowPositions();

        createCallbacks();

        // set framerate
        GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(window);

        time = System.currentTimeMillis();
    }

    private void initWindow() {
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 6);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        if(!glfwInit()) {
            throw new IllegalStateException("ERROR: GLFW wasn't initialized");
        }

        input = new Input();



        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : MemoryUtil.NULL, MemoryUtil.NULL);

        if(window == MemoryUtil.NULL) {
            throw new IllegalStateException("ERROR: Window wasn't created");
        }
    }

    private void defineWindowPositions() {

        // set video
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        // define window position
        windowPosX[0] = (videoMode.width() - width) / 2;
        windowPosY[0] = (videoMode.height() - height) / 2;
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    private void createCallbacks() {
        windowSizeCallback = new GLFWWindowSizeCallback() {

            @Override
            public void invoke(long window, int width, int height) {
                Window.this.width = width;
                Window.this.height = height;
                Window.this.isResized = true;
            }
        };
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetWindowSizeCallback(window, windowSizeCallback);
        GLFW.glfwSetScrollCallback(window, input.getScrollCallback());
    }

    public void update() {
        if(isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }

        GL11.glClearColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha());
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();
        if(System.currentTimeMillis() > time + 1000) {
            //System.out.println(frames);
            GLFW.glfwSetWindowTitle(window, title + " FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
        frames++;
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        GLFW.glfwSetWindowShouldClose(window, true);
        input.destroy();
        windowSizeCallback.free();
        GLFW.glfwTerminate();
    }

    public void setBackgroundColor(float r, float g, float b) {
        backgroundColor = new Color(r, g, b);
    }

    public void setIsFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        this.isResized = true;
        if (isFullscreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }

}

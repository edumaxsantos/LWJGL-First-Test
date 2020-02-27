package engine.io;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.glfw.*;


public class Input {

    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];


    @Getter @Setter
    private double mouseX, mouseY;
    @Getter @Setter
    private double scrollX, scrollY;

    @Getter @Setter
    private GLFWKeyCallback keyboardCallback;
    @Getter @Setter
    private GLFWCursorPosCallback mouseMoveCallback;
    @Getter @Setter
    private GLFWMouseButtonCallback mouseButtonsCallback;

    @Getter
    private GLFWScrollCallback scrollCallback;

    public Input() {
        keyboardCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseMoveCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        mouseButtonsCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        scrollCallback = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scrollX += xoffset;
                scrollY += yoffset;
            }
        };
    }

    public boolean isKeyDown(int key) {
        return keys[key];
    }

    public boolean isButtonDown(int button) {
        return buttons[button];
    }

    public void destroy() {
        keyboardCallback.free();
        mouseButtonsCallback.free();
        mouseMoveCallback.free();
        scrollCallback.free();
    }

}

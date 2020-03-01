package engine.objects;

import engine.io.Input;
import engine.maths.Vector3f;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.glfw.GLFW;

@RequiredArgsConstructor

public class Camera {

    @Getter
    private final Vector3f position, rotation;

    private float moveSpeed = 0.05f, mouseSensitivity = 0.07f;
    private double oldMouseX, oldMouseY = 0;
    private double newMouseX, newMouseY;


    public void update() {

        float x= (float) Math.sin(Math.toRadians(rotation.getY())) * moveSpeed;
        float z = (float) Math.cos(Math.toRadians(rotation.getY())) * moveSpeed;

        if (Input.isKeyDown(GLFW.GLFW_KEY_A))
            position.set(Vector3f.add(position, new Vector3f(-z, 0, x)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_D))
            position.set(Vector3f.add(position, new Vector3f(z, 0, -x)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_W))
            position.set(Vector3f.add(position, new Vector3f(-x, 0, -z)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_S))
            position.set(Vector3f.add(position, new Vector3f(x, 0, z)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE))
            position.set(Vector3f.add(position, new Vector3f(0, moveSpeed, 0)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
            position.set(Vector3f.add(position, new Vector3f(0, -moveSpeed, 0)));

        // handling mouse
        newMouseX = Input.getMouseX();
        newMouseY = Input.getMouseY();


        float dx = (float) (newMouseX - oldMouseX);
        float dy = (float) (newMouseY - oldMouseY);

        rotation.set(Vector3f.add(rotation, new Vector3f(-dy * mouseSensitivity, -dx * mouseSensitivity, 0)));

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;

    }


}

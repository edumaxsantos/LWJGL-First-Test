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

    private float moveSpeed = 0.04f;


    public void update() {
        if (Input.isKeyDown(GLFW.GLFW_KEY_A))
            position.set(Vector3f.add(position, new Vector3f(-moveSpeed, 0, 0)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_D))
            position.set(Vector3f.add(position, new Vector3f(moveSpeed, 0, 0)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_W))
            position.set(Vector3f.add(position, new Vector3f(0, 0, -moveSpeed)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_S))
            position.set(Vector3f.add(position, new Vector3f(0, 0, moveSpeed)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE))
            position.set(Vector3f.add(position, new Vector3f(0, moveSpeed, 0)));

        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
            position.set(Vector3f.add(position, new Vector3f(0, -moveSpeed, 0)));
    }


}

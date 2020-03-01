package engine.graphics;

import engine.maths.Vector2f;
import engine.maths.Vector3f;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Vertex {
    private final Vector3f position, normal;//, color;
    private final Vector2f textureCoord;

}

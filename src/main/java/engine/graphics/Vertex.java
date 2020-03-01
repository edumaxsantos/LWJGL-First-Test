package engine.graphics;

import engine.maths.Vector2f;
import engine.maths.Vector3f;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Vertex {
    private Vector3f position;//, color;
    private Vector2f textureCoord;
}

package engine.maths;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Vector2f {

    private float x, y;

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2f add(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.x + vector2.x,
                vector1.y + vector2.y);
    }

    public static Vector2f subtract(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.x - vector2.x,
                vector1.y - vector2.y);
    }

    public static Vector2f multiply(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.x * vector2.x,
                vector1.y * vector2.y);
    }

    public static Vector2f divide(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.x / vector2.x,
                vector1.y / vector2.y);
    }

    public static float length(Vector2f vector) {
        return (float) Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2));
    }

    public static Vector2f normalize(Vector2f vector) {
        float len = length(vector);
        return divide(vector, new Vector2f(len, len));
    }

    /**
     * can be used for lights, shaders.
     * @param vector1
     * @param vector2
     * @return
     */
    public static float dot(Vector2f vector1, Vector2f vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y;
    }
}

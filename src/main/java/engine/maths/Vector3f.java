package engine.maths;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Vector3f {

    private float x, y, z;

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vector3f vector) {
        set(vector.x, vector.y, vector.z);
    }

    public static Vector3f add(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.x + vector2.x,
                vector1.y + vector2.y,
                vector1.z + vector2.z);
    }

    public static Vector3f subtract(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.x - vector2.x,
                vector1.y - vector2.y,
                vector1.z - vector2.z);
    }

    public static Vector3f multiply(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.x * vector2.x,
                vector1.y * vector2.y,
                vector1.z * vector2.z);
    }

    public static Vector3f divide(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.x / vector2.x,
                vector1.y / vector2.y,
                vector1.z / vector2.z);
    }

    public static float length(Vector3f vector) {
        return (float) Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2) + Math.pow(vector.z, 2));
    }

    public static Vector3f normalize(Vector3f vector) {
        float len = length(vector);
        return divide(vector, new Vector3f(len, len, len));
    }

    /**
     * can be used for lights, shaders.
     * @param vector1
     * @param vector2
     * @return
     */
    public static float dot(Vector3f vector1, Vector3f vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y + vector1.z * vector2.z;
    }

}

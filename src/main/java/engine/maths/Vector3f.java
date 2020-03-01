package engine.maths;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Vector3f {

    private float x, y, z;

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

}

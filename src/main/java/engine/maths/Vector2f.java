package engine.maths;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Vector2f {

    private float x, y;

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

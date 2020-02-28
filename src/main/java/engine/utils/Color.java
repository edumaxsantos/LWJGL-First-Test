package engine.utils;

import lombok.Getter;

@Getter
public class Color {

    private float red;
    private float green;
    private float blue;
    private float alpha;

    public Color(float red, float green, float blue) {
        this(red, green, blue, 1f);
    }

    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
}

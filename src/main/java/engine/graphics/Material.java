package engine.graphics;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;

@RequiredArgsConstructor
public class Material {

    private final String path;

    private Texture texture;
    @Getter
    private float width, height;
    @Getter
    private int textureID;

    public void create() {
        try {
            texture = TextureLoader.getTexture(path.split("[.]")[1], new FileInputStream(path), GL11.GL_NEAREST);
            width = texture.getWidth();
            height = texture.getHeight();
            textureID = texture.getTextureID();
        } catch (IOException e) {
            System.err.println("Can't find texture at " + path);
        }

    }

    public void destroy() {
        GL13.glDeleteTextures(textureID);
    }

}

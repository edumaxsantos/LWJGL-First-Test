package engine.graphics;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@RequiredArgsConstructor
@Getter()
public class Mesh {

    private final Vertex[] vertices;
    private final int[] indices;
    // VAO (Vertex Array Object) stores data about a 3D model
    // has a lot of slots (attribute lists), up to 16.
    // VBO (Vertex Buffer Object) is just data,
    // and is put on a different position in a VAO.
    // Each VAO has a unique ID to be accessed.
    private int vao, pbo, ibo, cbo, tbo;

    private final Material material;

    public void create() {
        material.create();
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            positionData[i * 3] = vertices[i].getPosition().getX();
            positionData[i * 3 + 1] = vertices[i].getPosition().getY();
            positionData[i * 3 + 2] = vertices[i].getPosition().getZ();
        }
        positionBuffer.put(positionData).flip();

        pbo = storeData(positionBuffer, 0, 3);

        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] colorData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            colorData[i * 3] = vertices[i].getColor().getX();
            colorData[i * 3 + 1] = vertices[i].getColor().getY();
            colorData[i * 3 + 2] = vertices[i].getColor().getZ();
        }
        colorBuffer.put(colorData).flip();

        cbo = storeData(colorBuffer, 1, 3);

        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(vertices.length * 2);
        float[] textureData = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i++) {
            textureData[i * 2] = vertices[i].getTextureCoord().getX();
            textureData[i * 2 + 1] = vertices[i].getTextureCoord().getY();
        }
        textureBuffer.put(textureData).flip();

        tbo = storeData(textureBuffer, 2, 2);


        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();
        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        return bufferID;
    }

    public void destroy() {
        GL15.glDeleteBuffers(pbo);
        GL15.glDeleteBuffers(cbo);
        GL15.glDeleteBuffers(ibo);
        GL15.glDeleteBuffers(tbo);
        GL30.glDeleteVertexArrays(vao);

        material.destroy();
    }

    private void storeDataInIntBuffer(int[] data) {
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(data.length);
        indicesBuffer.put(data).flip();
        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

}

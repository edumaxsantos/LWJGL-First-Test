package engine.objects;

import engine.graphics.Mesh;
import engine.maths.Vector3f;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GameObject {

    private final Vector3f position, rotation, scale;
    private final Mesh mesh;
    //private double temp;


    public void update() {
        //temp += 0.02;
        position.setZ(position.getZ() - 0.05f);
        /*position.setX((float) Math.sin(temp));
        rotation.set((float) Math.sin(temp) * 360, (float) Math.sin(temp) * 360, (float) Math.sin(temp) * 360);
        scale.set((float) Math.sin(temp), (float) Math.sin(temp), (float) Math.sin(temp));*/
    }



}

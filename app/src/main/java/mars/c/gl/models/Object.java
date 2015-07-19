package mars.c.gl.models;

import lombok.Data;

/**
 * Created by Constantine Mars on 7/19/15.
 */
@Data
public abstract class Object {
    private float[] mesh;

//    should be overriden
    protected Object() {}

    public abstract void draw();

}

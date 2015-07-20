package mars.c.gl.models;

import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by Constantine Mars on 7/19/15.
 */
public class Triangle extends Object {
//    each object has its own mesh of vertices
    private final float[] vertices = {
        0f, 1f, 0f, 1f, 0f,
        -1f, -1f, 1f, 0f, 0f,
        1f, -1, 1f, 1f, 0f,
        0f, 1f, 0f, 1f, 0f,
    };

    public Triangle() {
        super();
        setMesh(vertices, 5, 4);
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLE_FAN, 0, getVerticesCount());
    }

}

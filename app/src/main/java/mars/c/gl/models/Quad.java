package mars.c.gl.models;

import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by Constantine Mars on 7/19/15.
 */
public class Quad extends Object {
//    each object has its own mesh of vertices
    private float[] vertices = {
        -0.7f, -0.7f, 1f, 0f, 0f,
        0.7f, -0.7f, 0f, 1f, 0f,
        0.7f, 0.7f, 0f, 0f, 1f,
        -0.7f, 0.7f, 1f, 1f, 0f,
        -0.7f, -0.7f, 1f, 0f, 0f,
    };

    public Quad() {
        super();
//
        setMesh(vertices);
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLE_FAN, 0, 5);
    }

}

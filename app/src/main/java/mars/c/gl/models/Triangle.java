package mars.c.gl.models;

import lombok.Data;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;

/**
 * Created by Constantine Mars on 7/16/15.
 */
@Data
public class Triangle {

    private float[] vertices={
            // Triangle Fan
            0,     0,
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f,  0.5f,
            -0.5f,  0.5f,
            -0.5f, -0.5f,
    };

    public void draw(){
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
    }
}

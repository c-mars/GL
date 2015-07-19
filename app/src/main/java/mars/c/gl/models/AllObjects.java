package mars.c.gl.models;

import lombok.Data;

import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by Constantine Mars on 7/16/15.
 */
@Data
public class AllObjects extends Object{
    private final float[] verticesWorld = {
//            table
            0f, 0f,
            9f, 14f,
            0f, 14f,

            0f, 0f,
            9f, 0f,
            9f, 14f,

//            line
            0f, 7f,
            9f, 7f,

//            mallets
            4.5f, 2f,
            4.5f, 12f

    };

    private final float[] verticesGL = {
//            table
            -0.5f, -0.5f,
            0.5f, 0.5f,
            -0.5f, 0.5f,

            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f,

//            line
            -0.5f, 0f,
            0.5f, 0f,

//            mallets
            0f, -0.25f,
            0f, 0.25f
    };

        private  float[] mesh = {
// Order of coordinates: X, Y, R, G, B
                // Triangle Fan
                0f,    0f,   1f,   1f,   1f,
                -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
                0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
                0.5f,  0.5f, 0.7f, 0.7f, 0.7f,
                -0.5f,  0.5f, 0.7f, 0.7f, 0.7f,
                -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
// Line 1
                -0.5f, 0f, 1f, 0f, 0f,
                0.5f, 0f, 1f, 0f, 0f,
// Mallets
                0f, -0.25f, 0f, 0f, 1f,
                0f,  0.25f, 1f, 0f, 0f
        };

        public void draw(){
                glDrawArrays(GL_TRIANGLE_FAN, 0, 6);

//            Shaders.setuColorLocation(Color.RED);
                glDrawArrays(GL_LINES, 6, 2);
                glDrawArrays(GL_POINTS, 8, 1);
                glDrawArrays(GL_POINTS, 9, 1);
        }
}

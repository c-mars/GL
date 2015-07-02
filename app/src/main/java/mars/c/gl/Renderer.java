package mars.c.gl;

import android.graphics.Color;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glDrawElements;
import static android.opengl.GLES20.glUniform4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glViewport;

/**
 * Created by Constantine Mars on 6/29/15.
 */
public class Renderer implements GLSurfaceView.Renderer {
    private static final int VS=2;
    private float[] vs={
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
    private int color = Color.GREEN;
    private static final int FS=4;
    private final FloatBuffer vd;
    private int p;
    private boolean v;

    public Renderer() {
        vd = ByteBuffer.allocateDirect(vs.length * FS)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer();
        vd.put(vs);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0f,0f,0f,0f);
        p=Shaders.linkProgram();
        if(Shaders.validateProgram(p)){
            glUseProgram(p);
            Shaders.setVertices(p, vd, VS);
            v=true;
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);

        if(v) {
            Shaders.setColor(color);
            glDrawArrays(GL_TRIANGLES, 0, 6);
            glDrawArrays(GL_LINES, 6, 2);
            glDrawArrays(GL_POINTS, 8, 1);
            glDrawArrays(GL_POINTS, 9, 1);
        }
    }
}

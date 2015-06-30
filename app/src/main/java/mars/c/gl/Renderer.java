package mars.c.gl;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
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
    private static final int FS=4;
    private final FloatBuffer vd;
    private int p;

    public Renderer() {
        vd = ByteBuffer.allocateDirect(vs.length * FS)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer();
        vd.put(vs);

        p=Shaders.linkProgram();
        if(Shaders.validateProgram(p)){
            glUseProgram(p);
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(1f,0f,0f,0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);
    }
}

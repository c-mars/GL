package mars.c.gl;

import android.graphics.Color;
import android.opengl.GLSurfaceView;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Collections;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import mars.c.gl.models.AllObjects;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glViewport;

/**
 * Created by Constantine Mars on 6/29/15.
 */
public class Renderer implements GLSurfaceView.Renderer {
    private static final int VS = 2;

//    // FIXME: 7/15/15 get separate x and y max values
    private float[] translateToGLCoords(float[] in) {

        if (in.length > 0) {
            float oleft=-0.5f;
            float oright=0.5f;
            float omid=0f;

            float[] out = new float[in.length];
            float max = Collections.max(Arrays.asList(ArrayUtils.toObject(in))) ;
            float mid = max / 2f;

            float scale=oright/(max-mid);

            for (int i = 0; i < in.length; i++) {
                float x=in[i];
                float ox = (x-mid)*scale;
                out[i]=ox;
            }
            return out;
        }
        return in;
    }

    private int color = Color.GREEN;
    private static final int FS = 4;
    private final FloatBuffer vd;
    private int p;
    private boolean v;
    private AllObjects allObjects=new AllObjects();

    public Renderer() {
        float[] vs= allObjects.getVerticesGL();
        vd = ByteBuffer.allocateDirect(vs.length * FS)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        float[] c = vs;
//        // FIXME: 7/15/15 translate can be used when left/right margins calculation becomes possible
//                translateToGLCoords(vsOriginal);
        vd.put(c);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0f, 0f, 0f, 0f);
        p = Shaders.linkProgram();
        if (Shaders.validateProgram(p)) {
            glUseProgram(p);
            Shaders.setVertices(p, vd, VS);
            v = true;
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);

        if (v) {
            Shaders.setColor(Color.GRAY);
            glDrawArrays(GL_TRIANGLES, 0, 6);

            Shaders.setColor(Color.RED);
            glDrawArrays(GL_LINES, 6, 2);
            glDrawArrays(GL_POINTS, 8, 1);
            glDrawArrays(GL_POINTS, 9, 1);
        }
    }
}

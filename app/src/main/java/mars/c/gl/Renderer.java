package mars.c.gl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import mars.c.gl.helpers.RenderHelper;
import mars.c.gl.models.*;
import mars.c.gl.models.Object;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

/**
 * Created by Constantine Mars on 6/29/15.
 */
public class Renderer implements GLSurfaceView.Renderer {
    private Integer p;
    RenderHelper renderHelper;
    Object object =  new Quad(); //new AllObjects();

    public Renderer() {
//        create paint helper and setup mesh
        renderHelper =new RenderHelper(object.getMesh());
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0f, 0f, 0f, 0f);
//        create pogram and setup shaders
        p = renderHelper.createProgram();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);

        if (p!=null) {
            object.draw();
        }
    }
}

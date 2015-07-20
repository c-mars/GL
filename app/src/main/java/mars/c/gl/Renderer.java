package mars.c.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import mars.c.gl.helpers.CSVPolygonReader;
import mars.c.gl.helpers.RenderHelper;
import mars.c.gl.models.AllObjects;
import mars.c.gl.models.Object;
import mars.c.gl.models.Quad;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.orthoM;

/**
 * Created by Constantine Mars on 6/29/15.
 */
public class Renderer implements GLSurfaceView.Renderer {
    private Integer p;
    RenderHelper renderHelper;
    Object object = null;

    private Renderer(){}

    public Renderer(Context context) {
//        create paint helper and setup mesh

        object=CSVPolygonReader.read(context, R.raw.hexagon);//=  new AllObjects();
        if (object != null) {
            renderHelper =new RenderHelper(object.getMesh());
        }
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
        renderHelper.createProjection(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);
        renderHelper.setMatrix();

        if (p!=null) {
            object.draw();
        }
    }
}

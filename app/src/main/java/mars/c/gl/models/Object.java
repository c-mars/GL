package mars.c.gl.models;

import android.graphics.Color;

import lombok.Data;
import mars.c.gl.Shaders;
import mars.c.gl.helpers.RenderHelper;

import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;

/**
 * Created by Constantine Mars on 7/19/15.
 */
@Data
public class Object {
    private float[] mesh;
    private int vertexSize;
    private int verticesCount;
    private RenderHelper.VertexFormat vertexFormat;
    int color = Color.GREEN;

    public void setMesh(float[] mesh, int vertexSize, int verticesCount){
        this.mesh=mesh;
        this.vertexSize = vertexSize;
        this.verticesCount=verticesCount;
        if(vertexSize==2){
            vertexFormat= RenderHelper.VertexFormat.TWO_COORDS;
        }
    }

    public void draw(){
        Shaders.selectColor(color);
        glDrawArrays(GL_TRIANGLE_FAN, 0, verticesCount);
    }

}

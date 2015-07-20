package mars.c.gl.helpers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import mars.c.gl.Shaders;

import static android.opengl.GLES20.glUseProgram;
import static android.opengl.Matrix.orthoM;

/**
 * Created by Constantine Mars on 7/19/15.
 * helps to draw object according to its format
 */
public class RenderHelper {
    private static final int BYTES_PER_FLOAT = 4;
    private int positionComponentCount = 2;
    private int colorComponentCount = 3;
    private int stride(){ return (positionComponentCount + colorComponentCount) * BYTES_PER_FLOAT;}
    private final FloatBuffer vertexData;

    public enum VertexFormat{
        TWO_COORDS,
        TWO_COORDS_WITH_COLOR
    }
    VertexFormat vertexFormat=VertexFormat.TWO_COORDS_WITH_COLOR;

    private final float[] projectionMatrix=new float[16];

    public RenderHelper(float[] mesh) {
        this(mesh, null);
    }

    public RenderHelper(float[] mesh, VertexFormat vertexFormat) {
        if(vertexFormat != null){
            this.vertexFormat=vertexFormat;
            switch (vertexFormat){
                case TWO_COORDS:
                    positionComponentCount=2;
                    colorComponentCount=0;
                    break;
            }
        }

        vertexData= ByteBuffer.allocateDirect(mesh.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(mesh);
    }

    public Integer createProgram(){
        int p = Shaders.linkProgram();
        if (Shaders.validateProgram(p)) {
            glUseProgram(p);
            if(vertexFormat.equals(VertexFormat.TWO_COORDS)){
                Shaders.setPositionData(vertexData, positionComponentCount, stride());
            } else {
                Shaders.setVertices(vertexData, positionComponentCount, stride(), colorComponentCount);
            }
            return p;
        }
        return null;
    }

    public void createProjection(int width, int height){
        final float aspectRatio=width>height
                ? (float)width/(float)height
                :(float)height/(float)width;
        if(width>height){
            orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        } else {
            orthoM(projectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

    public void setMatrix(){
        Shaders.setMatrix(projectionMatrix);
    }
}

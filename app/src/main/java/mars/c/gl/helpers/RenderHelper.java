package mars.c.gl.helpers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import mars.c.gl.Shaders;

import static android.opengl.GLES20.glUseProgram;

/**
 * Created by Constantine Mars on 7/19/15.
 */
public class RenderHelper {
    private static final int BYTES_PER_FLOAT = 4;
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;
    private final FloatBuffer vertexData;

    public RenderHelper(float[] mesh) {
        vertexData= ByteBuffer.allocateDirect(mesh.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(mesh);
    }

    public Integer createProgram(){
        int p = Shaders.linkProgram();
        if (Shaders.validateProgram(p)) {
            glUseProgram(p);
            Shaders.setVertices(p, vertexData, POSITION_COMPONENT_COUNT, STRIDE, COLOR_COMPONENT_COUNT);
            return p;
        }
        return null;
    }
}

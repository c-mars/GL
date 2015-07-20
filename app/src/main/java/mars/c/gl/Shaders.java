package mars.c.gl;

import android.graphics.Color;

import java.nio.FloatBuffer;

import timber.log.Timber;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glValidateProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glVertexAttrib4f;

/**
 * Created by Constantine Mars on 6/29/15.
 */
public class Shaders {
    public static final String A_POSITION = "a_Position";
    public static final String A_COLOR = "a_Color"; //attribute
    public static final String V_COLOR = "v_Color"; // varying
    public static final String U_MATRIX = "u_Matrix"; // uniform
    private static final String VERTEX_SHADER = "" +
            "uniform mat4 " + U_MATRIX + ";" +
            "attribute vec4 " + A_POSITION + ";" +
            "attribute vec4 " + A_COLOR + ";" +
            "varying vec4 " + V_COLOR + ";" +
            "void main() {" +
            "   " + V_COLOR + " = " + A_COLOR + ";" +
            "   gl_Position=" + U_MATRIX + " * " + A_POSITION + ";" +
            "   gl_PointSize = 10.0;" +
            "}";
    private static final String FRAGMENT_SHADER = "" +
            "precision mediump float" + ";" +
            "varying vec4 " + V_COLOR + ";" +
            "void main() {" +
            "   gl_FragColor=" + V_COLOR + ";" +
            "}";

    private static Integer aColorLocation;
    private static Integer aPositionLocation;
    private static Integer uMatrixLocation;

    public static int compileVertexShader() {
        return compileShader(GL_VERTEX_SHADER, VERTEX_SHADER);
    }

    public static int compileFragmentShader() {
        return compileShader(GL_FRAGMENT_SHADER, FRAGMENT_SHADER);
    }

    private static int compileShader(int t, String c) {
        int s = glCreateShader(t);
        if (s == 0) {
            Timber.e("Couldn't create shader");
            return 0;
        }
        glShaderSource(s, c);
        glCompileShader(s);
        int[] cs = new int[1];
        glGetShaderiv(s, GL_COMPILE_STATUS, cs, 0);
        if (cs[0] == 0) {
            Timber.e("Shader [" + s + "] compilation error: " + glGetShaderInfoLog(s));
            glDeleteShader(s);
            return 0;
        }
        return s;
    }

    public static int linkProgram() {
        int vs = compileVertexShader();
        int fs = compileFragmentShader();
        int p = glCreateProgram();
        if (p == 0) {
            Timber.e("Create program failed: " + glGetProgramInfoLog(p));
            return 0;
        }
        glAttachShader(p, vs);
        glAttachShader(p, fs);
        glLinkProgram(p);
        int[] ls = new int[1];
        glGetProgramiv(p, GL_LINK_STATUS, ls, 0);
        if (ls[0] == 0) {
            Timber.e("Link program failed: " + glGetProgramInfoLog(p));
            return 0;
        }

        aPositionLocation = glGetAttribLocation(p, A_POSITION);
        aColorLocation = glGetAttribLocation(p, A_COLOR);
        uMatrixLocation=glGetUniformLocation(p, U_MATRIX);

        return p;
    }

    public static boolean validateProgram(int p) {
        glValidateProgram(p);
        final int[] vs = new int[1];
        glGetProgramiv(p, GL_VALIDATE_STATUS, vs, 0);
        Timber.v("Validating program result: " + vs[0] + " - " + glGetProgramInfoLog(p));
        return vs[0] != 0;
    }

    public static void setPositionData(FloatBuffer vertexData, int positionComponentCount, int stride){
        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, positionComponentCount, GL_FLOAT, false, stride, vertexData);
        glEnableVertexAttribArray(aPositionLocation);
    }

    public static void setColorData(FloatBuffer vertexData, int positionComponentCount, int stride, int colorComponentCount){
        vertexData.position(positionComponentCount);
        glVertexAttribPointer(aColorLocation, colorComponentCount, GL_FLOAT, false, stride, vertexData);
        glEnableVertexAttribArray(aColorLocation);
    }

    public static void setVertices(FloatBuffer vertexData, int positionComponentCount, int stride, int colorComponentCount){
        setPositionData(vertexData, positionComponentCount, stride);
        setColorData(vertexData, positionComponentCount, stride, colorComponentCount);
//        set vertices array for position
//        vertexData.position(0);
//        glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, false, STRIDE, vertexData);
//        glEnableVertexAttribArray(aPositionLocation);

//        set vertices array for color
//        vertexData.position(POSITION_COMPONENT_COUNT);
//        glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GL_FLOAT, false, STRIDE, vertexData);
//        glEnableVertexAttribArray(aColorLocation);
    }

    public static void setMatrix(float[] projectionMatrix){
        glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);
    }

    public static void selectColor(int color) {
        glVertexAttrib4f(aColorLocation, Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
    }

}

package mars.c.gl;

import timber.log.Timber;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;

/**
 * Created by Constantine Mars on 6/29/15.
 */
public class Shaders {
    public static final String AP ="ap";
    public static final String UC ="uc";
    private static final String VERTEX_SHADER="" +
            "attribute vec4 "+ AP +
            "void main() {" +
            "   gl_Position="+ AP +
            "}";
    private static final String FRAGMENT_SHADER="" +
            "precision mediump float" +
            "uniform vec4 "+ UC +
            "void main() {" +
            "   gl_FragColor="+ UC +
            "}";
    public static int compileVertexShader(){
        return compileShader(GL_VERTEX_SHADER, VERTEX_SHADER);
    }

    public static int compileFragmentShader(){
        return compileShader(GL_FRAGMENT_SHADER, FRAGMENT_SHADER);
    }

    private static int compileShader(int t, String c){
        int s=glCreateShader(t);
        if(s==0){
            Timber.e("Couldn't create shader");
        }
        glShaderSource(s, c);
        glCompileShader(s);
        int[] cs=new int[1];
        glGetShaderiv(s, GL_COMPILE_STATUS, cs, 0);
        if(cs[0]==0) {
            Timber.e("Shader [" + s + "] compilation error: " + glGetShaderInfoLog(s));
            glDeleteShader(s);
            return 0;
        }
        return s;
    }

    public static int linkProgram(){
        int vs=compileVertexShader();
        int fs=compileFragmentShader();
        int p=glCreateProgram();
        if(p==0){
            Timber.e("Create program failed: "+glGetProgramInfoLog(p));
            return 0;
        }
        glAttachShader(p, vs);
        glAttachShader(p, fs);
        glLinkProgram(p);
        int[] ls=new int[1];
        glGetProgramiv(p, GL_LINK_STATUS, ls, 0);
        if(ls[0]==0){
            Timber.e("Link program failed: " + glGetProgramInfoLog(p));
            return 0;
        }
        return p;
    }

    public static boolean validateProgram(int p) {
        glValidateProgram(p);
        final int[] vs = new int[1];
        glGetProgramiv(p, GL_VALIDATE_STATUS, vs, 0);
        Timber.v("Validating program result: " + vs[0] + " - " + glGetProgramInfoLog(p));
        return vs[0] != 0;
    }

}

package mars.c.gl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;


public class MainActivity extends ActionBarActivity {

    @Bind(R.id.t)
    TextView t;
    @Bind(R.id.r)
    RelativeLayout r;

    private GLSurfaceView sv;
    private boolean rs=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());
        ButterKnife.bind(this);

        sv=new GLSurfaceView(this);
        sv.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        r.addView(sv);
        if(GL.supportsGLES2(this)){
            sv.setEGLContextClientVersion(2);
            sv.setRenderer(new Renderer());
            sv.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
            rs=true;
        } else {
            String m="This device does not support OpenGL ES 2.0";
            Timber.e(m);
            Toast.makeText(this, m, Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(rs) {
            sv.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rs){
            sv.onResume();
        }
    }
}

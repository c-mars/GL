package mars.c.gl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    }

}
